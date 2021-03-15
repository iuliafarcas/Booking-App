package com.example.tennispal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class DelBooking extends AppCompatActivity {

    CalendarView dcalender;

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String date = "", data;
    TextView books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_booking);

        dcalender = (CalendarView)findViewById(R.id.dcalender);
        books = (TextView)findViewById(R.id.books);
        fStore = FirebaseFirestore.getInstance() ;
        fAuth = FirebaseAuth.getInstance();

        dcalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                date = (month + 1) + "." + dayOfMonth +  "." + year;

                // set this date in TextView for Display
                //books.setText(date);
                checkdate();
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
                        String s = parse();
                        books.setText(s);
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

    public String parse()
    {
        data = data.substring(1, data.length() - 1);
        String[] s = data.split(",");
        //String[] ss = new String[14];
        String[] ss = new String[14];
        String x;
        int count = 0;
        for(String w: s)
        {
            if(count == 0)
            {
                x = w.substring(0,5);
                w = w.substring(0, w.length()) + "\n";
                //ss = w + "\n";

            }
            else
            {
                x = w.substring(1,6);
                w = w.substring(1, w.length()) + "\n";
                //ss += w + "\n";

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
            count++;
        }

        x = "";
        for(String str: ss)
        {
            x += str;
        }
        return x;
    }
}

