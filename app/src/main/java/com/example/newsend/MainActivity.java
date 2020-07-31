package com.example.newsend;

import android.annotation.SuppressLint;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
    TextView tvMessagesq;
    TextView tvMessages1;
    TextView tvMessages2;
    TextView tvMessages3;
    TextView tvMessages4;
    TextView tvMessages5;
    String SERVER_IP = "192.168.1.59";
    int SERVER_PORT = 5557;
    int noOfSecond = 1;
    String[] CountArray = new String[6];
    String[] QueArray = new String[6];
    String typeA = "A";
    String typeB = "B";
    String typeC = "C";
    int i = 0;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMessagesq = findViewById(R.id.tvMessagesq);
        tvMessages1 = findViewById(R.id.tvMessages1);
        tvMessages2 = findViewById(R.id.tvMessages2);
        tvMessages3 = findViewById(R.id.tvMessages3);
        tvMessages4 = findViewById(R.id.tvMessages4);
        tvMessages5 = findViewById(R.id.tvMessages5);
        Thread1 = new Thread(new Thread1());
        Thread1.start();

        String sound_path = "";

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
                        tvMessagesq.setText("");
                    }
                });
                new Thread(new Thread2()).start();  /*change thread type work A, B and C*/
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

                    try {
                        JSONObject jsonObject = new JSONObject(dataString);
                        final String count = jsonObject.getString("counter");
                        final String queue = jsonObject.getString("qnum");
                        final String check = queue.substring(0,1);
                        if (dataString != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (check.equals(typeA)) {
                                        CountArray[i] = count;
                                        QueArray[i] = queue;
                                        switch (i) {
                                            case 0:
                                                tvMessagesq.setText("");
                                                tvMessagesq.append(QueArray[0] + "                                 " + CountArray[0]);
                                                break;
                                            case 1:
                                                tvMessages1.setText("");
                                                tvMessages1.append(QueArray[1] + "                                 " + CountArray[1]);
                                                break;
                                            case 2:
                                                tvMessages2.setText("");
                                                tvMessages2.append(QueArray[2] + "                                 " + CountArray[2]);
                                                break;
                                            case 3:
                                                tvMessages3.setText("");
                                                tvMessages3.append(QueArray[3] + "                                 " + CountArray[3]);
                                                break;
                                            case 4:
                                                tvMessages4.setText("");
                                                tvMessages4.append(QueArray[4] + "                                 " + CountArray[4]);
                                                break;
                                            case 5:
                                                tvMessages5.setText("");
                                                tvMessages5.append(QueArray[5] + "                                 " + CountArray[5]);
                                                break;
                                        }
                                        i++;
                                        if (i == 6) {
                                            i -= 6;
                                        }
//                                        tvMessagesq.append(QueArray[0] + "               " + CountArray[0]);
                                    }
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
            while (true) {
                try {
                    final String message = input.readLine();
                    String dataString = message.substring(11, message.length() - 5);

                    try {
                        JSONObject jsonObject = new JSONObject(dataString);
                        final String count = jsonObject.getString("counter");
                        final String queue = jsonObject.getString("qnum");
                        final String check = queue.substring(0,1);
                        if (dataString != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (check.equals(typeB)) {
                                        CountArray[i] = count;
                                        QueArray[i] = queue;
                                        switch (i) {
                                            case 0:
                                                tvMessagesq.setText("");
                                                tvMessagesq.append(QueArray[0] + "                                 " + CountArray[0]);
                                                break;
                                            case 1:
                                                tvMessages1.setText("");
                                                tvMessages1.append(QueArray[1] + "                                 " + CountArray[1]);
                                                break;
                                            case 2:
                                                tvMessages2.setText("");
                                                tvMessages2.append(QueArray[2] + "                                 " + CountArray[2]);
                                                break;
                                            case 3:
                                                tvMessages3.setText("");
                                                tvMessages3.append(QueArray[3] + "                                 " + CountArray[3]);
                                                break;
                                            case 4:
                                                tvMessages4.setText("");
                                                tvMessages4.append(QueArray[4] + "                                 " + CountArray[4]);
                                                break;
                                            case 5:
                                                tvMessages5.setText("");
                                                tvMessages5.append(QueArray[5] + "                                 " + CountArray[5]);
                                                break;
                                        }
                                        i++;
                                        if (i == 6) {
                                            i -= 6;
                                        }
//                                        tvMessagesq.append(QueArray[0] + "               " + CountArray[0]);
                                    }
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

    class Thread4 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    String dataString = message.substring(11, message.length() - 5);

                    try {
                        JSONObject jsonObject = new JSONObject(dataString);
                        final String count = jsonObject.getString("counter");
                        final String queue = jsonObject.getString("qnum");
                        final String check = queue.substring(0,1);
                        if (dataString != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (check.equals(typeC)) {
                                        CountArray[i] = count;
                                        QueArray[i] = queue;
                                        switch (i) {
                                            case 0:
                                                tvMessagesq.setText("");
                                                tvMessagesq.append(QueArray[0] + "                                 " + CountArray[0]);
                                                break;
                                            case 1:
                                                tvMessages1.setText("");
                                                tvMessages1.append(QueArray[1] + "                                 " + CountArray[1]);
                                                break;
                                            case 2:
                                                tvMessages2.setText("");
                                                tvMessages2.append(QueArray[2] + "                                 " + CountArray[2]);
                                                break;
                                            case 3:
                                                tvMessages3.setText("");
                                                tvMessages3.append(QueArray[3] + "                                 " + CountArray[3]);
                                                break;
                                            case 4:
                                                tvMessages4.setText("");
                                                tvMessages4.append(QueArray[4] + "                                 " + CountArray[4]);
                                                break;
                                            case 5:
                                                tvMessages5.setText("");
                                                tvMessages5.append(QueArray[5] + "                                 " + CountArray[5]);
                                                break;
                                        }
                                        i++;
                                        if (i == 6) {
                                            i -= 6;
                                        }
//                                        tvMessagesq.append(QueArray[0] + "               " + CountArray[0]);
                                    }
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

    class Thread5 implements Runnable {
        @Override
        public void run() {
            try {
                final String message = "[{\"type\":\"monitor\"}]";
                output.write(message);
                output.flush();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //tvMessagesq.append("client: " + message + "\n");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}