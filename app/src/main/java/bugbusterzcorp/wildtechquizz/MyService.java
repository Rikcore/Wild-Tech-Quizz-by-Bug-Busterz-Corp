package bugbusterzcorp.wildtechquizz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyService extends Service {

    public static final String INTENT_DISPLAY_NOTIF = "DemoService.DisplayNotif";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null){
            if (intent.getAction().equals(INTENT_DISPLAY_NOTIF)){
                displayNotification();
            }

        }
        return Service.START_STICKY;
    }

    private void displayNotification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Un nouveau quizz est disponible !")
                .setContentText(user.getDisplayName()+" a créé un quizz")
                .setSmallIcon(R.drawable.edward)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0))
                .build();

        notifManager.notify(0, noti);


    }
}
