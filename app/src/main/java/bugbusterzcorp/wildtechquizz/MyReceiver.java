package bugbusterzcorp.wildtechquizz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by apprenti on 10/04/17.
 */


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(intent.ACTION_BOOT_COMPLETED)){
            context.startService(new Intent(context, MyService.class));

        }
    }
}
