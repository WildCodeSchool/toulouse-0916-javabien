package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class MainActivity extends Activity {
   // private List<Contenant> mContenantList;
    private DBHandler mDBHelper;
    private  ArrayList<Contenant> quizzPass = new ArrayList<Contenant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// programation des spamnotifs
notificateur(getApplicationContext());

        // création base de donnée
        mDBHelper = new DBHandler(this);

        // check database
        File database = getApplicationContext().getDatabasePath(DBHandler.DBNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();

            //copy
            if (copyDatabase(this)) {
                //  Toast.makeText(this,"elle existe déjà!",Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Button debouton = (Button) findViewById(R.id.button);
        debouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("listeExercices", mDBHelper.getListNiveau("1"));
                startActivity(intent);


            }
        });
        Button boutinfo = (Button) findViewById(R.id.boutonfo);
        boutinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intentinfo = new Intent(MainActivity.this, InfoPage.class);
                startActivity(intentinfo);

            }

        });


    }
    private boolean copyDatabase(Context context){
        try{

            InputStream inpuStream = context.getAssets().open(DBHandler.DBNAME);
            String outFileName = DBHandler.DBLOCATION+DBHandler.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length =0;
            while((length=inpuStream.read(buff))>0){
                outputStream.write(buff, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }




    }

private void notificateur(Context context){
    // lancement de la programmation du spam notification

    //recupération de la date actuelle
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.getTimeInMillis();

    // ajout de 6 jours
    calendar.add(Calendar.DATE, 5);
// creation de l'intent qui lance le service de notification
    Intent intent = new Intent(MainActivity.this,SchedulerService.class);
// creation du Pendingintent pour l'alarmManager avec l'intent qui lance la notification
    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
            0, intent, 0);
// initialisation de l'alarmManager
    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
/*
// lancement de l'alarmManager
   */
 if(Build.VERSION.SDK_INT < 23) {
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                pendingIntent);
    }else {

        Intent i = new Intent(context, SchedulerService.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        Intent i2 = new Intent(context, Notifyme.class);
        PendingIntent pi2 = PendingIntent.getActivity(context, 0, i2, 0);

        AlarmManager.AlarmClockInfo ac =
                new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(),
                        pi2);

        alarmManager.setAlarmClock(ac,pendingIntent);
     alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }/*

    // Ajout d'un spamNotif ultérieur
    //ajout de 6 jours au calendrier précédent
  /*  calendar.add(Calendar.DAY_OF_MONTH, 6);
    //et rebelote
    AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
    alarmManager2.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
            pendingIntent);

*/
}

public void onStart(){
    super.onStart();
    quizzPass.addAll(mDBHelper.getQuizzPass());
    if(quizzPass.get(0).getAvancement()==1){
        Button deux = (Button)findViewById(R.id.button2);
        deux.setBackgroundResource(R.drawable.jf3);
        deux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("listeExercices", mDBHelper.getListNiveau("2"));
                startActivity(intent);
            }
        });
    }
   if(quizzPass.get(1).getAvancement()==1){
        Button deux = (Button)findViewById(R.id.button3);
        deux.setBackgroundResource(R.drawable.jf4);
        deux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("listeExercices", mDBHelper.getListNiveau("3"));
                startActivity(intent);
            }
        });
    }

    if(quizzPass.get(2).getAvancement()==1){
        Button deux = (Button)findViewById(R.id.button4);
        deux.setBackgroundResource(R.drawable.jf5);
        deux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("listeExercices", mDBHelper.getListNiveau("4"));
                startActivity(intent);
            }
        });
    }
}
public void onResume(){
    super.onResume();
    quizzPass.addAll(mDBHelper.getQuizzPass());
    if(quizzPass.get(0).getAvancement()==1){
        Button deux = (Button)findViewById(R.id.button2);
        deux.setBackgroundResource(R.drawable.jf3);
        deux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("listeExercices", mDBHelper.getListNiveau("2"));
                startActivity(intent);
            }
        });
    }
   if(quizzPass.get(1).getAvancement()==1){
        Button deux = (Button)findViewById(R.id.button3);
        deux.setBackgroundResource(R.drawable.jf4);
        deux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("listeExercices", mDBHelper.getListNiveau("3"));
                startActivity(intent);
            }
        });
    }

    if(quizzPass.get(2).getAvancement()==1){
        Button deux = (Button)findViewById(R.id.button4);
        deux.setBackgroundResource(R.drawable.jf5);
        deux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("listeExercices", mDBHelper.getListNiveau("4"));
                startActivity(intent);
            }
        });
    }
}
}
