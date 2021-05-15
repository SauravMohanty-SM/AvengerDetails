package com.example.avengerheros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText searchName;
    ImageView SearchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialization();



        SearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String HeroName = searchName.getText().toString();

                Intent intent = new Intent(MainActivity.this, AfterSearch.class);
                intent.putExtra("url", HeroName);
                startActivity(intent);

            }
        });
    }
    private void initialization() {

        searchName = (EditText) findViewById(R.id.HeroSearch);
        SearchIcon = (ImageView) findViewById(R.id.search_image);

    }
}