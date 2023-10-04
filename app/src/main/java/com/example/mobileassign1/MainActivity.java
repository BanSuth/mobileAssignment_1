package com.example.mobileassign1;

import static java.lang.Math.ceil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileassign1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; //defining the  binding, Binding is used to acquire the elements of the layout without having to get the ID for each element

    EditText principal, rate, year; //creating EditText variable that will be used in calculate function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater()); //defining the binding
        setContentView(binding.getRoot());


        principal= binding.principleAmount; //defining principal,rate,year input fields.
        rate= binding.interestAmount;
        year= binding.yearsAmount;

        binding.submitButton.setOnClickListener(view -> calculate()); // on click listener function for button to call calculate function
    }


    public void calculate() { //Calculate function, that calculates the mortgage EMI based on the user input

        String prinS=principal.getText().toString(); //Getting user input, and placing into string variables
        String rateS=rate.getText().toString();
        String yearS=year.getText().toString();
        boolean check = false;// Defining check variable, that used for error checking.

        if (TextUtils.isEmpty(prinS) || Integer.parseInt(prinS)<=0){ //If statement to check that user input is not empty or 0.
            principal.setError("Enter loan amount greater than 0"); //Using setError to display to user that there is a problem with their input
            check=true; //Setting check variable to true, so that function will exit.
        }
        if (TextUtils.isEmpty(rateS) || Integer.parseInt(rateS)<=0){ //If statement to check that user input is not empty or 0.
            rate.setError("Enter an interest rate amount greater than 0"); //Using setError to display to user that there is a problem with their input
            check=true; //Setting check variable to true, so that function will exit.
        }
        if (TextUtils.isEmpty(yearS) || Integer.parseInt(yearS)<=0){ //If statement to check that user input is not empty or 0.
            year.setError("Enter a loan term greater than 0"); //Using setError to display to user that there is a problem with their input
            check=true; //Setting check variable to true, so that function will exit.
        }

        if(check){ // if statement to see if check is true, If true then the function will exit.
            binding.outputText.setText(""); //clearing output field
            return;
        }

        float prinV=Float.parseFloat(prinS); //Converting the String value acquire earlier to float
        float rateV=(Float.parseFloat(rateS)/100)/12; // Converting to float and dividing by 100 then 12
        int monthV=Integer.parseInt(yearS)*12; // Converting to Int the multiplying by 12 to get months

        double result= Math.ceil((prinV*rateV*(Math.pow((1+rateV),monthV)))/(Math.pow((1+rateV),monthV)-1)); //Calculating the EMI, then storing in result variable.

        binding.outputText.setText("Monthly Payment: $"+ result); //Displaying result to the user.
    }


}