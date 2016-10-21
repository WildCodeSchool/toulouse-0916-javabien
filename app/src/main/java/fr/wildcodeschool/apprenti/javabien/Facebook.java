package fr.wildcodeschool.apprenti.javabien;

import android.app.Application;
import com.facebook.FacebookSdk;
/**
 * Created by apprenti on 18/10/16.
 */

public class Facebook extends Application{
    @Override
    public void onCreate(){
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
