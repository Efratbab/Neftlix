package com.example.trialactivities.utilities;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import java.io.File;

public class FileUtil {

    private static final String TAG = "FileUtil";

    public static String getRealPathFromURI(Context context, Uri uri) {
        String filePath = null;

        // ✅ Case 1: If it's a "file://" Uri, return the path directly
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            filePath = uri.getPath();
            Log.d(TAG, "📂 File Uri detected, returning: " + filePath);
            return filePath;
        }

        // ✅ Case 2: If it's a "content://" Uri (Gallery, Downloads, etc.)
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    filePath = cursor.getString(columnIndex);
                    Log.d(TAG, "✅ Content Uri resolved to: " + filePath);
                }
            } catch (Exception e) {
                Log.e(TAG, "❌ Error getting file path from Uri", e);
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        // ✅ Case 3: If no path was found, try last resort
        if (filePath == null) {
            filePath = uri.getPath();
            Log.w(TAG, "⚠️ Could not resolve Uri, falling back to: " + filePath);
        }

        return filePath;
    }
}

