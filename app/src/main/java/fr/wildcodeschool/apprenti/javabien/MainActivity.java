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
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class MainActivity extends Activity {
    // declare  DBHandler
    private DBHandler mDBHelper;
    // arraylist checking quizz progress
    private ArrayList<Exercice> quizzPass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // set calendar and schedule notification
        this.setNotifCalendar(this.getApplicationContext());

        // create  DBHandler
        this.mDBHelper = new DBHandler(this);

        // check if database exist
        File database = this.getApplicationContext().getDatabasePath(Constante.DBNAME);
        if (!database.exists()) {
            this.mDBHelper.getReadableDatabase();
            // and copy database with method
            if (!this.copyDatabase(this)) {
                Toast.makeText(this, "error cannot copy Database", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //first level button
        Button debouton = (Button) findViewById(R.id.button);
        debouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListExoActivity.class);
                //send list of level exercices with getListNiveau of DBHandler class
                intent.putExtra(Constante.SERIALIZED_LIST, mDBHelper.getListNiveau("1"));
                startActivity(intent);


            }
        });
        //help button
        Button boutinfo = (Button) findViewById(R.id.boutonfo);
        boutinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intentinfo = new Intent(MainActivity.this, InfoPageActivity.class);
                startActivity(intentinfo);

            }

        });


    }

    //copying database from assets to database folder
    private boolean copyDatabase(Context context) {
        try {
            InputStream inpuStream = context.getAssets().open(Constante.DBNAME);
            // set target of output
            OutputStream outputStream = new FileOutputStream(getDatabasePath(Constante.DBNAME));
            // buffer
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inpuStream.read(buff)) > 0) {
                //writing
                outputStream.write(buff, 0, length);

            }
            //clear buffer
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
    // set calendar and schedule notification 7 days later with alarmclockManager
    private void setNotifCalendar(Context context) {

        //get current date

        Calendar calendar = Calendar.getInstance();
        calendar.getTimeInMillis();

        // add 6 days
        calendar.add(Calendar.DATE, 6);
        // intent with receiver
        Intent intent = new Intent(MainActivity.this, SchedulerReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                0, intent, 0);
        // init alarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // launch alarmManager
        if (Build.VERSION.SDK_INT < 23) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    pendingIntent);
        } else {
            Intent i = new Intent(context, SchedulerReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
            Intent i2 = new Intent(context, NotifyService.class);
            PendingIntent pi2 = PendingIntent.getActivity(context, 0, i2, 0);

            AlarmManager.AlarmClockInfo ac =
                    new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(),
                            pi);

            alarmManager.setAlarmClock(ac, pi2);
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }


    }

    // refresh
    public void onStart() {
        super.onStart();
       buttonActivation();
    }

    // refresh
    public void onResume() {
        super.onResume();
      buttonActivation();
    }
    // method to check if previous level quizz is passed and activate button if it's true
    private void buttonActivation(){
        //check if previous quizz is passed and activate button
        quizzPass.addAll(this.mDBHelper.getQuizzPass());
        if (quizzPass.get(0).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button2);
            deux.setBackgroundResource(R.drawable.jf3);
            deux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ListExoActivity.class);
                    intent.putExtra(Constante.SERIALIZED_LIST, mDBHelper.getListNiveau("2"));
                    startActivity(intent);
                }
            });
        }
        if (quizzPass.get(1).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button3);
            deux.setBackgroundResource(R.drawable.jf4);
            deux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ListExoActivity.class);
                    intent.putExtra(Constante.SERIALIZED_LIST, mDBHelper.getListNiveau("3"));
                    startActivity(intent);
                }
            });
        }

        if (quizzPass.get(2).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button4);
            deux.setBackgroundResource(R.drawable.jf5);
            deux.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ListExoActivity.class);
                    intent.putExtra(Constante.SERIALIZED_LIST, mDBHelper.getListNiveau("4"));
                    startActivity(intent);
                }
            });
        }
    }
}
