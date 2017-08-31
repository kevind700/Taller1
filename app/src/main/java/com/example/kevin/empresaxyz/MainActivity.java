package com.example.kevin.empresaxyz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private EditText quantity;
    private Resources resources;
    private RadioButton dollar, peso;
    private Spinner material, pendant, type;
    private String mat[], pend[], typ[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView)findViewById(R.id.lblResult);
        quantity = (EditText)findViewById(R.id.txtQuantity);
        resources = this.getResources();
        dollar = (RadioButton)findViewById(R.id.rbDollars);
        peso = (RadioButton)findViewById(R.id.rbPesos);
        material = (Spinner)findViewById(R.id.cmbMaterial);
        pendant = (Spinner)findViewById(R.id.cmbPendant);
        type = (Spinner)findViewById(R.id.cmbPendantMaterial);
        mat = resources.getStringArray(R.array.material);
        pend = resources.getStringArray(R.array.pendant);
        typ = resources.getStringArray(R.array.pendantMaterial);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mat);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pend);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typ);
        material.setAdapter(adapter);
        pendant.setAdapter(adapter2);
        type.setAdapter(adapter3);
    }

    public boolean validate(){
        if (quantity.getText().toString().isEmpty()){
            quantity.setError(resources.getString(R.string.message_error_quantity));
            return false;
        }else{
            if (Integer.parseInt(quantity.getText().toString()) == 0){
                quantity.setError(resources.getString(R.string.message_error_not0));
                return false;
            }
        }



        if (!dollar.isChecked() && !peso.isChecked()){
            Toast.makeText(this, R.string.message_error_checkbox, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void calculate(View v){
        int optionMaterial, optionPendant, optionType, quanty;
        double totalResult = 0, product;
        boolean doll;
        result.setText("");

        if (validate()){
            quanty = Integer.parseInt(quantity.getText().toString());
            optionMaterial = material.getSelectedItemPosition();
            optionPendant = pendant.getSelectedItemPosition();
            optionType = type.getSelectedItemPosition();
            doll = dollar.isChecked();
            product = (doll)?1:3200;

            if (optionMaterial == 0 && optionPendant == 0 && optionType == 0){
                totalResult = quanty * (100*product);
            }else if(optionMaterial == 0 && optionPendant == 0 && optionType == 1){
                totalResult = quanty * (80*product);
            }else if(optionMaterial == 0 && optionPendant == 0 && optionType == 2){
                totalResult = quanty * (70*product);
            }else if(optionMaterial == 0 && optionPendant == 1 && optionType == 0){
                totalResult = quanty * (120*product);
            }else if(optionMaterial == 0 && optionPendant == 1 && optionType == 1){
                totalResult = quanty * (100*product);
            }else if(optionMaterial == 0 && optionPendant == 1 && optionType == 2){
                totalResult = quanty * (90*product);
            }else if(optionMaterial == 1 && optionPendant == 0 && optionType == 0){
                totalResult = quanty * (90*product);
            }else if(optionMaterial == 1 && optionPendant == 0 && optionType == 1){
                totalResult = quanty * (70*product);
            }else if(optionMaterial == 1 && optionPendant == 0 && optionType == 2){
                totalResult = quanty * (50*product);
            }else if(optionMaterial == 1 && optionPendant == 1 && optionType == 0){
                totalResult = quanty * (110*product);
            }else if(optionMaterial == 1 && optionPendant == 1 && optionType == 1){
                totalResult = quanty * (90*product);
            }else if(optionMaterial == 1 && optionPendant == 1 && optionType == 2){
                totalResult = quanty * (80*product);
            }

            result.setText("" + String.format("%.2f", totalResult) + ((doll)?" Dolares":" Pesos"));
        }
    }

    public void clear(View v){
        quantity.setText("");
        material.setSelection(0);
        pendant.setSelection(0);
        type.setSelection(0);
        dollar.setChecked(false);
        peso.setChecked(false);
        result.setText("");
    }
}
