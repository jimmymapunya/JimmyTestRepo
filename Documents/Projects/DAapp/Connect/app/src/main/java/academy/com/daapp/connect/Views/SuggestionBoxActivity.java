package academy.com.daapp.connect.Views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import academy.com.daapp.connect.R;

/**
 * Created by TheDigitalAcademy on 16/03/07.
 */
public class SuggestionBoxActivity extends Activity implements View.OnClickListener{

    private EditText edtSuggestion;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_box_activity);

        edtSuggestion = (EditText)findViewById(R.id.edtSuggestion);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);






    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btnSubmit)
        {
            String suggestion = edtSuggestion.getText().toString();


        }

    }
}


