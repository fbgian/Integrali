package com.example.integrali;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button calcola;
    EditText estrA, estrB, numint;
    TextView risultato;
    Spinner spint;
    EditText insFunz;
    Algoritmi algo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcola = findViewById(R.id.calcola);
        estrA = findViewById(R.id.estrA);
        estrB = findViewById(R.id.estrB);
        numint = findViewById(R.id.numint);
        risultato = findViewById(R.id.risultato);
        spint = findViewById(R.id.spint);
        insFunz = findViewById(R.id.insFunz);

        algo = new Algoritmi();


        calcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                double a = Double.parseDouble(estrA.getText().toString());
                double b = Double.parseDouble(estrB.getText().toString());
                int n = Integer.parseInt(numint.getText().toString());
                String f = insFunz.getText().toString();

                algo.setFunction(f);

                double area;

                switch (spint.getSelectedItemPosition()){
                    case 0: area=algo.MetodoTrapezi(a,b,n); break;
                    case 1: area=algo.MetodoSimpson(a,b,n); break;
                    default: area=0;
                }

                DecimalFormat df = new DecimalFormat("#.######");
                risultato.setText(df.format(area));
            }


        });

    }
}
