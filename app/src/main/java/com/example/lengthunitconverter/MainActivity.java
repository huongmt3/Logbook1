package com.example.lengthunitconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView m, mm, mi, ft;
    Spinner unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        m = findViewById(R.id.m);
        mm = findViewById(R.id.mm);
        mi = findViewById(R.id.mi);
        ft = findViewById(R.id.ft);
        unit = findViewById(R.id.unit);

        //Length Units for Spinner
        String[] array = {"m", "mm", "mi", "ft"};
        //Set Spinner with Length Units
        unit.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, array));

        //For Spinner
        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                update();
            }
        });
    }

    private void update(){
        //Check whether input is empty or not & unit got selected
        if(!input.getText().toString().isEmpty() && !unit.getSelectedItem().toString().isEmpty()){
            double in  = Double.parseDouble(input.getText().toString());
            switch (unit.getSelectedItem().toString()) {
                case "m":
                    setM(in);
                    break;
                case "mm":
                    setM(in/1000);
                    break;
                case "mi":
                    setM(in*1609.344);
                    break;
                case "ft":
                    setM(in*0.3048);
                    break;
            }
        }
        else {
            m.setText("0");
            mm.setText("0");
            mi.setText("0");
            ft.setText("0");
        }
    }

    //For TextView
    private void setM(double m_in){
        m.setText(String.valueOf(m_in));
        mm.setText(String.valueOf(m_in*1000));
        mi.setText(String.valueOf(m_in/1609.344));
        ft.setText(String.valueOf(m_in/0.3048));
    }
}