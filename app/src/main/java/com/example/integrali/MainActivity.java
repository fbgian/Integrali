//Copyright GIANCIOTTI SOFTWARE HOUSE 2019
package com.example.integrali;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button calcola;
    EditText estrA, estrB, numint, epsilon;
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
        epsilon = findViewById(R.id.epsilon);
        risultato = findViewById(R.id.risultato);
        spint = findViewById(R.id.spint);
        insFunz = findViewById(R.id.insFunz);

        algo = new Algoritmi();


        calcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double a,b;
                int prec;

                try {
                    a = Double.parseDouble(estrA.getText().toString());
                    b = Double.parseDouble(estrB.getText().toString());
                    prec = Integer.parseInt(epsilon.getText().toString());
                    if (prec>6) prec=6;

                } catch (Exception e){
                    risultato.setText(getString(R.string.error));
                    return;
                }

                String f = insFunz.getText().toString();
                algo.setFunction(f);
                double area;

                switch (spint.getSelectedItemPosition()){
                    case 0: area=algo.MetodoTrapezi(a, b, prec); break;
                    case 1: area=algo.MetodoSimpson(a, b, prec); break;
                    default: area=0;
                }

                DecimalFormat df = new DecimalFormat("#.000000");
                risultato.setText(df.format(area));
            }


        });

        epsilon.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_DONE){
                    calcola.callOnClick();
                    InputMethodManager imm = (InputMethodManager)getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                    v.clearFocus();
                    return true;
                }
                else return false;
            }
        });

    }
}
