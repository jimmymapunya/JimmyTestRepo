package academy.com.daapp.connect.Controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import academy.com.daapp.connect.Models.Calender;
import academy.com.daapp.connect.Models.SuggestionBox;
import academy.com.daapp.connect.Models.Talks;

/**
 * Created by TheDigitalAcademy on 16/03/04.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {


        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}