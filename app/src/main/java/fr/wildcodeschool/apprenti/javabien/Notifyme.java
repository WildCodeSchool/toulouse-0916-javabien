package fr.wildcodeschool.apprenti.javabien;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Notifyme extends Service {

    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags, startId);

                  // Getting Notification Service
                    mManager = (NotificationManager) getApplicationContext()
                            .getSystemService(
                                    getApplicationContext().NOTIFICATION_SERVICE);
		/*
		 * When the user taps the notification we have to show the Home Screen
		 * of our App, this job can be done with the help of the following
		 * Intent.
		 */
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);

                    //personalisation de la notification avec le builder
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                    builder.setContentTitle("Javabien")
                            .setAutoCancel(true)
                            .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                            .setColor(getResources().getColor(R.color.colorAccent))
                            .setContentText("Joue avec moi !")
                            .setSmallIcon(R.drawable.iconej);

                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                            1,
                            new Intent(getApplicationContext(), MainActivity.class),
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
//        builder.setDeleteIntent(NotificationReceiver.getDeleteIntent(this));

                    // lancement de la notification
                    final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(0, builder.build());

                    //stopForeground(true);

                return START_STICKY;
                }



    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
