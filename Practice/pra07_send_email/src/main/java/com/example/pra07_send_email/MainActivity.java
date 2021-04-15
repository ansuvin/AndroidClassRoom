package com.example.pra07_send_email;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectAsyncTask newTask = new ConnectAsyncTask();
                newTask.execute();
            }
        });
    }

    Connection conn;

    private class ConnectAsyncTask extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        boolean exists = false;
        String uname, pwd;
        String email;

        @Override
        protected Void doInBackground(Void... voids) {

            //if a record was found send account information to email address
            exists = true;
            uname = "dks";
            pwd = "1234";
            email ="s19032@gsm.hs.kr";
            try {
                GMailSender sender = new GMailSender("s19032@gsm.hs.kr", "ece1778h1s");
                sender.sendMail("eXperience Account Information",
                        "Your username is: " + uname + ", and your password is: " + pwd,
                        "s19032@gsm.hs.kr",
                        email);
                Log.e("성공이야","보냈어");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                //notify user of incoming email with account details
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Success");
                alertDialog.setMessage("An email has been sent to the address specified with account details.");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        //go back to StartActivity
                        onBackPressed();
                    }
                });
                alertDialog.show();
                Log.e("성공이야","보냈어?");


        }
    }
}