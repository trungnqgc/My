package com.example.merrychistmasnguyenquangtrung2016.mymp3;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.merrychistmasnguyenquangtrung2016.mymp3.model.SongModel;
import com.example.merrychistmasnguyenquangtrung2016.mymp3.utilily.Constants;
import com.example.merrychistmasnguyenquangtrung2016.mymp3.utilily.Utils;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener

{
    public static final int SELECT_SONG_REQUEST=0;
    public static Toolbar toolbar;
    public static ArrayList<SongModel> arrSongs=new ArrayList<>();
    //thoi gian tua ve phia truoc va phia sau
    private int seeForwadTime=5000;//5000 milisecond
    private int seeBackwardTime=5000;
    private int seeBarkeo=0;
    public static   int currentSongIndex=0;
    public static MediaPlayer mp;
    private Handler handler=new Handler();
    private  Handler backgroundHandler=new Handler();
    private ImageView btnplay;
    private ImageView btnForward;
    private ImageView btnBackward;
    private ImageView btnNext;
    private ImageView btnPrevious;
    private SeekBar songProgressBar;
    private TextView lblCurrenDuration;
    private TextView lbltotalduration;
    private ImageView background;
    private ImageView repeat;
    private ImageView truff;
    int arrImage[] ={R.drawable.cloud,R.drawable.music,R.drawable.music2,R.drawable.music3,R.drawable.music4};
    int current_position_background=0;
    private boolean isRepeat=false;
    private boolean isShuffle=false;


    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MP3 Player");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationIcon(R.drawable.list_song);

        setSupportActionBar(toolbar);
        ArrayList<SongModel> list= List_Song_Current.gtesong();
        arrSongs.addAll(list);
        mp=new MediaPlayer();

        btnplay= (ImageView) findViewById(R.id.bt_play);
        btnForward=(ImageView)findViewById(R.id.bt_forward);
        btnBackward=(ImageView)findViewById(R.id.bt_rewind);
        btnNext=(ImageView)findViewById(R.id.bt_next);
        btnPrevious=(ImageView)findViewById(R.id.bt_last);
        songProgressBar=(SeekBar)findViewById(R.id.see_bar);
        lblCurrenDuration=(TextView)findViewById(R.id.current_duration);
        lbltotalduration=(TextView)findViewById(R.id.total_duration);
        background=(ImageView)findViewById(R.id.image_background);
        repeat=(ImageView)findViewById(R.id.nghe_lai);
        truff=(ImageView)findViewById(R.id.lap_baihat);
        //add xu li su kien
        btnplay.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnBackward.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        repeat.setOnClickListener(this);
        truff.setOnClickListener(this);
        songProgressBar.setOnSeekBarChangeListener(this);

        playSong(currentSongIndex);


    }





    public  void playSong(int currentSongIndex) {

        try {

                mp.stop();
                mp.reset();
                mp = MediaPlayer.create(getApplicationContext(), arrSongs.get(currentSongIndex).getPath());

                // mp.prepare();
                mp.start();
            mp.setOnCompletionListener(this);
                toolbar.setTitle(arrSongs.get(currentSongIndex).getTitle());
                btnplay.setImageResource(R.drawable.pause);
                songProgressBar.setProgress(0);
                songProgressBar.setMax(100);
                 Log.i("update","update");
                //Update Progress
                updateProgressBar();
               Log.i("noti","noti");
               notificationService();


        }
        catch (IllegalStateException e){
            Log.i("Loi",e.getMessage());
        }
    }

    private void notificationService() {

        serviceIntent = new Intent(MainActivity.this, NotificationService.class);
        serviceIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        startService(serviceIntent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(mUpdateTimeTask);

        mp.release();
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_SONG_REQUEST && resultCode==RESULT_OK){
            currentSongIndex =data.getExtras().getInt("i");
            playSong(currentSongIndex);
        }

    }

    private void updateProgressBar() {

        handler.postDelayed(mUpdateTimeTask,100);
        handler.postDelayed(UpdateimageBackground,5000);
    }
    private Runnable mUpdateTimeTask=new Runnable() {
        @Override
        public void run() {
            try {


                long totalDuration = mp.getDuration();
                long currentduration = mp.getCurrentPosition();
                //hien thi duration len time
                lbltotalduration.setText("" + Utils.milliSecondsToTimer(totalDuration));
                lblCurrenDuration.setText("" + Utils.milliSecondsToTimer(currentduration));
                //update len progresss
                int progress = (Utils.getProgressPercentage(currentduration, totalDuration));
                songProgressBar.setProgress(progress);
                handler.postDelayed(this, 100);
            }
            catch (Exception e){
                Log.i("loi",e.getMessage().toString());
            }
        }
    };

    private Runnable UpdateimageBackground=new Runnable(){

        @Override
        public void run() {
            background.setImageResource(arrImage[current_position_background]);
            current_position_background++;
            if(current_position_background == arrImage.length-1){
                current_position_background=0;
            }
            handler.postDelayed(UpdateimageBackground,10000);
        }
    };



    @Override
    public void onClick(View view) {
      //play or pause mot bai hat
        int id=view.getId();
        if(id==R.id.bt_play){
            if(mp.isPlaying()){
                mp.pause();
                btnplay.setImageResource(R.drawable.pause);
            }
            else{
                mp.start();
                btnplay.setImageResource(R.drawable.play);
                btnplay.setImageResource(R.drawable.play);
            }
        }
        //buttoon forward
        if(id==R.id.bt_forward){
            //get currposition cua mp3
            int currentDuration=mp.getCurrentPosition();
            int current_forward=currentDuration+seeForwadTime;
            if(current_forward < mp.getDuration()){
                mp.seekTo(current_forward);
            }
            else{
                mp.seekTo(mp.getDuration());
            }

        }
        if(id==R.id.bt_rewind){
            int cuuentDuration=mp.getCurrentPosition();
            int current_rewind=cuuentDuration-seeBackwardTime;
            if(current_rewind >= 0){
                mp.seekTo(current_rewind);
            }
            else{
                mp.seekTo(0);
            }
        }

        //next mot bai hat
        if(id==R.id.bt_next){
            if(currentSongIndex < arrSongs.size() - 1){
                currentSongIndex =currentSongIndex + 1;
                playSong(currentSongIndex);

            }
            else{
                playSong(0);
                currentSongIndex=0;
            }
          notificationService();


        }
        if(id==R.id.bt_last){
            if(currentSongIndex >0){
                currentSongIndex =currentSongIndex - 1;
                playSong(currentSongIndex);

            }
            else{
                playSong(arrSongs.size()-1);
                currentSongIndex=arrSongs.size()-1;
            }
           notificationService();
        }
        if(id==R.id.nghe_lai){
              if(isRepeat){
                  isRepeat=false;
                  Toast.makeText(getApplicationContext(),"Repeat is Off",Toast.LENGTH_LONG).show();
              }
            else{
                  isRepeat=true;
                  isShuffle=false;
                  Toast.makeText(getApplicationContext(),"Repeat is On",Toast.LENGTH_LONG).show();
              }
        }
        if(id==R.id.lap_baihat){
            if(isShuffle){
                isShuffle=false;
                Toast.makeText(getApplicationContext(),"Shuff is Off",Toast.LENGTH_LONG).show();

            }
            else{
                isShuffle=true;
                isRepeat=false;
                Toast.makeText(getApplicationContext(),"Shuff is on",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.header_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.list_song:{
                Intent intent=new Intent(this,List_Song.class);
                startActivityForResult(intent,SELECT_SONG_REQUEST);

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }



    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.i("progre", String.valueOf(i));
        Log.i("max", String.valueOf(seekBar.getMax()));

             seeBarkeo=i;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
//        seekBar.setProgress(Integer.parseInt(seeBarkeo + "/" + seekBar.getMax()));

        mp.seekTo(Utils.progressToTimer(seeBarkeo,mp.getDuration()));
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {


        if(isRepeat){
            // repeat is on play same song again
            playSong(currentSongIndex);
        } else if(isShuffle){
            // shuffle is on - play a random song
            Random rand = new Random();
            currentSongIndex = rand.nextInt((arrSongs.size() - 1) - 0 + 1) + 0;
            playSong(currentSongIndex);
        } else{
            // no repeat or shuffle ON - play next song
            if(currentSongIndex < (arrSongs.size() - 1)){
                playSong(currentSongIndex + 1);
                currentSongIndex = currentSongIndex + 1;
            }else{
                // play first song
                playSong(0);
                currentSongIndex = 0;
            }
        }

    }
}
