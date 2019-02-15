package com.example.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton rb;
    private ImageView imvu;
    private ImageView playbutton;
    private Boolean songIsPaused = true;
    private String currentArtwork = "nothing";

    static MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imvu = (ImageView) findViewById(R.id.albumView);
        playbutton = (ImageView) findViewById(R.id.playView);

        rg = (RadioGroup) findViewById(R.id.radioSex);

        if (savedInstanceState != null) {
            String rotateArtwork = savedInstanceState.getString("artwork");
            String shouldItBePlay = savedInstanceState.getString("state");
            System.out.println(rotateArtwork);

            switch (rotateArtwork) {
                case "stress":
                    imvu.setImageResource(R.drawable.stressedout);
                    currentArtwork = "stress";
                    break;
                case "counting":
                    imvu.setImageResource(R.drawable.nerepublic);
                    currentArtwork = "counting";
                    break;
                case "demons":
                    imvu.setImageResource(R.drawable.deamons);
                    currentArtwork = "demons";
                    break;
                case "nothing":
                    imvu.setImageResource(R.drawable.recordcopy);
                    currentArtwork = "nothing";
                    break;
            }

            switch (shouldItBePlay) {
                case "true":
                    playbutton.setImageResource(R.drawable.playcopy);
                    songIsPaused = true;
                    break;
                case "false":
                    playbutton.setImageResource(R.drawable.pausecopy);
                    songIsPaused = false;
                    break;

            }


        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("artwork", currentArtwork);
        if (songIsPaused){
            outState.putString("state","true");
        } else {
            outState.putString("state","false");
        }
        super.onSaveInstanceState(outState);
    }

    public void addListenerOnButton(View v) {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String song = (String) rb.getText();
        switch (song) {
            case "Stressed Out":
                if(player == null) {
                    player = MediaPlayer.create(this, R.raw.stressedout);
                } else if(player != null) {
                    player.release();
                    player = null;
                    Toast.makeText(this,"Restarted song", Toast.LENGTH_SHORT).show();
                    player = MediaPlayer.create(this, R.raw.stressedout);
                }
                songIsPaused = false;
                currentArtwork = "stress";
                imvu.setImageResource(R.drawable.stressedout);
                playbutton.setImageResource(R.drawable.pausecopy);
                player.start();
                break;
            case "Demons":
                if(player == null) {
                    player = MediaPlayer.create(this, R.raw.demon);
                } else if(player != null) {
                    player.release();
                    player = null;
                    Toast.makeText(this,"Restarted song", Toast.LENGTH_SHORT).show();
                    player = MediaPlayer.create(this, R.raw.demon);
                }
                songIsPaused = false;
                currentArtwork = "demons";
                imvu.setImageResource(R.drawable.deamons);
                playbutton.setImageResource(R.drawable.pausecopy);
                player.start();
                break;
            case "Counting Stars":
                if(player == null) {
                    player = MediaPlayer.create(this, R.raw.countingstars);
                } else if(player != null) {
                    player.release();
                    player = null;
                    Toast.makeText(this,"Restarted song", Toast.LENGTH_SHORT).show();
                    player = MediaPlayer.create(this, R.raw.countingstars);
                }
                currentArtwork = "counting";
                songIsPaused = false;
                imvu.setImageResource(R.drawable.nerepublic);
                playbutton.setImageResource(R.drawable.pausecopy);
                player.start();
                break;
        }
    }

public void pause(View v) {
    System.out.println(songIsPaused);
        if (songIsPaused) {
            player.start();
            playbutton.setImageResource(R.drawable.pausecopy);
            Toast.makeText(this,"Resuming", Toast.LENGTH_SHORT).show();
            songIsPaused = false;
            return;
        }
        else if (player != null) {
            playbutton.setImageResource(R.drawable.playcopy);
            songIsPaused = true;
            player.pause();
            Toast.makeText(this,"Pausing", Toast.LENGTH_SHORT).show();
            return;
        }
}

public void stoppedsong(View v) {
        currentArtwork = "nothing";
        if (player != null) {
            player.release();
            rg.clearCheck();
            player = null;
            songIsPaused = false;
            Toast.makeText(this,"Song Stopped", Toast.LENGTH_SHORT).show();
        }
}

}
