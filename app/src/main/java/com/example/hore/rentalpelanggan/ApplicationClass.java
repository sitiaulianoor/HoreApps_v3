package com.example.hore.rentalpelanggan;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.onesignal.OneSignal;

public class ApplicationClass extends Application {
    FirebaseAuth mAuth;

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
