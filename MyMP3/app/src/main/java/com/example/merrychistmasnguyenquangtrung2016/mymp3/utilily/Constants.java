package com.example.merrychistmasnguyenquangtrung2016.mymp3.utilily;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.merrychistmasnguyenquangtrung2016.mymp3.R;

/**
 * Created by dell on 1/25/2017.
 */

public class Constants {
    public interface ACTION {
        public static String MAIN_ACTION = " com.example.merrychistmasnguyenquangtrung2016.mymp3.action.main";
        public static String INIT_ACTION = " com.example.merrychistmasnguyenquangtrung2016.mymp3.action.init";
        public static String PREV_ACTION = " com.example.merrychistmasnguyenquangtrung2016.mymp3.action.prev";
        public static String PLAY_ACTION = " com.example.merrychistmasnguyenquangtrung2016.mymp3.action.play";
        public static String NEXT_ACTION = " com.example.merrychistmasnguyenquangtrung2016.mymp3.action.next";
        public static String STARTFOREGROUND_ACTION = " com.example.merrychistmasnguyenquangtrung2016.mymp3.action.startforeground";
        public static String STOPFOREGROUND_ACTION = " com.example.merrychistmasnguyenquangtrung2016.mymp3.action.stopforeground";

    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }

    public static Bitmap getDefaultAlbumArt(Context context) {
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            bm = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.music, options);
        } catch (Error ee) {
        } catch (Exception e) {
        }
        return bm;
    }


}

