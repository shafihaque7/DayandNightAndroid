package com.example.rafisantu.helloworld;

import android.icu.util.Calendar;

/**
 * Created by rafisantu on 8/22/2017.
 */





class DayNightFilter1

{

    int width_;

    int height_;

    int offset_;

    int [] wtab_;

    int sign_;

    private static short monthOffset_[] = {

            0, 31, 59, 90, 120, 151, 181, 212, 243, 273,

            304, 334

    };



    DayNightFilter1(int width, int height, int offset)

    {

        width_ = width;

        height_ = height;

        offset_ = offset;

        wtab_ = new int[width];

        sign_ = 1;

    }//constructor





    /**

     * Construct width table

     * ZONE_OFFSET has to be in minute

     */

    void updateWidthTable(Calendar cal)

    {

        double d = (6.2831853071795862D * (double)((cal.get(Calendar.DAY_OF_MONTH) + monthOffset_[cal.get(Calendar.MONTH)]) - 80)) / 365D;

        if(d == 0.0D) d = 0.001D;

        double d1 = 1.0D / (Math.sin(d) * Math.tan(0.40927970959267024D));

        d = (6.2831799999999998D * (double)(cal.get(Calendar.HOUR_OF_DAY) * 60 + offset_ + cal.get(Calendar.MINUTE) - (cal.get(Calendar.DST_OFFSET) + cal.get(Calendar.ZONE_OFFSET))/60000)) / 1440D;

        sign_ = d1 < 0.0D ? -1 : 1;

        for(int i = 0; i < wtab_.length; i++)

        {

            wtab_[i] = (int)((double)(height_ / 2) + (Math.atan(d1 * Math.cos(d + ((double)(2 * i) * 3.1415926535897931D) / (double)width_)) * (double)height_) / 3.1415926535897931D);

        }



        System.out.println(cal.getTime().toString() + ": Width Table Updated");

    }//updateWidthTable





    void updateWidthTable()

    {

        updateWidthTable(Calendar.getInstance());

    }//updateWidthTable





    /**

     * Filter the pixel depending on its coordinate

     */

    public int filterRGB(int x, int y, int rgb)
    {
        return rgb;
    }

}
class filteringColor { // Lowers the opacity for the pixel.
    public static int filterRGB(int x, int y, int rgb) {
        return rgb & 0xff000000 | (rgb & 0xfe0000) >> 1 | (rgb & 0xfe00) >> 1 | (rgb & 0xfe) >> 1;
    }

}



