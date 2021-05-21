package com.example.gamelink;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import com.example.gamelink.utils.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowNetworkInfo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(AndroidJUnit4.class)
@Config(manifest= Config.NONE)
public class InternetTest {

    private ShadowNetworkInfo shadowOfActiveNetworkInfo;
    private Context context;


    @Test
    public void checkIsInternetAvailableFalse_NoInternet() {
        this.setUpContextInternet();
        shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.DISCONNECTED);
        assertFalse(utils.isInternetAvailable(context));
    }

    @Test
    public void checkIsInternetAvailableTrue_InternetOn() {
        this.setUpContextInternet();
        shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.CONNECTED);
        assertTrue(utils.isInternetAvailable(ApplicationProvider.getApplicationContext()));
    }

    private void setUpContextInternet(){
        context =  ApplicationProvider.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            shadowOfActiveNetworkInfo = shadowOf(connectivityManager.getActiveNetworkInfo());
        }
    }
}