package pl.wsb.students.android.introduction.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pl.wsb.students.android.introduction.app.fragments.FirstFragment;
import pl.wsb.students.android.introduction.app.fragments.SecondFragment;
import pl.wsb.students.android.introduction.app.service.ExampleService;

public class MainActivity extends AppCompatActivity {
    private final static String CHANNEL_ID = "wsb_course_test_channel_0";
    private final static CharSequence CHANNEL_NAME = "wsb_course_test_channel_0";
    private final static String CHANNEL_DESCRIPTION = "WSB course test channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startService(View view) {
        startService(
                new Intent(
                        this,
                        ExampleService.class
                )
        );
    }
    public void stopService(View view) {
        stopService(
                new Intent(
                        this,
                        ExampleService.class
                )
        );
    }
    public void sendNotification(View view) {
        EditText notificationTitle = findViewById(R.id.edtNotificationTitle);
        EditText notificationMessage = findViewById(R.id.edtNotificationMessage);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = createNotificationChannel();
            if (channel != null) {
                notificationManager.createNotificationChannel(channel);
                notify = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                        .setContentTitle(
                                notificationTitle.getText().toString()
                        )
                        .setContentText(
                                notificationMessage.getText().toString()
                        )
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .build();
            } //if
        } else { //if
            notify = new Notification.Builder(getApplicationContext())
                    .setContentTitle(
                            notificationTitle.getText().toString()
                    )
                    .setContentText(
                            notificationMessage.getText().toString()
                    )
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build();
        } //else
        notificationManager.notify(0, notify);
    }
    private NotificationChannel createNotificationChannel() {
        int importance = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_HIGH;
        }
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200,
                    400});
            channel.setShowBadge(false);
        }
        return channel;
    }
}