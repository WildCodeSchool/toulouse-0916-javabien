package fr.wildcodeschool.apprenti.javabien;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.util.Calendar;
import java.util.Date;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SchedulerService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {



            Intent intent1 = new Intent(getApplicationContext(),Notifyme.class);
            context.startService(intent1);



    }
}
