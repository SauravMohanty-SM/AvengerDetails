package com.example.avengerheros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    EditText searchName;
    ImageView SearchIcon;
    String URL = "https://superheroapi.com/api/1142391666189631/search/";
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialization();

        searchName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String HeroName = searchName.getText().toString();
                String NewURL = URL + HeroName;

                mLoadingBar.setTitle("Searching Your Hero");
                mLoadingBar.setMessage("Please wait, while we searching your hero.");
                mLoadingBar.setCanceledOnTouchOutside(true);
                mLoadingBar.show();

                Log.d("Avg", NewURL);

                ApiCall(NewURL);

                return false;
            }
        });

        SearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoadingBar.setTitle("Searching Hero");
                mLoadingBar.setMessage("Please wait, while we searching your hero.");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();

                String HeroName = searchName.getText().toString();
                String NewURL = URL + HeroName;

                Log.d("Avg", NewURL);

                ApiCall(NewURL);

            }
        });
    }

    private void ApiCall(String newURL) {

//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

// Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, newURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mLoadingBar.dismiss();

                        Log.d("Avg", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        mLoadingBar.dismiss();

                        showErrorDialog(error);

                    }
                });

// Add the request to the RequestQueue.
//        queue.add(jsonObjectRequest);
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

    private void initialization() {

        searchName = (EditText) findViewById(R.id.HeroSearch);
        SearchIcon = (ImageView) findViewById(R.id.search_image);
        mLoadingBar = new ProgressDialog(this);

    }

    private void showErrorDialog(VolleyError error) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Oops")
                .setMessage(error.toString())
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}