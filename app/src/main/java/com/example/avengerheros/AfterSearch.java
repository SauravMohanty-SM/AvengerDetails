package com.example.avengerheros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AfterSearch extends AppCompatActivity {

    private ProgressDialog mLoadingBar;
    String url, finalUrl;
    List<Data> datas;
    Adapter mAdapter;
    RecyclerView mRecyclerView;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_after_search);

                mLoadingBar = new ProgressDialog(this);
                showProgressBar();

                mRecyclerView = findViewById(R.id.recycleView);
                datas = new ArrayList<>();

                Intent i = getIntent();
                url = i.getStringExtra("url");

                finalUrl = "https://superheroapi.com/api/1142391666189631/search/"+url;

                ApiCall(finalUrl);


    }

    private void showProgressBar() {

        mLoadingBar.setTitle("Searching Hero");
        mLoadingBar.setMessage("Please wait, while we searching your hero.");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();
    }

    private void ApiCall(final String newURL) {

        RequestQueue queue = Volley.newRequestQueue(AfterSearch.this);

//       Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, newURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        mLoadingBar.dismiss();

                        Log.d("Avg","Success" + response.toString());

                        try {
//                            JSONObject array = response.getJSONObject("results");
                            Log.d("Avg", "Length is " + response.getJSONArray("results").length());

                            for (int i = 0; i < response.getJSONArray("results").length(); i++) {
                                Data data = new Data();
                                try {
                                    data.setName(response.getJSONArray("results").getJSONObject(i).getString("name"));
                                    Log.d("Avg", "Name is " + response.getJSONArray("results").getJSONObject(i).getString("name"));
                                    data.setImageUrl(response.getJSONArray("results").getJSONObject(i).getJSONObject("image").getString("url"));
                                    Log.d("Avg", "Image is " + response.getJSONArray("results").getJSONObject(i).getJSONObject("image").getString("url"));
                                    datas.add(data);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d("Avg", "Error is " + e.toString());
                                }
                        }

                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            mAdapter = new Adapter(getApplicationContext(), datas);
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Avg", "Error is " + e.toString());
                            showErrorDialog();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        mLoadingBar.dismiss();
                        Log.d("Avg", "Fail : " + error.toString());
                        showErrorDialog();

                    }
                });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

    private void showErrorDialog() {
        new AlertDialog.Builder(AfterSearch.this)
                .setTitle("Oops")
                .setMessage("Could not find anything. Try again")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(AfterSearch.this, MainActivity.class));
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .show();
    }
}