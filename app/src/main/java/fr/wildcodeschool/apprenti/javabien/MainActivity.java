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
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class MainActivity extends Activity {
    // déclaration du DBHandler
    private DBHandler mDBHelper;
    // arraylist pour recupérer l'avancement du quizz
    private ArrayList<Contenant> quizzPass = new ArrayList<Contenant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // programation des spamnotifs(cf méthode en bas)
        notificateur(getApplicationContext());

        // création base de donnée
        mDBHelper = new DBHandler(this);

        // vérification existence database
        File database = getApplicationContext().getDatabasePath(DBHandler.DBNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();

            //sinon copie
            if (copyDatabase(this)) {

            } else {
                //en cas d'erreur de copie
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //bouton premier niveau
        Button debouton = (Button) findViewById(R.id.button);
        debouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //envoie de la liste du premier niveau avec la méthode getListNiveau de la classe DBHandler
                intent.putExtra("listeExercices", mDBHelper.getListNiveau("1"));
                startActivity(intent);


            }
        });
        //bouton pour l'info
        Button boutinfo = (Button) findViewById(R.id.boutonfo);
        boutinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intentinfo = new Intent(MainActivity.this, InfoPage.class);
                startActivity(intentinfo);

            }

        });


    }

    //méthode qui copie la base de donnée
    private boolean copyDatabase(Context context) {
        try {
            //InputStream va lire des données
            InputStream inpuStream = context.getAssets().open(DBHandler.DBNAME);
            //outFileName lieu où est le fichier de base de données
            String outFileName = DBHandler.DBLOCATION + DBHandler.DBNAME;
            //Output va écrire dans le fichier de bdd de l'application
            OutputStream outputStream = new FileOutputStream(outFileName);
            //cache pour la lecture et écriture de données
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inpuStream.read(buff)) > 0) {
                //écrire dans la bdd
                outputStream.write(buff, 0, length);

            }
            //vide le buffer
            outputStream.flush();
            //ferme le flux de données
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;

        }
        //en cas d'erreur:
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    private void notificateur(Context context) {
        // lancement de la programmation du spam notification(alerte de l'appli au bout de 7 jours d'inactivité)

        //recupération de la date actuelle, crée le calendrier

        Calendar calendar = Calendar.getInstance();
        calendar.getTimeInMillis();

        // ajout de 6 jours
        calendar.add(Calendar.DATE, 6);
        // creation de l'intent qui lance le service de notification
        Intent intent = new Intent(MainActivity.this, SchedulerService.class);
        // creation du Pendingintent pour l'alarmManager avec l'intent qui lance la notification
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                0, intent, 0);
        // initialisation de l'alarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // lancement de l'alarmManager
        //si la version d'android est inférieur à 23
        if (Build.VERSION.SDK_INT < 23) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    pendingIntent);
        } else {
            //sinon création des intent pour configurer l'alarmManager > 23
            Intent i = new Intent(context, SchedulerService.class);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
            Intent i2 = new Intent(context, Notifyme.class);
            PendingIntent pi2 = PendingIntent.getActivity(context, 0, i2, 0);

            AlarmManager.AlarmClockInfo ac =
                    new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(),
                            pi);

            alarmManager.setAlarmClock(ac, pi2);
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }


    }

    //au démarrage, vérifie si le quizz des niveaux précédent a été passé et active les boutons des niveaux suivants
    // en conséquence
    public void onStart() {
        super.onStart();
        //récupère dans la bdd la liste des sauvegardes de l'avancement des quizz
        quizzPass.addAll(mDBHelper.getQuizzPass());
        if (quizzPass.get(0).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button2);
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
        if (quizzPass.get(1).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button3);
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

        if (quizzPass.get(2).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button4);
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

    //au retour sur cette activité, vérifie si le quizz des niveaux précédent a été passé et active les boutons des niveaux suivants
    // en conséquence
    public void onResume() {
        super.onResume();
        quizzPass.addAll(mDBHelper.getQuizzPass());
        if (quizzPass.get(0).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button2);
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
        if (quizzPass.get(1).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button3);
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

        if (quizzPass.get(2).getAvancement() == 1) {
            Button deux = (Button) findViewById(R.id.button4);
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
