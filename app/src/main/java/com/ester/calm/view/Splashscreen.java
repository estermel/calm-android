package com.ester.calm.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ester.calm.R;

public class Splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        startHeavyProcessing();
    }

    private void startHeavyProcessing() {
        new LongOperation().execute("");
    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params){
            for (int i=0; i<5; i++){
                try{
                    Thread.sleep(SPLASH_TIME_OUT);
                } catch (InterruptedException e){
                    Thread.interrupted();
                }
            }
            return "something";
        }

        @Override
        protected void onPostExecute(String result){
            Intent i = new Intent(Splashscreen.this, OrderActivity.class);
            startActivity(i);
            finish();
        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onProgressUpdate(Void... values){

        }
    }
}
