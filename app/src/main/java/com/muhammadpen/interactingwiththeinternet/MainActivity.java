package com.muhammadpen.interactingwiththeinternet;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static class DownloadingSource extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            String result = "";

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream dataFromWebpage = httpURLConnection.getInputStream();
                InputStreamReader readerForInputStream = new InputStreamReader(dataFromWebpage);

                int reader = readerForInputStream.read();

                while (reader != -1) {

                    char dataFromReader = (char) reader;

                    result += dataFromReader;

                    reader = readerForInputStream.read();
                }

                return result;

            }

            catch (java.io.IOException e) {

                e.printStackTrace();

                return "Failed to download the Source.";

            }

        }
    }

    DownloadingSource downloadSource;

        public void buttonPressed(View view){


        downloadSource = new DownloadingSource();

            try {
                String resultOfDownload = downloadSource.execute("https://manoverboard.com").get();

                System.out.println(resultOfDownload);

            } catch (InterruptedException e) {

                e.printStackTrace();
            } catch (ExecutionException e) {

                e.printStackTrace();
            }

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}



