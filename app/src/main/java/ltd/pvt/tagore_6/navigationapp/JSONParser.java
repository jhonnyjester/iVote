package ltd.pvt.tagore_6.navigationapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
/**
 * Created by Aman on 6/19/2017.
 */
public class JSONParser {
    private static final String MAIN_URL = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=186qvoDxOSz3teDiDghq9jH6VrvDxPttrcg-UnJKrbBc&sheet=Sheet";

    public static final String TAG = "TAG";

    private static final String KEY_USER_ID = "user_id";

    private static Response response;

    public static JSONObject getDataFromWeb(String page) {
        try {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL+page)
                    .build();
            response = client.newCall(request).execute();
           // Toast.makeText(context,"Parser",Toast.LENGTH_LONG).show();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataById(int userId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_USER_ID, Integer.toString(userId))
                    .build();

            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .post(formBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}
