package fr.wildcodeschool.apprenti.javabien;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import java.util.Calendar;
import java.util.Date;

public class SchedulerService extends Service {
    public SchedulerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);


            Intent intent1 = new Intent(getApplicationContext(),Notifyme.class);
            startService(intent1);


return START_STICKY;
    }
}
