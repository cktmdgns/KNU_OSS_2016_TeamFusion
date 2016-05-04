package mobilelecture.cdp12_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class UserInformActivity extends AppCompatActivity {

    private DBManager dbManager = null;
    private String zender = "";
    private String age = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_inform);

        dbManager = new DBManager(getApplicationContext(), "test.db", null, 1);


        String spinner_zender_array[] = {"남성", "여성"};
        ArrayAdapter<String> spinner_adapter_zender = new ArrayAdapter<String>(this, R.layout.spinner_item, spinner_zender_array);

        String spinner_age_array[] = {"10~19", "20~29", "30~39" , "40~49" , "50~59"};
        ArrayAdapter<String> spinner_adapter_age = new ArrayAdapter<String>(this, R.layout.spinner_item, spinner_age_array);

        spinner_adapter_zender.setDropDownViewResource(R.layout.spinner_item);
        final Spinner spinner_zender = (Spinner) findViewById(R.id.spinner_zender_inform);
        spinner_zender.setAdapter(spinner_adapter_zender);

        spinner_adapter_age.setDropDownViewResource(R.layout.spinner_item);
        Spinner spinner_age = (Spinner) findViewById(R.id.spinner_age_inform);
        spinner_age.setAdapter(spinner_adapter_age);

        spinner_zender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                zender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                age = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button button_start = (Button) findViewById(R.id.button_start_inform);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.insert_inform(zender, age);

                Intent intent_out_inform = getIntent();
                if( dbManager.select_IsThereInform().equals("0")) {
                    intent_out_inform.putExtra("Inform_exist","false");
                }
                else {
                    intent_out_inform.putExtra("Inform_exist","true");
                }
                setResult(RESULT_OK, intent_out_inform);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_inform, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
