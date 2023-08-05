package com.example.ph25533_asm.UNTIL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckConnection {
    public static boolean HaveNetworkConnection(Context context)
    {
        boolean wifi=false,mobile=false;
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        for(NetworkInfo info:networkInfos)
        {
            if(info.getTypeName().equals("WIFI"))
                if(info.isConnected())
                    wifi=true;
            if(info.getTypeName().equals("MOBILE"))
                if(info.isConnected())
                    wifi=true;

        }
        return wifi||mobile;
    }
    public static void ShowToastLong(Context context,String tb)
    {
        Toast.makeText(context,tb,Toast.LENGTH_LONG).show();
    }
}
