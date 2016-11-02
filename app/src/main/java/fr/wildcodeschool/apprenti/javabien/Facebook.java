package fr.wildcodeschool.apprenti.javabien;

import android.app.Application;
import com.facebook.FacebookSdk;

public class Facebook extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        // initialise le SDK de facebook
       FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
