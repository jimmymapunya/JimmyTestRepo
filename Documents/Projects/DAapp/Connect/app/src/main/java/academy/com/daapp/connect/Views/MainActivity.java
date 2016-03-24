package academy.com.daapp.connect.Views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import academy.com.daapp.connect.Controllers.JSONParser;
import academy.com.daapp.connect.R;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button btnRegister,btnTakePicture;
    private EditText edtName,edtEmail,edtPhonenumber,edtComment;
    private String internName,internEmail,teamLeadersNames,academiesnames,internPhonenumber,internComment,selectedName,selectedAcademy;
    private int teamLeadersID,academiesID;
    private Context context;
    private static final String URL = "http://masscash.empirestate.co.za/BeaconAppTest/phpStoredProcedure/API/register.php";
    JSONParser jsonParser;
    JSONObject team;
    JSONObject jsonObject;
    JSONArray teamLeadersArray,academiesArray;
    Spinner spinnerTeamLeaders,spinnerAcademies;
    private ArrayAdapter<String> teamLeadersAdapter,academiesAdapter;
    private ArrayList<String> listTeamLeaders,listAcademies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnTakePicture = (Button)findViewById(R.id.btnTakePicture);
        btnRegister.setOnClickListener(this);
        btnTakePicture.setOnClickListener(this);


        edtName = (EditText)findViewById(R.id.edtName);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPhonenumber = (EditText)findViewById(R.id.edtPhoneNumber);
        edtComment = (EditText)findViewById(R.id.edtComment);
        spinnerTeamLeaders = (Spinner) findViewById(R.id.spinner_team_leaders);
        spinnerAcademies = (Spinner) findViewById(R.id.spinner_academies);

        jsonParser = new JSONParser();
        listTeamLeaders = new ArrayList<String>();
        listAcademies = new ArrayList<String>();


        getTeamLeadersInformation();
        getAcademiesInformation();


    }


    @Override
    public void onClick(View view) {

            if (view.getId() == R.id.btnRegister){

                internName = edtName.getText().toString();
                internEmail = edtEmail.getText().toString();
                internPhonenumber = edtPhonenumber.getText().toString();
                internComment = edtComment.getText().toString();


                if(selectedName.equalsIgnoreCase("Dave"))
                {
                    teamLeadersID = 1;

                }
                else if(selectedName.equalsIgnoreCase("Gael"))
                {
                    teamLeadersID = 2;

                }
                else if(selectedName.equalsIgnoreCase("Tshepo"))
                {
                    teamLeadersID = 3;

                }
                else if(selectedName.equalsIgnoreCase("Bonificious"))
                {
                    teamLeadersID = 4;

                }
                else if(selectedName.equalsIgnoreCase("Avhasei"))
                {
                    teamLeadersID = 5;

                }

                if(selectedAcademy.equalsIgnoreCase("Barclays"))
                {
                    academiesID = 1;

                }
                else if(selectedAcademy.equalsIgnoreCase("Tracker"))
                {
                    academiesID = 2;

                }



                class  Register extends AsyncTask<String, String, JSONObject> {


                    private ProgressDialog progressDialog;


                    private static final String TAG_SUCCESS = "success";
                    private static final String TAG_MESSAGE = "message";

                    String id = android.provider.Settings.System.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);


                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();

                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setMessage("Login in...");
                        progressDialog.setIndeterminate(false);
                        progressDialog.setCancelable(true);
                        progressDialog.show();
                    }

                    @Override
                    protected JSONObject doInBackground(String... params) {

                        try {
                            HashMap<String, String> hashMap = new HashMap<>();
                            //hashMap.put("deviceUDID", id);
                            hashMap.put("name", internName);
                            hashMap.put("email", internEmail);
                            hashMap.put("teamLeaderID", ""+teamLeadersID);
                            hashMap.put("academiesID", ""+academiesID);
                            hashMap.put("phonenumber", internPhonenumber);
                            hashMap.put("comment", internComment);


                            JSONObject jsonObject = jsonParser.makeHttpRequest(URL, "POST", hashMap);


                            if (jsonObject != null){
                                Log.d("JSON result", jsonObject.toString());

                               int success = jsonObject.getInt("success");
                                if(success==1)
                                {
                                    Intent intent = new Intent(context,CheckInActivity.class);
                                    startActivity(intent);

                                }



                                return jsonObject;
                            }
                        }catch (Exception e){

                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(JSONObject jsonObject) {


                        if (progressDialog != null && progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }

                    }
                }


                new Register().execute();

            }
            else if (view.getId() == R.id.btnTakePicture){

                captureImage();

            }


        }

    void getTeamLeadersInformation()
    {

        class teamLeaders extends AsyncTask<String,String,JSONObject>
        {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected JSONObject doInBackground(String... strings) {

                String url = "http://masscash.empirestate.co.za/BeaconAppTest/phpStoredProcedure/API/getLeadersData.php";

                jsonObject = jsonParser.makeHttpGet(url, "GET");
                try {
                    teamLeadersArray = jsonObject.getJSONArray("posts");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return  null;
            }
            @Override
            protected void onPostExecute(JSONObject jsonObject) {


                try {

                    for(int x=0;x<teamLeadersArray.length();x++)
                    {
                        team = teamLeadersArray.getJSONObject(x);
                        teamLeadersNames = team.getString("Name");
                        listTeamLeaders.add(teamLeadersNames);

                    }

                    teamLeadersAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listTeamLeaders);
                    //teamLeadersAdapter.add("");
                    spinnerTeamLeaders.setAdapter(teamLeadersAdapter);


                    spinnerTeamLeaders.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            selectedName = adapterView.getItemAtPosition(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        new teamLeaders().execute();

    }

    void getAcademiesInformation()
    {

        class Academies extends AsyncTask<String, String, JSONObject> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }
            @Override
            protected JSONObject doInBackground(String... strings) {
                String url = "http://masscash.empirestate.co.za/BeaconAppTest/phpStoredProcedure/API/getAcademiesData.php";

                JSONObject jsonObject = jsonParser.makeHttpGet(url, "GET");
                try {
                    academiesArray = jsonObject.getJSONArray("academies");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return  null;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {

                try {

                    for(int x=0;x<academiesArray.length();x++)
                    {
                        jsonObject = academiesArray.getJSONObject(x);
                        academiesnames = jsonObject.getString("Academy_name");
                        listAcademies.add(academiesnames);

                    }
                    academiesAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listAcademies);
                    spinnerAcademies.setAdapter(academiesAdapter);

                    spinnerAcademies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            selectedAcademy = adapterView.getItemAtPosition(i).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }
        new Academies().execute();

    }

    /*
 * Capturing Camera Image will lauch camera app requrest image capture
 */
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri; // file url to store image/video
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, 100);
    }



    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }


}

