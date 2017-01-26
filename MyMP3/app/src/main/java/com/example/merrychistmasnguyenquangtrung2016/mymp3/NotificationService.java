package com.example.merrychistmasnguyenquangtrung2016.mymp3;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.merrychistmasnguyenquangtrung2016.mymp3.utilily.Constants;

/**
 * Created by dell on 1/25/2017.
 */

public class NotificationService extends Service implements MediaPlayer.OnCompletionListener {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            showNotification();

            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            if(MainActivity.currentSongIndex >0) {
                MainActivity.currentSongIndex--;

            }
            else{
                MainActivity.currentSongIndex=MainActivity.arrSongs.size()-1;
            }
           playSong();


        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            if(MainActivity.mp.isPlaying()){
                MainActivity.mp.pause();

            }
            else{
                MainActivity.mp.start();

            }
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            if(MainActivity.currentSongIndex < MainActivity.arrSongs.size() -1) {
                MainActivity.currentSongIndex++;

            }
            else{
                MainActivity.currentSongIndex=0;
            }
            playSong();

        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            Toast.makeText(this, "Service Stoped", Toast.LENGTH_SHORT).show();
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    Notification status;
    private final String LOG_TAG = "NotificationService";

    private void showNotification() {

        RemoteViews bigViews = new RemoteViews(getPackageName(),
                R.layout.status_bar_expanded);

// showing default album image
      //  views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
       // views.setViewVisibility(R.id.status_bar_album_art, View.GONE);
        bigViews.setImageViewBitmap(R.id.status_bar_album_art,
                Constants.getDefaultAlbumArt(this));

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(this, NotificationService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, NotificationService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(this, NotificationService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Intent closeIntent = new Intent(this, NotificationService.class);
        closeIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, 0);

       // views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);

       // views.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);

     //   views.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);

       // views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);

       // views.setImageViewResource(R.id.status_bar_play,
              //  R.drawable.last);
        bigViews.setImageViewResource(R.id.status_bar_play,
                R.drawable.last);

       // views.setTextViewText(R.id.status_bar_track_name, "Song Title");
        bigViews.setTextViewText(R.id.status_bar_track_name, "Song Title");

      //  views.setTextViewText(R.id.status_bar_artist_name, "Artist Name");
        bigViews.setTextViewText(R.id.status_bar_artist_name, "Artist Name");

        bigViews.setTextViewText(R.id.status_bar_album_name, "Album Name");

        status = new Notification.Builder(this).build();
        //status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.drawable.forward;
        status.contentIntent = pendingIntent;
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
    }

    @Override
    public void onDestroy() {
        // Giải phóng nguồn dữ nguồn phát nhạc.
        Log.i("giaiphong","giaiphong");
        super.onDestroy();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

        if(MainActivity.currentSongIndex < MainActivity.arrSongs.size() - 1){
            MainActivity.currentSongIndex=MainActivity.currentSongIndex + 1;
          playSong();

        }
        else{
            MainActivity.currentSongIndex=0;
           playSong();

        }
    }

    public void playSong(){
        MainActivity.mp.reset();
        MainActivity.mp = MediaPlayer.create(getApplicationContext(), MainActivity.arrSongs.get(MainActivity.currentSongIndex).getPath());
        MainActivity.toolbar.setTitle(MainActivity.arrSongs.get(MainActivity.currentSongIndex).getTitle());

        MainActivity.mp.start();
        MainActivity.mp.setOnCompletionListener(this);
    }
}
