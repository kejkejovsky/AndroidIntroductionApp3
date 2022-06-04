package pl.wsb.students.android.introduction.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class ExampleService extends Service {
    private boolean looping;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        Toast.makeText(this, "Utworzenie usługi...", Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Rozpoczęcie uruchamiania usługi...",
                Toast.LENGTH_LONG).show();
        this.looping = true;
        Runnable runnable = new Runnable() {
            public void run() {
                int retryCounter = 0;
                while ((retryCounter <= 999) && (looping)) {
                    retryCounter++;
                    synchronized (this) {
                        try {
                            wait(2000);
                        } catch (Exception e) {
//handle exception
                        }
                    }
                    int finalRetryCounter = retryCounter;
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(
                                ExampleService.this,
                                String.format(
                                        " Usługa działa - iteracja %s...",
                                        finalRetryCounter
                                ),
                                Toast.LENGTH_SHORT
                        ).show();
                    });
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Toast.makeText(this, "Zakończenie uruchamiania usługi...",
                Toast.LENGTH_LONG).show();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.looping = false;
        Toast.makeText(this, "Zatrzymanie usługi...", Toast.LENGTH_LONG).show();
    }
}
