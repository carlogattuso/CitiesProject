package com.example.citiesproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String [] DUMMY_CREDENTIALS = new String[]{"foo@example.com:hello","bar@example.com:world"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void doLogin(View view) {

        new login(this).execute();
    }

    private class login extends AsyncTask<String, Void, String> {
        Context context;

        private login(Context context) {
            this.context = context;
        }
        @Override
        protected String doInBackground(String... urls) {

            final EditText username = (EditText) findViewById(R.id.username);
            final EditText password = (EditText) findViewById(R.id.password);

            return username.getText().toString()+":"+password.getText().toString();
        }

        @Override
        protected void onPostExecute(String result) {
            if(DUMMY_CREDENTIALS[0].equals(result)||DUMMY_CREDENTIALS[1].equals(result)){
                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("registered",true);
                editor.putString("username",result.split(":")[0]);
                editor.putString("password",result.split(":")[1]);
                editor.apply();

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "login error",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
