package kzn.mrcrabs.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    static MediaPlayer mediaPlayer;
    Button button;
    SharedPreferences mSettings;
    private boolean isMusicPlaying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (mediaPlayer == null)
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mrcrabsmusic);

        mSettings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        isMusicPlaying = mSettings.getBoolean("Music", false);

        button = findViewById(R.id.musicButton);

        if (isMusicPlaying) {
//            pause();
            button.setText("ON");
        } else {
            play();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMusicPlaying) {
                    pause();
                } else {
                    play();
                }
                isMusicPlaying = !isMusicPlaying;
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putBoolean("Music", isMusicPlaying);
                editor.apply();
            }
        });
    }


    public void play() {
        mediaPlayer.start();
        button.setText("OFF");
    }

    public void pause() {
        mediaPlayer.pause();
        button.setText("ON");
    }
}
