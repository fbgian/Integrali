//Copyright GIANCIOTTI SOFTWARE HOUSE 2019

package com.example.integrali;

import android.util.Log;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import static java.lang.Math.abs;

class Algoritmi {

    private Expression e;

    void setFunction(String f) {
        e = new ExpressionBuilder(f).variable("x").build();
    }

    double f(double x) {
        e.setVariable("x", x);
        return e.evaluate();
    }

    private double MetodoTrapeziN(double a, double b, int n) {
        double x = a;
        double h = (b-a)/n;
        double s = (f(a)+f(b))/2;
        for (int i = 0; i<n; i++) {
            x += h;
            s += f(x);
        }
        return s*h;
    }

    /*double MetodoSimpsonN(double a, double b, int n) {

        if ((n%2)==1) n++;
        double sp = 0;
        double sd = 0;
        double h = (b-a)/n;
        double fab = (f(a)+f(b));
        double x = a + h;

        for (int i = 1; i<n; i+=2) {
            sd += f(x);
            x+=h;
            sp += f(x);
            x+=h;
        }
        sd += f(x);

        return (fab + 4*sd + 2*sp) * (h/3);
    }
    */


    double MetodoTrapezi(double a, double b, int prec){
        int n = (int) Math.pow(10,prec);
        return MetodoTrapeziN(a,b,n);
    }

    double MetodoSimpson(double a, double b, int prec) {

        double eps = Math.pow(10,-1*prec);
        int n = (int) Math.pow(10,prec);

        if ((n%2)==1) n++;

        double sp = 0;
        double sd = 0;
        double h = (b-a)/n;
        double fab = (f(a)+f(b));
        double x = a;
        double area;
        double area1;
        double diff;

        for(int i=1; i<(n-2)/2; i++) {
            x+=h;
            sd+=f(x);
            x+=h;
            sp+=f(x);
        }

        x+=h;
        sd+=f(x);
        area = (h/3)*(fab+4*sd+2*sp);

        do {
            sp+=sd;
            sd = 0;
            x=a+h/2;

            for(int i=1; i<n; i++) {
                sd+=f(x);
                x+=h;
            }

            area1 = (fab + 4*sd + 2*sp)*(h/6);
            diff = abs(area1-area);
            area=area1;
            h=h/2;
            n*=2;
            //Log.d("n = ", Integer.toString(n));

        } while(diff>eps);
        return area;
    }



}
