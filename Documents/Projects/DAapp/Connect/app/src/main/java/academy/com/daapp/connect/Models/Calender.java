package academy.com.daapp.connect.Models;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import academy.com.daapp.connect.R;

/**
 * Created by TheDigitalAcademy on 16/03/04.
 */
public class Calender extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.talks_activity,container,false);


        return  rootView;


    }


}