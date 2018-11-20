package com.example.shareme.Controller;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shareme.R;

public class Creditcard extends AppCompatActivity {

    private EditText cardHolder, cardno, expdate, cvc;
    private TextInputLayout inputSpinner,inputHolder, inputCardno, inputExpdate, inputCvc;
    private ImageButton save;
    private Spinner spinner;
    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard);

        spinner=findViewById(R.id.spin);
        ArrayAdapter adapter = new ArrayAdapter<String>(Creditcard.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cardtypes));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        inputSpinner = findViewById(R.id.spinnerlayout);
        inputHolder = findViewById(R.id.input_cardholder);
        inputCardno=findViewById(R.id.input_cardno);
        inputExpdate=findViewById(R.id.input_expdate);
        inputCvc=findViewById(R.id.input_cvc);

        cardHolder = findViewById(R.id.cardholderinput);
        cardno= findViewById(R.id.cardnoinput);
        expdate= findViewById(R.id.expdateinput);
        cvc = findViewById(R.id.cvcinput);
        save=findViewById(R.id.savebtn);

        cardHolder.addTextChangedListener(new Creditcard.MyTextWatcher(cardHolder));
        cardno.addTextChangedListener(new Creditcard.MyTextWatcher(cardno));
        expdate.addTextChangedListener(new Creditcard.MyTextWatcher(expdate));
       cvc.addTextChangedListener(new Creditcard.MyTextWatcher(cvc));


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails();


            }
        });

        back = findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }

    private void openActivity() {
        Intent i1 = new Intent(Creditcard.this,FrontPage.class);
        startActivity(i1);
    }

    private void saveDetails(){
        if(!validCardholder()){
            return;
        }

        if(!validCardno()){
            return;
        }

        if(!validExpdate()){
            return;
        }

        if(!validCvc()){
            return;
        }

        if (!validMethod()){
            return;
        }

        Toast.makeText(Creditcard.this, "Your credit card details have been updated and saved!", Toast.LENGTH_SHORT).show();
    }



    private boolean validMethod(){
        if(spinner.getSelectedItem().equals("Choose payment method")){
            inputSpinner.setError(getString(R.string.error_invalid_method));
            requestFocus(spinner);
            return false;
        }
        else{
            inputSpinner.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validCardholder(){
        if(cardHolder.getText().toString().trim().isEmpty()){
            inputHolder.setError(getString(R.string.error_invalid_username));
            requestFocus(cardHolder);
            return false;
        }
        else{
            inputHolder.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validCardno(){
        if(cardno.getText().toString().trim().isEmpty() || !(cardno.getText().toString().trim().length()==16)){
            inputCardno.setError(getString(R.string.error_invalid_cardno));
            requestFocus(cardno);
            return false;
        }

        else{
            inputCardno.setErrorEnabled(false);
        }
        return true;
    }


    private boolean validExpdate(){
        if(expdate.getText().toString().trim().isEmpty()|| !(expdate.getText().toString().trim().length()==4)){
            inputExpdate.setError(getString(R.string.error_invalid_expdate));
            requestFocus(expdate);
            return false;
        }else{
            inputExpdate.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validCvc(){
        if(cvc.getText().toString().trim().isEmpty()){
            inputCvc.setError(getString(R.string.error_invalid_cvc));
            requestFocus(cvc);
            return false;
        }else{
            inputCvc.setErrorEnabled(false);
        }
        return true;
    }


    private void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private class MyTextWatcher implements TextWatcher {
        private View view;
        private  MyTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()){
                case R.id.cardholderinput:
                    validCardholder();
                    break;
                case R.id.cardnoinput:
                    validCardno();
                    break;
                case R.id.expdateinput:
                    validExpdate();
                    break;
                case R.id.cvcinput:
                    validCvc();

            }
        }
    }



}
