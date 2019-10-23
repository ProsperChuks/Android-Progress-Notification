package com.example.progressnotification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    int id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.btnShow);
        Button b2 = (Button) findViewById(R.id.btnShow2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(MainActivity.this);
                mBuilder.setContentTitle("Determinate File Download")
                        .setContentText("Download in progress")
                        .setSmallIcon(R.drawable.ic_launcher_foreground);
                // Start a the operation in a background thread
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                int incr;
                                // Do the "lengthy" operation 20 times
                                for (incr = 0; incr <= 100; incr+=5) {
                                    // Sets the progress indicator to a max value, the current completion percentage and "determinate" state
                                    mBuilder.setProgress(100, incr, false);
                                    // Displays the progress bar for the first time.
                                    mNotifyManager.notify(id, mBuilder.build());
                                    // Sleeps the thread, simulating an operation
                                    try {
                                        // Sleep for 1 second
                                        Thread.sleep(1*1000);
                                    } catch (InterruptedException e) {
                                        Log.d("TAG", "sleep failure");
                                    }
                                }
                                // When the loop is finished, updates the notification
                                mBuilder.setContentText("Download completed")
                                        // Removes the progress bar
                                        .setProgress(0,0,false);
                                mNotifyManager.notify(id, mBuilder.build());
                            }
                        }
                        // Starts the thread by calling the run() method in its Runnable
                ).start();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(MainActivity.this);
                mBuilder.setContentTitle("File Download")
                        .setContentText("Download in progress")
                        .setSmallIcon(R.drawable.ic_launcher_foreground);
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                int incr;
                                // Do the "lengthy" operation 20 times
                                for (incr = 0; incr <= 100; incr+=5) {
                                    // Sets the progress indicator to a max value, the current completion percentage and "determinate" state
                                    mBuilder.setProgress(100, incr, true);
                                    // Displays the progress bar for the first time.
                                    mNotifyManager.notify(id, mBuilder.build());
                                    // Sleeps the thread, simulating an operation
                                    try {
                                        // Sleep for 1 second
                                        Thread.sleep(1*1000);
                                    } catch (InterruptedException e) {
                                        Log.d("TAG", "sleep failure");
                                    }
                                }
                                // When the loop is finished, updates the notification
                                mBuilder.setContentText("Download completed")
                                        // Removes the progress bar
                                        .setProgress(0,0,false);
                                mNotifyManager.notify(id, mBuilder.build());
                            }
                        }
                        // Starts the thread by calling the run() method in its Runnable
                ).start();
            }
        });
    }
}