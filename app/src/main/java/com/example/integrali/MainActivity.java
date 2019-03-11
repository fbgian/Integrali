package com.example.integrali;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

                double a,b;
                int n;

                try {
                    a = Double.parseDouble(estrA.getText().toString());
                    b = Double.parseDouble(estrB.getText().toString());
                    n = Integer.parseInt(numint.getText().toString());
                } catch (Exception e){
                    risultato.setText("Inserisci valori validi");
                    return;
                }


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
