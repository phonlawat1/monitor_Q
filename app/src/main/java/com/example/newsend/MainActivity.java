package com.example.newsend;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    TextView tvMessages;
    String SERVER_IP = "192.168.1.89";
    int SERVER_PORT = 5557;
    int noOfSecond = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMessages = findViewById(R.id.tvMessages);
        Thread1 = new Thread(new Thread1());
        Thread1.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Thread3()).start();
            }
        },noOfSecond * 1000);
    }

    private PrintWriter output;
    private BufferedReader input;

    class Thread1 implements Runnable {
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected\n");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    String dataString = message.substring(11, message.length() - 5);

                    //JSONObject result = new JSONObject(dataString);
                    //String counterNum= result.getString("counter");

                    //Log.d("Result: ", counterNum);
                    try {
                        JSONObject jsonObject = new JSONObject(dataString);
                        final String count = jsonObject.getString("counter");
                        final String queue = jsonObject.getString("qnum");

                        if (dataString != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    tvMessages.append("server: " + "Counter -> " + count + ",  qnum -> " + queue + "\n");
                                }
                            });
                        } else {
                            Thread1 = new Thread(new Thread1());
                            Thread1.start();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class Thread3 implements Runnable {
        @Override
        public void run() {
            try {
                final String message = "[{\"type\":\"monitor\"}]";
                output.write(message);
                output.flush();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.append("client: " + message + "\n");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}