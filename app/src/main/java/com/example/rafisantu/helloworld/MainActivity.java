package com.example.rafisantu.helloworld;



import android.app.DatePickerDialog;

import android.app.Dialog;

import android.app.TimePickerDialog;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

import android.icu.util.Calendar;

import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.DatePicker;

import android.widget.ImageView;

import android.widget.TextView;

import android.widget.TimePicker;

import android.widget.Toast;



public class MainActivity extends AppCompatActivity {



    TextView txt;

    Button btn;



    int clicked =0;


    boolean chageImage = true;



    Calendar getCurrentCal = Calendar.getInstance();



    Button btn2;

    int year_x = getCurrentCal.get(Calendar.YEAR);

    int month_x = getCurrentCal.get(Calendar.MONTH);

    int day_x = getCurrentCal.get(Calendar.DAY_OF_MONTH);

    static final int DIALOG_ID = 0;



    Button button_stpd;

    static final int DIALOG_ID_2 = 23;

    int hour_x = getCurrentCal.get(Calendar.HOUR_OF_DAY);

    int minute_x = getCurrentCal.get(Calendar.MINUTE);

    Bitmap bitmap;
    Paint paint;
    Bitmap workingBitmap;
    Bitmap mutableBitmap;
    Canvas canvas;
    ImageView imageView;

    int widthOfImage = 2048; // Width and height of the image, it can be changed here if a different image is used.
    int heightOfImage = 1024;
    DayNightFilter1 filter1 = new DayNightFilter1(widthOfImage, heightOfImage, 0);



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        txt= (TextView)findViewById(R.id.editText2);

        btn= (Button)findViewById(R.id.button);

        //img= (ImageView)findViewById(R.id.imageView);

        //img.setImageResource(R.drawable.earth);

        showDialogOnButtonClick();

        showTimePickerDialog();


        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
        myOptions.inPurgeable = true;

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.earth,myOptions);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);


        workingBitmap = Bitmap.createBitmap(bitmap);
        mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);


        canvas = new Canvas(mutableBitmap);
        canvas.drawCircle(100, 50, 25, paint);
        //canvas.drawCircle(100, 100, 25, paint);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setAdjustViewBounds(true);
        imageView.setImageBitmap(mutableBitmap);







        btn.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){

                txt.setText("Button is clicked"+Integer.toString(clicked));

                clicked++;

                BitmapFactory.Options myOptions = new BitmapFactory.Options();
                myOptions.inDither = true;
                myOptions.inScaled = false;
                myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
                myOptions.inPurgeable = true;

                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.earth,myOptions);


                Calendar tempCalendar = new GregorianCalendar(year_x, month_x, day_x,hour_x,minute_x,0);
                filter1.updateWidthTable(tempCalendar);

                int colour = bitmap.getPixel(200, 100);

                colour=filteringColor.filterRGB(200,100,colour);

                paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(colour);


                workingBitmap = Bitmap.createBitmap(bitmap);
                mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);


                canvas = new Canvas(mutableBitmap);
                canvas.drawPoint(200, 100, paint);

                for (int i = 0; i < widthOfImage; i++) { // First loop for all of the width value.
                    for (int j = filter1.wtab_[i]; j < heightOfImage; j++) { // Second loop starts at the y value for the curve. Ends at the height of the image, which is bottom of the image.
                        canvas.drawPoint(i,j,paint);
                    }
                }



                //This is where it ends

                imageView = (ImageView)findViewById(R.id.imageView);
                imageView.setAdjustViewBounds(true);
                imageView.setImageBitmap(mutableBitmap);



            }

        });



    }



    public void showTimePickerDialog(){

        button_stpd = (Button) findViewById(R.id.button4);

        button_stpd.setText("Change Time");

        button_stpd.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {

                showDialog(DIALOG_ID_2);



            }



        });

    }







    public void showDialogOnButtonClick(){

        btn2 = (Button)findViewById(R.id.button2);

        btn2.setText("Change Date");

        btn2.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {

                showDialog(DIALOG_ID);



            }



        });



    }

    @Override

    protected Dialog onCreateDialog(int id){



        if (id== DIALOG_ID)

            return new DatePickerDialog(this, dpickerListener, year_x, month_x,day_x );

        if(id==DIALOG_ID_2)

            return new TimePickerDialog(this,kTimePickerListener, hour_x,minute_x, false);// 24 hour or 12 hour is the boolean

        return null;

    }





    private DatePickerDialog.OnDateSetListener dpickerListener

            = new DatePickerDialog.OnDateSetListener(){

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){

            year_x = year;

            month_x = monthOfYear;

            day_x = dayOfMonth;

            Toast.makeText(MainActivity.this,year_x + "/"+ month_x + " / "+day_x,Toast.LENGTH_LONG).show();

            btn2.setText(year_x + "/"+ month_x + " / "+day_x);

        }

    };



    protected TimePickerDialog.OnTimeSetListener kTimePickerListener

            = new TimePickerDialog.OnTimeSetListener() {

        @Override

        public void onTimeSet(TimePicker view, int hourOfDay, int minute){

            hour_x = hourOfDay;

            minute_x = minute;

            Toast.makeText(MainActivity.this, hour_x + " : " + minute_x, Toast.LENGTH_LONG).show();

        }

    };





//    public static Bitmap convertImage()

//    {

//

//

//    }

}

