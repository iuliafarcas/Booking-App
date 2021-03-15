package com.example.tennispal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Calendar extends AppCompatActivity {

    CalendarView calender;
    TextView date_view;
    String date = "", data;

    TextView h8, h9, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20, h21;
    CheckBox cb8, cb9, cb10, cb11, cb12, cb13, cb14, cb15, cb16, cb17, cb18, cb19, cb20, cb21;
    Button book;
    boolean b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21;

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calender = (CalendarView)findViewById(R.id.calender);
        date_view = (TextView)findViewById(R.id.date_view);
        fStore = FirebaseFirestore.getInstance() ;
        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getCurrentUser().getUid();


        h8 = (TextView)findViewById(R.id.h15);
        h9 = (TextView)findViewById(R.id.h9);

        cb8 = findViewById(R.id.cb8);
        cb9 = findViewById(R.id.cb9);
        cb10 = findViewById(R.id.cb10);
        cb11 = findViewById(R.id.cb11);
        cb12 = findViewById(R.id.cb12);
        cb13 = findViewById(R.id.cb13);
        cb14 = findViewById(R.id.cb14);
        cb15 = findViewById(R.id.cb15);
        cb16 = findViewById(R.id.cb16);
        cb17 = findViewById(R.id.cb17);
        cb18 = findViewById(R.id.cb18);
        cb19 = findViewById(R.id.cb19);
        cb20 = findViewById(R.id.cb20);
        cb21 = findViewById(R.id.cb21);


        book = findViewById(R.id.bbook);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                date = (month + 1) + "." + dayOfMonth +  "." + year;

                   // set this date in TextView for Display
                   date_view.setText(date);

                {cb8.setVisibility(View.VISIBLE);
                cb9.setVisibility(View.VISIBLE);
                cb10.setVisibility(View.VISIBLE);
                cb11.setVisibility(View.VISIBLE);
                cb12.setVisibility(View.VISIBLE);
                cb13.setVisibility(View.VISIBLE);
                cb14.setVisibility(View.VISIBLE);
                cb15.setVisibility(View.VISIBLE);
                cb16.setVisibility(View.VISIBLE);
                cb17.setVisibility(View.VISIBLE);
                cb18.setVisibility(View.VISIBLE);
                cb19.setVisibility(View.VISIBLE);
                cb20.setVisibility(View.VISIBLE);
                cb21.setVisibility(View.VISIBLE);}

                   checkdate();
            }
        });

        cb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb8.isChecked())
                    b8 = !b8;
            }
        });
        cb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb9.isChecked())
                    b9 = !b9;
            }
        });
        cb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb10.isChecked())
                    b10 = !b10;
            }
        });
        cb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb11.isChecked())
                    b11 = !b11;
            }
        });
        cb12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb12.isChecked())
                    b12 = !b12;
            }
        });
        cb13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb13.isChecked())
                    b13 = !b13;
            }
        });
        cb14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb14.isChecked())
                    b14 = !b14;
            }
        });
        cb15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb15.isChecked())
                    b15 = !b15;
            }
        });
        cb16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb16.isChecked())
                    b16 = !b16;
            }
        });
        cb17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb17.isChecked())
                    b17 = !b17;
            }
        });
        cb18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb18.isChecked())
                    b18 = !b18;
            }
        });
        cb19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb19.isChecked())
                    b19 = !b19;
            }
        });
        cb20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb20.isChecked())
                    b20 = !b20;
            }
        });
        cb21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb21.isChecked())
                    b21 = !b21;
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.equals(""))
                    Toast.makeText(Calendar.this,"SELECT A DAY!", Toast.LENGTH_SHORT).show();
                else book();

                if(!b8 || !b9 || !b10 || !b11 || !b12 || !b13 || !b14 || !b15 || !b16 || !b17 || !b18 || !b19 || !b20 || !b21)
                    Toast.makeText(Calendar.this,"Booking successful", Toast.LENGTH_SHORT).show();
                else Toast.makeText(Calendar.this,"Please select an hour.", Toast.LENGTH_SHORT).show();

                { if(cb8.isChecked())
                    cb8.toggle();
                if(cb9.isChecked())
                    cb9.toggle();
                if(cb10.isChecked())
                    cb10.toggle();
                if(cb11.isChecked())
                    cb11.toggle();
                if(cb12.isChecked())
                    cb12.toggle();
                if(cb13.isChecked())
                    cb13.toggle();
                if(cb14.isChecked())
                    cb14.toggle();
                if(cb15.isChecked())
                    cb15.toggle();
                if(cb16.isChecked())
                    cb16.toggle();
                if(cb17.isChecked())
                    cb17.toggle();
                if(cb18.isChecked())
                    cb18.toggle();
                if(cb19.isChecked())
                    cb19.toggle();
                if(cb20.isChecked())
                    cb20.toggle();
                if(cb21.isChecked())
                    cb21.toggle();}

            }
        });
    }


    public void checkdate()
    {
        DocumentReference docRef = fStore.collection("Bookings").document(date);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        data = document.getData().toString();
                        String[] s = parse();
                        int count = 8;
                        //System.out.println("///////////////////////////////////////////////////////////////////////");
                        for(String ss:s)
                        {
                            //System.out.println("count: " + count + ", " + ss);
                            if(!ss.equals("free"))
                            {
                                switch(count)
                                {
                                    case 8:
                                        cb8.setVisibility(View.INVISIBLE);
                                        break;
                                    case 9:
                                        cb9.setVisibility(View.INVISIBLE);
                                        break;
                                    case 10:
                                        cb10.setVisibility(View.INVISIBLE);
                                        break;
                                    case 11:
                                        cb11.setVisibility(View.INVISIBLE);
                                        break;
                                    case 12:
                                        cb12.setVisibility(View.INVISIBLE);
                                        break;
                                    case 13:
                                        cb13.setVisibility(View.INVISIBLE);
                                        break;
                                    case 14:
                                        cb14.setVisibility(View.INVISIBLE);
                                        break;
                                    case 15:
                                        cb15.setVisibility(View.INVISIBLE);
                                        break;
                                    case 16:
                                        cb16.setVisibility(View.INVISIBLE);
                                        break;
                                    case 17:
                                        cb17.setVisibility(View.INVISIBLE);
                                        break;
                                    case 18:
                                        cb18.setVisibility(View.INVISIBLE);
                                        break;
                                    case 19:
                                        cb19.setVisibility(View.INVISIBLE);
                                        break;
                                    case 20:
                                        cb20.setVisibility(View.INVISIBLE);
                                        break;
                                    case 21:
                                        cb21.setVisibility(View.INVISIBLE);
                                        break;

                                }
                            }
                            count++;
                        }
                    } else {
                        Map<String, Object> bookings = new HashMap<>();
                        bookings.put("08-09", "free");
                        bookings.put("09-10", "free");
                        bookings.put("10-11", "free");
                        bookings.put("11-12", "free");
                        bookings.put("12-13", "free");
                        bookings.put("13-14", "free");
                        bookings.put("14-15", "free");
                        bookings.put("15-16", "free");
                        bookings.put("16-17", "free");
                        bookings.put("17-18", "free");
                        bookings.put("18-19", "free");
                        bookings.put("19-20", "free");
                        bookings.put("20-21", "free");
                        bookings.put("21-22", "free");
                        docRef.set(bookings);

                    }
                }
            }
        });

    }

    public String[] parse()
    {
        data = data.substring(1, data.length() - 1);
        String[] s = data.split(",");
        String[] ss = new String[14];
        String x;
        int count = 0;
        for(String w: s)
        {
           // x = w.substring(0,5);
            //System.out.println(x);
            if(count == 0)
            {
                x = w.substring(0,5);
                w = w.substring(6, w.length());

            }
            else
            {
                x = w.substring(1,6);
                w = w.substring(7, w.length());

            }

            switch(x)
            {
                case "08-09":
                    ss[0] = w;
                    break;
                case "09-10":
                    ss[1] = w;
                    break;
                case "10-11":
                    ss[2] = w;
                    break;
                case "11-12":
                    ss[3] = w;
                    break;
                case "12-13":
                    ss[4] = w;
                    break;
                case "13-14":
                    ss[5] = w;
                    break;
                case "14-15":
                    ss[6] = w;
                    break;
                case "15-16":
                    ss[7] = w;
                    break;
                case "16-17":
                    ss[8] = w;
                    break;
                case "17-18":
                    ss[9] = w;
                    break;
                case "18-19":
                    ss[10] = w;
                    break;
                case "19-20":
                    ss[11] = w;
                    break;
                case "20-21":
                    ss[12] = w;
                    break;
                case "21-22":
                    ss[13] = w;
                    break;

            }

            //ss[count] = w;
            count++;
        }
        return ss;
    }

    public void book()
    {
        DocumentReference docRef = fStore.collection("Users").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        data = document.getData().toString();
                        String s = parse2(data);
                        DocumentReference docRef2 = fStore.collection("Bookings").document(date);
                        {if(b8)
                            docRef2.update("08-09", s);
                        if(b9)
                            docRef2.update("09-10", s);
                        if(b10)
                            docRef2.update("10-11", s);
                        if(b11)
                            docRef2.update("11-12", s);
                        if(b12)
                            docRef2.update("12-13", s);
                        if(b13)
                            docRef2.update("13-14", s);
                        if(b14)
                            docRef2.update("14-15", s);
                        if(b15)
                            docRef2.update("15-16", s);
                        if(b16)
                            docRef2.update("16-17", s);
                        if(b17)
                            docRef2.update("17-18", s);
                        if(b18)
                            docRef2.update("18-19", s);
                        if(b19)
                            docRef2.update("19-20", s);
                        if(b20)
                            docRef2.update("20-21", s);
                        if(b21)
                            docRef2.update("21-22", s);}
                        //System.out.println(s);
                    }
                }
            }
        });

        recreate();
    }

    public String parse2(String data)
    {
        data = data.substring(1, data.length() - 1);
        String[] s = data.split(",");
        String fname = s[4].substring(10, s[4].length());
        String first_name = s[5].substring(12, s[5].length());
        //String pnb = s[6].substring(7, s[6].length());
        String pnb = s[3].substring(8, s[3].length());

        return (first_name + " " + fname + ": " + pnb);
    }

}
