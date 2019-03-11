//Copyright GIANCIOTTI SOFTWARE HOUSE 2019

package com.example.integrali;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

class Algoritmi {

    private Expression e;

    void setFunction(String f) {
        e = new ExpressionBuilder(f).variable("x").build();
    }

    double f(double x) {
        e.setVariable("x", x);
        return e.evaluate();
    }

    double MetodoTrapezi(double a, double b, int n) {
        double x = a;
        double h = (b-a)/n;
        double s = (f(a)+f(b))/2;
        for (int i = 0; i<n; i++) {
            x += h;
            s += f(x);
        }
        return s*h;
    }

    double MetodoSimpson(double a, double b, int n) {

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



}
