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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


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

        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, newURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d ("Avg", response);
                        mLoadingBar.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mLoadingBar.dismiss();
                showErrorDialog();

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void initialization() {

        searchName = (EditText) findViewById(R.id.HeroSearch);
        SearchIcon = (ImageView) findViewById(R.id.search_image);
        mLoadingBar = new ProgressDialog(this);

    }

    private void showErrorDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Oops")
                .setMessage("Something Went Wrong. Please try Again")
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}