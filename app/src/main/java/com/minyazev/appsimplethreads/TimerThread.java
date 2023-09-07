package com.minyazev.appsimplethreads;

import android.os.Handler;
import android.widget.TextView;

public class TimerThread extends Thread {
    private Handler handler;
    private TextView timerTextView;
    private volatile boolean running = false;
    private int elapsedSeconds = 0;

    public TimerThread(Handler handler, TextView timerTextView) {
        this.handler = handler;
        this.timerTextView = timerTextView;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            elapsedSeconds++;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    timerTextView.setText(String.valueOf(elapsedSeconds));
                }
            });
        }
    }

    public void stopTimer() {
        running = false;
    }

    public void resetTimer() {
        elapsedSeconds = 0;
        handler.post(new Runnable() {
            @Override
            public void run() {
                timerTextView.setText(String.valueOf(elapsedSeconds));
            }
        });
    }
}

/*
public class TimerThread extends Thread  implements Runnable {
    private Handler handler;
    private TextView timerTextView;
    private boolean running = true;
    private int elapsedSeconds = 0;

    public TimerThread(Handler handler, TextView timerTextView) {
        this.handler = handler;
        this.timerTextView = timerTextView;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elapsedSeconds++;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    timerTextView.setText(String.valueOf(elapsedSeconds));
                }
            });
        }
    }

    public void stopTimer() {
        running = false;
    }
}
*/