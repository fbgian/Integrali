//metodo di Simpson
#include <iostream>
#include <cmath>
using namespace std;

double f(double x) {
	return exp(x);
}

int main() {
	double a, b; //input
	int n; //input
	double h,h2, s, sd=0, sp=0, x, area ;

	cout<<"Questo programma fa l'integrale tra 'a' e 'b' della funzione\n";
	cout<<"Inserisci gli estremi 'a' e 'b' \n";
	cin>>a>>b;
	cout<<"Inserisci il numero di intervalli 'n' : ";
	cin>>n;

	h=(b-a)/n;

	if (n%2) n++;

	double fab = (f(a)+f(b));
	h2 = 2*h;
	x = a + h;

	for (int i = 1; i<n; i+=2) {
		sd += f(x);
		x+=h;
		sp += f(x);
		x+=h;
	}
	sd += f(x);

	area = (fab + 4*sd + 2*sp) * (h/3);
	cout<<endl<<area<<endl<<exp(1)<<endl<<exp(1)-1-area;
}
