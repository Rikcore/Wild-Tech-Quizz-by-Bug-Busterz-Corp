package bugbusterzcorp.wildtechquizz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiverForNotif extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent();
        myIntent.setClass(context, MyService.class);

        if (intent.getAction().equals(intent.ACTION_BOOT_COMPLETED) || intent.getAction().equals(intent.ACTION_PACKAGE_INSTALL)){
            context.startService(myIntent);
            Log.d("Test receiver", "Receiver works");
        }
        else{
            Log.d("Test receiver", "Receiver fails");
        }
    }
}
