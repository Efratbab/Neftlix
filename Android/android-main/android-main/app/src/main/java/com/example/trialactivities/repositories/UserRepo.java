package com.example.trialactivities.repositories;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.trialactivities.NetflixApplication;
import com.example.trialactivities.api.UserAPI;
import com.example.trialactivities.database.UserDB;
import com.example.trialactivities.entities.User;
import com.example.trialactivities.room.UserDao;
import com.example.trialactivities.utilities.FileUtil;

import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserRepo {

    private UserDao userDao;
    private UserListData userListData;
    private UserAPI api;
    private static final String TAG = "UserRepo";  // 🔹 Tag for Logcat



    public UserRepo() {
        UserDB db = UserDB.getInstance(NetflixApplication.getAppContext());
        userDao = db.userDao();
        userListData = new UserListData();
        api = new UserAPI(userListData, userDao);
    }

    class UserListData extends MutableLiveData<List<User>> {

        public UserListData() {
            super();
            setValue(new LinkedList<User>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            Log.d(TAG, "Fetching users from Room...");
            new Thread(() -> {
                List<User> newUsers = userDao.index();
                List<User> currentUsers = userListData.getValue();

                // ⚠️ Avoid updating LiveData if the data hasn't changed
                if (currentUsers == null || !currentUsers.equals(newUsers)) {
                    userListData.postValue(newUsers);
                }
            }).start();
        }

    }

//    public LiveData<List<User>> getAll() {
//        new Thread(() -> {
//            List<User> users = userDao.index();
//            Log.d("UserRepo", "📂 Users fetched from Room: " + users);
//            userListData.postValue(users);
//        }).start();
//        return userListData;
//    }

//    public void clearAllUsers() {
//        executorService.execute(() -> {
//            userDao.clearAllUsers();
//            Log.d(TAG, "🗑️ Room database cleared on app launch");
//        });
//    }

//    public void add(Uri profilePicture, User user, String username,String password,String nameForDisplay) {
//        RequestBody imageBody = RequestBody.create(MediaType.parse("jpg/*"), new File(profilePicture.getPath()));
//        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("profilePicture", new File(profilePicture.getPath()).getName(), imageBody);
//        api.signUp(user, imagePart, username,password, nameForDisplay);
//    }

    public void add(Context context, Uri profilePicture, User user, String username, String password, String nameForDisplay) {
        // ✅ Get real file path from Uri
        String filePath = FileUtil.getRealPathFromURI(context, profilePicture);

        if (filePath == null || !(new File(filePath).exists())) {
            Log.e("UserRepo", "❌ Invalid file path: " + filePath);
            return;
        }

        File imageFile = new File(filePath);

        // ✅ Get the correct MIME type instead of "image/*"
        String mimeType = getMimeType(filePath);
        if (mimeType == null || (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png") && !mimeType.equals("image/jpg"))) {
            Log.e("UserRepo", "❌ Invalid MIME type: " + mimeType);
            return;
        }

        Log.d("UserRepo", "✅ Detected MIME type: " + mimeType);

        // ✅ Use the detected MIME type
        RequestBody imageBody = RequestBody.create(MediaType.parse(mimeType), imageFile);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("profilePicture", imageFile.getName(), imageBody);

        api.signUpUser(user, imagePart, username, password, nameForDisplay);
    }

    // ✅ Helper function to get MIME type based on file extension.
    private String getMimeType(String filePath) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(filePath);
        if (extension != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
        }
        return null;
    }

    /**
     * Get a specific user by ID.
     */
//    public void getUser(int userID) {
//        Log.d(TAG, "Fetching user with ID: " + userID);
//        api.getUser(userID);
//    }

    /**
     * Update a user.
     */
//    public void update(User user) {
//        Log.d(TAG, "Updating user: " + user);
//        api.updateUser(user.getId(), user);
//    }
}
