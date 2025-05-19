package com.example.trialactivities.utilities;

import android.net.Uri;
import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Converters {
    private static final Gson gson = new Gson();

    // ✅ Convert Uri to String (for Room storage)
    @TypeConverter
    public static String fromUri(Uri uri) {
        return (uri == null) ? null : uri.toString();
    }

    // ✅ Convert String back to Uri (for Room storage)
    @TypeConverter
    public static Uri toUri(String uriString) {
        return (uriString == null) ? null : Uri.parse(uriString);
    }

    // Convert JSON String back to List<Integer>
    @TypeConverter
    public static List<Integer> toIntegerList(String json) {
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    // Convert String[] to JSON String (for Room storage)
    @TypeConverter
    public static String fromStringArray(String[] array) {
        return gson.toJson(array);
    }

    // Convert JSON String back to String[]
    @TypeConverter
    public static String[] toStringArray(String json) {
        Type type = new TypeToken<String[]>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Convert int[] to JSON String (for Room storage)
    @TypeConverter
    public static String fromIntArray(int[] array) {
        return gson.toJson(array);
    }

    // Convert JSON String back to int[]
    @TypeConverter
    public static int[] toIntArray(String json) {
        Type type = new TypeToken<int[]>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Convert Map<String, Integer> to JSON String (for Room storage)
    @TypeConverter
    public static String fromMap(Map<String, Integer> map) {
        return gson.toJson(map);
    }

    // Convert JSON String back to Map<String, Integer>
    @TypeConverter
    public static Map<String, Integer> toMap(String json) {
        Type type = new TypeToken<Map<String, Integer>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Convert List<String> to JSON String (for Room storage)
    @TypeConverter
    public static String fromStringList(List<String> list) {
        return gson.toJson(list);
    }

    // Convert JSON String back to List<String>
    @TypeConverter
    public static List<String> toStringList(String json) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, listType);
    }
}
