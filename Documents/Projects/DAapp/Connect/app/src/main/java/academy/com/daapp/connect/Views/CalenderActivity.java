package academy.com.daapp.connect.Views;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import academy.com.daapp.connect.R;

/**
 * Created by TheDigitalAcademy on 16/03/07.
 */
public class CalenderActivity extends Activity {

    CalendarView calendar;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_activity);

        calender();



    }


    public void initializeCalendar() {
//        calendar = (CalendarView) findViewById(R.id.calendar);
//
//        // sets whether to show the week number.
//        calendar.setShowWeekNumber(false);
//
//        // sets the first day of week according to Calendar.
//        // here we set Monday as the first day of the Calendar
//        calendar.setFirstDayOfWeek(2);
//
//        //The background color for the selected week.
//        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
//
//        //sets the color for the dates of an unfocused month.
//        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
//
//        //sets the color for the separator line between weeks.
//        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
//
//        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
//        calendar.setSelectedDateVerticalBar(R.color.darkgreen);
//
//        //sets the listener to be notified upon selected date change.
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
//                Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
//
//
//
//
//
//
//
//            }
//
//
//        });
//




    }

    public void calender() {

        CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.drawSmallIndicatorForEvents(true);
        compactCalendarView.setLocale(Locale.US);
        compactCalendarView.setUseThreeLetterAbbreviation(true);


        // below allows you to configure color for the current day in the month
        compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.darkgreen));
        // below allows you to configure colors for the current day the user has selected
        compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.grey));

    }
}
