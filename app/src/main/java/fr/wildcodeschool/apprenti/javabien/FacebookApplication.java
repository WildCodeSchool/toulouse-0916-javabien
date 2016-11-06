package fr.wildcodeschool.apprenti.javabien;

import android.app.Application;
import com.facebook.FacebookSdk;

public class FacebookApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        // initialise facebook SDK
       FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
