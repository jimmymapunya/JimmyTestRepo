package academy.com.daapp.connect.Views;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import academy.com.daapp.connect.Controllers.JSONParser;
import academy.com.daapp.connect.R;

/**
 * Created by TheDigitalAcademy on 16/03/07.
 */
public class TalksActivity extends Activity implements View.OnClickListener{

    private String url = "http://masscash.empirestate.co.za/BeaconAppTest/phpStoredProcedure/API/getSpeakersData.php";
    private  JSONObject jsonObject;
    JSONParser jsonParser;
    JSONArray speakersArray;

    private Spinner speakersSpinner;
    private ArrayAdapter<String> speakersAdapter;
    private ArrayList<String> listSpeakers;
    Context context = this;

    private EditText edtComment;
    private SeekBar seekBar;
    private TextView tvRatingValue;
    private Button btnDone;
    private String speakerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talks_activity);

        speakersSpinner = (Spinner)findViewById(R.id.spinner_speakers);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        tvRatingValue = (TextView)findViewById(R.id.tvRatingValue);
        edtComment = (EditText)findViewById(R.id.edtComment);
        btnDone = (Button)findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);

        jsonParser = new JSONParser();
        listSpeakers = new ArrayList<>();

        tvRatingValue.setText(""+ seekBar.getProgress());
        seekBar.setMax(10);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean b) {

                progress = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                tvRatingValue.setText("" + progress);

            }
        });

        getSpeakersInformation();


        speakersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                speakerName = adapterView.getItemAtPosition(i).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    void postRatingResults()
    {


        class postTalkRatings extends AsyncTask<String,String,JSONObject>
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();


            }

            String progress = tvRatingValue.getText().toString();
            String comment = edtComment.getText().toString();


            @Override
            protected JSONObject doInBackground(String... strings) {

                String url = "http://masscash.empirestate.co.za/BeaconAppTest/phpStoredProcedure/API/postTalkRatings.php";

                HashMap<String, String> hashMap = new HashMap<>();



                hashMap.put("speakerName", speakerName);
                hashMap.put("ratingValue", progress);
                hashMap.put("comment", ""+comment);


                JSONObject jsonObject = jsonParser.makeHttpRequest(url, "POST", hashMap);


                return null;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {

            }
        }

        new postTalkRatings().execute();


    }


    void getSpeakersInformation()
    {
        class Speakers extends AsyncTask<String,String,JSONObject>
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }
            @Override
            protected JSONObject doInBackground(String... strings) {

                jsonObject = jsonParser.makeHttpGet(url, "GET");
                try {
                    speakersArray = jsonObject.getJSONArray("speakers");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(JSONObject jsonObject) {

                for (int x=0;x<speakersArray.length();x++)
                {
                    try {
                        jsonObject = speakersArray.getJSONObject(x);
                        String name = jsonObject.getString("Name");
                        listSpeakers.add(name);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                speakersAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listSpeakers);
                speakersSpinner.setAdapter(speakersAdapter);

            }
        }

        new Speakers().execute();

    }


    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btnDone)
        {

            postRatingResults();

        }

    }
}
