package com.example.staticcurrencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final double InrToUsdValue=0.013;
    private static final double UsdToInrValue=75.98;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner sourceSpinner=(Spinner) findViewById(R.id.sourceCurrencySpinner);
        Spinner targetSpinner=(Spinner) findViewById(R.id.targetCurrencySpinner);

        ArrayAdapter<String> sourceAdapter=new ArrayAdapter<>(
                MainActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.currencies)
        );
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(sourceAdapter);
        targetSpinner.setAdapter(sourceAdapter);
    }
    public void convertCurrency(View view){
        Log.i("info","Value Entered: ");
        EditText sourceVal=(EditText)findViewById(R.id.editText);
        double amountEntered=Double.parseDouble(sourceVal.getText().toString());
        String sourceCurrencyProvider=((Spinner) findViewById(R.id.sourceCurrencySpinner)).getSelectedItem().toString();
        String targetCurrencyProvider=((Spinner) findViewById(R.id.targetCurrencySpinner)).getSelectedItem().toString();
        Log.i("info","Value Entered: "+amountEntered);
        Log.i("info","Source Currency Choosen : "+sourceCurrencyProvider);


        double convertedCurrencyValue=0;
        if(sourceCurrencyProvider.equalsIgnoreCase(targetCurrencyProvider)){
            convertedCurrencyValue=amountEntered;
        }
        else if(sourceCurrencyProvider.equalsIgnoreCase("USD")){
            convertedCurrencyValue=amountEntered*InrToUsdValue;
        }else if(sourceCurrencyProvider.equalsIgnoreCase("INR")){
            convertedCurrencyValue=amountEntered*UsdToInrValue;
        }
        Log.i("info","Convert Value is : "+convertedCurrencyValue);
        Toast.makeText(this, ""+convertedCurrencyValue, Toast.LENGTH_SHORT).show();

        TextView computedTextView=(TextView) findViewById(R.id.textView3);
        computedTextView.setText(sourceCurrencyProvider+"=>"+targetCurrencyProvider+" is "+convertedCurrencyValue+"");
    }
}
