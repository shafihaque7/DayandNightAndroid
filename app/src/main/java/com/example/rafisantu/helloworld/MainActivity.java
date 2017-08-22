package com.example.rafisantu.helloworld;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
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
    ImageView img;
    boolean chageImage = true;

    Calendar getCurrentCal = Calendar.getInstance();

    Button btn2;
    int year_x = getCurrentCal.get(Calendar.YEAR);
    int month_x = getCurrentCal.get(Calendar.MONTH);
    int day_x = getCurrentCal.get(Calendar.DAY_OF_MONTH);
    static final int DIALOG_ID = 0;

    Button button_stpd;
    static final int DIALOG_ID_2 = 23;
    int hour_x;
    int minute_x;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt= (TextView)findViewById(R.id.editText2);
        btn= (Button)findViewById(R.id.button);
        img= (ImageView)findViewById(R.id.imageView);
        img.setImageResource(R.drawable.earth);
        showDialogOnButtonClick();
        showTimePickerDialog();



        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.setText("Button is clicked"+Integer.toString(clicked));
                clicked++;
                chageImage = !chageImage;
                if(chageImage) {
                    img.setImageResource(R.drawable.penguins);
                }
                else{
                    img.setImageResource(R.drawable.earth);
                }
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
    int testingChanges=5;
}
