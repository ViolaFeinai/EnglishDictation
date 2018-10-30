package com.example.viola.englishdictation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERID = "UserID";
    String testcoo="";
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        testcoo = pref.getString(PREF_USERID,"");
        if (testcoo!=null&&!testcoo.equals("NoUser")&&!testcoo.equals("No Connected Service Found")&&!testcoo.equals(""))
        {
            Log.i("new111111111", testcoo);
            UserID= testcoo;
            setContentView(R.layout.content_main);
       }
        else {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }

    public void goTest(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "goTest worked" +UserID,Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, Test.class);
        startActivity(intent);
    }


}