package fr.wildcodeschool.apprenti.javabien;

import android.app.Application;
import com.facebook.FacebookSdk;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

/**
 * Created by apprenti on 18/10/16.
 */

public class Facebook extends Application{



    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.


    @Override
    public void onCreate(){
        super.onCreate();
       // TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
       // Fabric.with(this, new Twitter(authConfig));

        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
