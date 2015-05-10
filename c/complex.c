/*
 * created by sijunhe on 05/09/2015
 * Complex Number Algebra
 * Show addition, multiplication, negation, and inversion of complex numbers in separate functions.
 * Print the results for each operation tested.
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct complex{
  double real; //real part of the complex number
  double imag; //imaginary part of the complex number
}complex;

complex add(complex a, complex b){
  complex temp;
  temp.real = a.real + b.real;
  temp.imag = a.imag + b.imag;
  return temp;
}

complex multiply(complex a, complex b){
  complex temp;
  temp.real = (a.real * b.real) - (a.imag * b.imag);
  temp.imag = (a.real * b.imag) + (a.imag * b.real);
  return temp;
}

complex negate(complex a){
	complex temp;
	temp.real = - (a.real);
	temp.imag = - (a.imag);
	return temp;
}

complex inversion(complex a){
	complex temp;
	temp.real = a.real / (a.real * a.real + a.imag * a.imag);
	temp.imag = (- a.imag) / (a.real * a.real + a.imag * a.imag);
	return temp;
}

int main(void) {
	complex a, b;
	a.real = 1;
	a.imag = 2;
	b.real = -3;
	b.imag = 4;

	complex sum = add(a,b);
	complex product = multiply(a,b);
	complex neg = negate(a);
	complex difference = add(a,negate(b));
	complex inverse = inversion(a);

	printf("Testing\n");
	printf("Complex Number a is %f + %f i\n",a.real,a.imag);
	printf("Complex Number a is %f + %f i\n",b.real,b.imag);
	printf("a + b is %f + %f i\n",sum.real,sum.imag);
	printf("a - b is %f + %f i\n",difference.real,difference.imag);
	printf("a * b is %f + %f i\n",product.real,product.imag);
	printf("The negation of a is %f + %f i\n",neg.real,neg.imag);
	printf("The inverse of a is %f + %f i\n",inverse.real,inverse.imag);

}
