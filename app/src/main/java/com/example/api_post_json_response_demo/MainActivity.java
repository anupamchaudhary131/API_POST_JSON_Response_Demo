package com.example.api_post_json_response_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidNetworking.initialize(this);
        AndroidNetworking.post("https://wscubetech.org/android-course/get-data.php")
                .addBodyParameter("course_id", "1")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Res", response.toString());

                        try {
                            JSONObject objData = response.getJSONObject("data");
                            String Name = objData.getString("name");

                            Log.d("name", Name);

                            JSONObject objDesc = objData.getJSONObject("description");
                            String extension = objDesc.getString("extension");

                            Log.d("ext", extension);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();

                    }
                });
    }
}