package com.kalu.cynthia.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kalu.cynthia.R;


public class SubmissionActivity extends AppCompatActivity {

    ImageView back;
    Button confirm;


    EditText firstname,lastname,emailaddress,projectlink;
    private String mFirstName;
    private String mLastName;
    private String mEmailAddress;
    private String mProjectLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        back = findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firstname = findViewById(R.id.edt_txt_first_name);
        lastname = findViewById(R.id.edt_txt_second_name);
        emailaddress = findViewById(R.id.edt_txt_email_address);
        projectlink = findViewById(R.id.edt_txt_link);

        confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirstName = firstname.getText().toString();
                mLastName = lastname.getText().toString();
                mEmailAddress = emailaddress.getText().toString();
                mProjectLink = projectlink.getText().toString();


                if (!isEmpty(mFirstName) && !isEmpty(mLastName) && !isEmpty(mEmailAddress) && !isEmpty(mProjectLink)){

                    Bundle bundle = new Bundle();
                    bundle.putString("fname", mFirstName);
                    bundle.putString("lname", mLastName);
                    bundle.putString("eaddress", mEmailAddress);
                    bundle.putString("plink", mProjectLink);

                ConfirmFragmet confirmFragmet = new ConfirmFragmet();
                confirmFragmet.setArguments(bundle);
                confirmFragmet.show(getSupportFragmentManager(),"ConfirmFragment");
                }

                else {
                    Toast.makeText(SubmissionActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean isEmpty(String text) {
        return text.equals("");
    }

}
