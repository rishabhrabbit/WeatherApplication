package com.example.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Button btn;
        btn = findViewById(R.id.enter);
        name = findViewById(R.id.username);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });


    }

    private void startNewActivity() {
        if(name.getText().toString().length() > 0) {
            SharedPreferences sharedPreferences = getSharedPreferences(sfName.sharedPreferencename, MODE_PRIVATE);
            SharedPreferences.Editor username = sharedPreferences.edit();
            username.putString("name", name.getText().toString());
            username.apply();
        }
        else {
            SharedPreferences sharedPreferences = getSharedPreferences(sfName.sharedPreferencename, MODE_PRIVATE);
            SharedPreferences.Editor username = sharedPreferences.edit();
            username.putString("name", "User");
            username.apply();
        }
        Intent intent = new Intent(this, LoadFragment.class);
        startActivity(intent);
    }

}
