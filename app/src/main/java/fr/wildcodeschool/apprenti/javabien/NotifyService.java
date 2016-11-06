package fr.wildcodeschool.apprenti.javabien;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class NotifyService extends Service {

    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        // notification Service
        this.mManager = (NotificationManager) getApplicationContext()
                .getSystemService(
                        this.getApplicationContext().NOTIFICATION_SERVICE);

        //customize notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentTitle("Javabien")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentText("Joue avec moi !")
                .setSmallIcon(R.drawable.iconejorange);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                1,
                new Intent(getApplicationContext(), MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        // launch notification
        this.mManager.notify(0, builder.build());
        return START_STICKY;
    }

}
