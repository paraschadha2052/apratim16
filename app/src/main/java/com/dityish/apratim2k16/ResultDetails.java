package com.dityish.apratim2k16;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);

        Bundle details=getIntent().getExtras();

        TextView resultName=(TextView) findViewById(R.id.resultName);
        TextView resultTime=(TextView) findViewById(R.id.resultTime);
        TextView resultDetails=(TextView) findViewById(R.id.resultDetails);

        resultName.setText(details.getString("name"));
        resultTime.setText(details.getString("updatedAt"));
        resultDetails.setText(details.getString("result"));

    }
}
