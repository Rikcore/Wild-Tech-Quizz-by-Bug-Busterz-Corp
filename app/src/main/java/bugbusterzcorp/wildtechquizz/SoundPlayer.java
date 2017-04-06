package bugbusterzcorp.wildtechquizz;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by apprenti on 06/04/17.
 */

public class SoundPlayer {

    private static final int SOUND_POOL_MAX = 2 ;
    private AudioAttributes audioAttributes;
    private static SoundPool soundPool;
    private static int successSound;
    private static int failSound;
    private static int stageSound;

    public SoundPlayer (Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();

        } else {
            //SoundPool (int maxStreams, int streamType, int srcQuality)
            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);
        }


        successSound = soundPool.load(context, R.raw.success, 1);
        failSound = soundPool.load(context, R.raw.wrong_buzzer, 1);
        stageSound = soundPool.load(context, R.raw.stage, 1);

    }

    public void playSuccessSound() {

        // play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(successSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playFailSound() {
        soundPool.play(failSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playStageSound() {
        soundPool.play(stageSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}





