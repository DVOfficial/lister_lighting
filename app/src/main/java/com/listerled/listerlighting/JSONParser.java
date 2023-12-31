package com.listerled.listerlighting;

import android.util.Log;

import androidx.annotation.NonNull;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JSONParser {

    private static final String MAIN_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=19xQitiSto6orcaM2RN02-ASz_56N-8w4dGFBLtTlvo0&sheet=English";
    private static final String MAIN_LATEST_URL_ENG = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=19xQitiSto6orcaM2RN02-ASz_56N-8w4dGFBLtTlvo0&sheet=Latest_Eng";

    public static final String TAG = "TAG";

    private static final String KEY_USER_ID = "user_id";

    private static Response response;

    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getLatestEngDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_LATEST_URL_ENG)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

//    public static JSONObject getDataById(int userId) {
//
//        try {
//            OkHttpClient client = new OkHttpClient();
//
//            RequestBody formBody = new FormEncodingBuilder()
//                    .add(KEY_USER_ID, Integer.toString(userId))
//                    .build();
//
//            Request request = new Request.Builder()
//                    .url(MAIN_URL)
//                    .post(formBody)
//                    .build();
//
//            response = client.newCall(request).execute();
//            return new JSONObject(response.body().string());
//
//        } catch (IOException | JSONException e) {
//            Log.e(TAG, "" + e.getLocalizedMessage());
//        }
//        return null;
//    }
}