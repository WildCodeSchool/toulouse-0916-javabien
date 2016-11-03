package fr.wildcodeschool.apprenti.javabien;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import static com.facebook.FacebookSdk.getApplicationContext;

public class SchedulerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        // lancement du service NotifyService à la réception du signal de l'alarmManager
            Intent intent1 = new Intent(getApplicationContext(),NotifyService.class);
            context.startService(intent1);
    }
}
