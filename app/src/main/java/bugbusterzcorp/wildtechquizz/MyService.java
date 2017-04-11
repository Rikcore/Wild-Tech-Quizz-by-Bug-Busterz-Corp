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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyService extends Service {

    public static final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Quizz");
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String creatorName;

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                creatorName = (String) dataSnapshot.child("username").getValue();
                displayNotification();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return Service.START_STICKY;

    }

    private void displayNotification() {

        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Wild Tech Quizz")
                .setContentText(user.getDisplayName()+", un nouveau quizz de "+creatorName+" vous attend!")
                .setSmallIcon(R.mipmap.interrogation_burned)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0))
                .build();

        notifManager.notify(0, noti);


    }


}
