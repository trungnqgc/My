package com.example.merrychistmasnguyenquangtrung2016.mymp3.utilily;

/**
 * Created by dell on 1/16/2017.
 */

public class Utils {
    /**funtion to convert milisecond time
     * Time format
     * Hours :minutes:Second
     * //chuyen doi thoi gian cua mot vbai hat ve milisecond giay
     * convert thanhf string de hien thi len man hinh
     *
    **/
    public static String milliSecondsToTimer(long miliseconds){
        String  finalTimerString="";
        String secondsString="";
              //convert     total duration into time
        int hours= (int) (miliseconds/ (1000 *60*60));
        int minutes = (int)(miliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int second = (int) ((miliseconds % (1000*60*60)) % (1000*60) /1000);
        if(hours > 0){
             finalTimerString =hours + ":";
        }
        if(second < 10){
            secondsString="0" + second;

        }
        else{
            secondsString="" + second;
        }
        finalTimerString =finalTimerString + minutes + ":"+ secondsString;
        return finalTimerString;

    }
    //convet thoi gian dang chay bai hat voi tong thoi gian chay bai hat
    public static int getProgressPercentage(long currentDuration,long totalDuration){
             Double percentage= (double)(0);
        long currentSecond=(int) (currentDuration/1000);
        long totalSecond=(int)(totalDuration/1000);
        percentage=(((double)currentSecond)/totalSecond)*100;
        return percentage.intValue();
    }
    //keo sessbar roi nha ra o vi tri nao
    public static int progressToTimer(int progress,int totalDuration){
            int currentDuration=0;
        totalDuration=(int)(totalDuration/1000);
        currentDuration=(int)((((double)progress) / 100) * totalDuration);
        return currentDuration*1000;
    }

}
