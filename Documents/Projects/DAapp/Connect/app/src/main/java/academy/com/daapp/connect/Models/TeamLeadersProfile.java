package academy.com.daapp.connect.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import academy.com.daapp.connect.Controllers.JSONParser;

/**
 * Created by TheDigitalAcademy on 16/03/01.
 */
public class TeamLeadersProfile {

    private String TeamLeaders_ID;
    private String Academies_tbl_Academies_ID;
    private String Name;
    private String Email_address;
    private String Phone_numbers;
    private JSONObject JsonTeamLeaders;
    static  TeamLeadersProfile teamLeadersProfile;
    JSONParser jsonParser;


    public static TeamLeadersProfile getInstance()
    {
        if(teamLeadersProfile==null)
        {
            teamLeadersProfile = new TeamLeadersProfile();
        }
        return  teamLeadersProfile;

    }
    public TeamLeadersProfile() {

    }
    public static void setInstance(JSONObject object) throws JSONException {
        teamLeadersProfile = new TeamLeadersProfile(object);
    }

    public TeamLeadersProfile(JSONObject object) {


        String url = "http://masscash.empirestate.co.za/BeaconAppTest/phpStoredProcedure/API/getLeadersData.php";

        object = jsonParser.makeHttpGet(url, "GET");


        try {
            JSONArray teamLeadersArray = object.getJSONArray("posts");

            for(int x=0;x<teamLeadersArray.length();x++)
            {
                JsonTeamLeaders = teamLeadersArray.getJSONObject(x);
                TeamLeaders_ID = JsonTeamLeaders.getString("TeamLeaders_ID");
                Academies_tbl_Academies_ID = JsonTeamLeaders.getString("Academies_tbl_Academies_ID");
                Name = JsonTeamLeaders.getString("Name");
                Email_address = JsonTeamLeaders.getString("Email_address");
                Phone_numbers = JsonTeamLeaders.getString("Phone_numbers");

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void setTeamLeaders(JSONObject jsonObject)
    {




    }


    public String getPhone_numbers() {
        return Phone_numbers;
    }

    public void setPhone_numbers(String phone_numbers) {
        Phone_numbers = phone_numbers;
    }

    public String getEmail_address() {
        return Email_address;
    }

    public void setEmail_address(String email_address) {
        Email_address = email_address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAcademies_tbl_Academies_ID() {
        return Academies_tbl_Academies_ID;
    }

    public void setAcademies_tbl_Academies_ID(String academies_tbl_Academies_ID) {
        Academies_tbl_Academies_ID = academies_tbl_Academies_ID;
    }

    public String getTeamLeaders_ID() {
        return TeamLeaders_ID;
    }

    public void setTeamLeaders_ID(String teamLeaders_ID) {
        TeamLeaders_ID = teamLeaders_ID;
    }






}
