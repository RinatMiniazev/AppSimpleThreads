package com.minyazev.appsimplethreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.minyazev.appsimplethreads.TimerThread;
public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Handler handler = new Handler();
    private TimerThread timerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button resetButton = findViewById(R.id.resetButton);

        timerThread = new TimerThread(handler, timerTextView);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerThread.isAlive()) {
                    timerThread = new TimerThread(handler, timerTextView);
                    timerThread.start();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerThread.stopTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerThread.resetTimer();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerThread.stopTimer();
    }
}


/* 2222
public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Handler handler = new Handler();
    private TimerThread timerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        timerThread = new TimerThread(handler, timerTextView);
        timerThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerThread.stopTimer();
    }
}

*/

/* 1111
public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView timerTextView = findViewById(R.id.timerTextView);

        // Запускаем дочерний поток
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Задержка 1 секунда
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter++;
                    // Обновляем UI с помощью метода post
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            timerTextView.setText(String.valueOf(counter));
                        }
                    });
                }
            }
        }).start();
    }
}
*/