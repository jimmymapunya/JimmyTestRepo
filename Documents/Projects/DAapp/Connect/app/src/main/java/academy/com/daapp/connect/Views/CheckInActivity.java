package academy.com.daapp.connect.Views;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import academy.com.daapp.connect.Controllers.JSONParser;
import academy.com.daapp.connect.Controllers.NavDrawerItem;
import academy.com.daapp.connect.Controllers.NavigationDrawerFragment;
import academy.com.daapp.connect.Models.Calender;
import academy.com.daapp.connect.Models.SuggestionBox;
import academy.com.daapp.connect.R;

/**
 * Created by TheDigitalAcademy on 16/03/02.
 */
public class CheckInActivity extends Activity implements View.OnClickListener, NavigationDrawerFragment.NavigationDrawerCallbacks{



    private ImageButton imgBtnHappy,imgBtnSad;
    private EditText edtComment;
    private int mood = 0;
    private String comment;
    private Button btnSubmit,btnDone;
    private static final String URL = "http://masscash.empirestate.co.za/BeaconAppTest/phpStoredProcedure/API/checkIn.php";
    JSONParser jsonParser;

    private ActionBar actionBar;
    private NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_in_activity);

        actionBar = getActionBar();
        actionBar.setTitle("Check In");

        imgBtnHappy = (ImageButton)findViewById(R.id.imgBtnHappy);
        imgBtnSad = (ImageButton)findViewById(R.id.imgBtnSad);
        imgBtnHappy.setOnClickListener(this);
        imgBtnSad.setOnClickListener(this);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        edtComment = (EditText)findViewById(R.id.edtComment);
        jsonParser = new JSONParser();


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer_myprofile);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer_myprofile,
                (DrawerLayout) findViewById(R.id.drawer_layout_myprofile));



    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.imgBtnHappy)
        {
            //Happy
            mood = 0;

        }

        if(view.getId()==R.id.imgBtnSad)
        {
            //Sad
            mood = 1;

        }

        if(view.getId()==R.id.btnSubmit)
        {
            CheckIn();

        }


    }

    void CheckIn()
    {
        comment = edtComment.getText().toString();

        class checkIn extends AsyncTask<String, String, JSONObject>
        {

            @Override
            protected JSONObject doInBackground(String... strings) {



                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("internID", ""+1);
                hashMap.put("mood", ""+mood);
                hashMap.put("comment", comment);


                JSONObject jsonObject = jsonParser.makeHttpRequest(URL, "POST", hashMap);



                if (jsonObject != null){

                    return jsonObject;
                }



                return null;
            }
        }

        new checkIn().execute();



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        onSectionAttached(position);

    }

    public void onSectionAttached(int number) {

        Intent intent;

        switch(number)
        {
            case 0:
                break;

            case 1:
                intent = new Intent(this,TalksActivity.class);
                startActivity(intent);
                break;

            case 2:
                intent = new Intent(this,SuggestionBoxActivity.class);
                startActivity(intent);
                break;

            case 3:
                intent = new Intent(this,CalenderActivity.class);
                startActivity(intent);
                break;



        }

    }
}
