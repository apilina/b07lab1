import java.io.File;
import java.util.Scanner;
import java.lang.Math;

public class Polynomial {
	double[] coefficients;
	double[] exponents;

	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new double[] {0};
	}

	public Polynomial(double[] coef, double[] exp) {
		coefficients = new double[coef.length];
		exponents = new double[exp.length];
		
		for(int i=0; i<coef.length; i++) {
			coefficients[i] = coef[i];
		}
		for(int j=0; j<exp.length; j++) {
			exponents[j] = exp[j];
		}
	}

//	public Polynomial(File f) {
//		Scanner sc = new Scanner(f);
//	}
	
	public void printpoly() {
		if(this.coefficients.length == 0) {
			System.out.println("nothing here");
		}
		else {
			for(int i=0; i<this.coefficients.length; i++) {
				System.out.print(this.coefficients[i]+"x^"+this.exponents[i]);
				if(i!=this.coefficients.length-1) {
					System.out.print(" + ");
				}
			}
		}
		System.out.print("\n");
	}
	
	public Polynomial add(Polynomial p) {
		int max = 0;
		int min = 0;
		
		int plen = p.exponents.length;
		int tlen = this.exponents.length;
		
		if(plen == 0) {
			return this;
		}
		else if(tlen == 0) {
			return p;
		}
		//find max size of new poly
		if(p.exponents[plen-1] <= this.exponents[tlen-1]) {
			max = (int)(this.exponents[tlen-1])+1;
			min = plen;
		}
		else {
			max = (int)(p.exponents[plen-1])+1;
			min = tlen;
		}
		
		double[] coeftemp = new double[max];
		double[] exptemp = new double[max];
		int pf = -1;
		int tf = -1;
		
		//add arrays
		for(int i=0; i<max; i++) {
			exptemp[i] = i;
			
			for(int j=0; j<max; j++) {
				if(j==plen) {
					break;
				}
				else {
					if(p.exponents[j] == i) {
						pf = j;
						break;
					}
				}
			}
			
			for(int j=0; j<max; j++) {
				if(j==tlen) {
					break;
				}
				else {
					if(this.exponents[j] == i) {
						tf = j;
						break;
					}
				}
			}
			
			if(pf!=(-1) && tf!=(-1)) {
				coeftemp[i] = p.coefficients[pf]+this.coefficients[tf];
			}
			else if(pf!=(-1)) {
				coeftemp[i] = p.coefficients[pf];
			}
			else if(tf!=(-1)) {
				coeftemp[i] = this.coefficients[tf];
			}
			else {
				coeftemp[i] = 0;
			}
			
			pf = -1;
			tf = -1;
			for(int k=0; k<max; k++) {
				System.out.println(coeftemp[k]+", "+exptemp[k]);
			}
			System.out.print("\n");
		}
		
		int finallen = 0;
		//find len of array without 0s
		for(int i = 0; i<max; i++) {
			if(coeftemp[i] != 0) {
				finallen++;
			}
		}
		
		double[] coef = new double[finallen];
		double[] exp = new double[finallen];
		int index = 0;
		//fill in final arrays without 0s
		for(int i = 0; i<max; i++) {
			if(coeftemp[i] != 0) {
				coef[index] = coeftemp[i];
				exp[index] = exptemp[i];
				index++;
				System.out.println("added "+coeftemp[i]+" and "+exptemp[i]);
			}
		}
		
		Polynomial temp = new Polynomial(coef, exp);
		
		return temp;
	}

	public double evaluate(double x) {
		int len = coefficients.length;
		double total = 0;

		for(int i=0; i<len; i++) {
			total += coefficients[i]*(Math.pow(x, exponents[i]));
			//System.out.println(total);
		}

		return total;
	}

	public boolean hasRoot(double x) {
		//check if x is a root of the polynomial
		if(this.evaluate(x)==0) {
			return true;
		}
		
		return false;
	}
	
	public Polynomial multiply(Polynomial p) {
		int plen = p.exponents.length;
		int tlen = this.exponents.length;
		
		if(plen == 0 || tlen==0) {
			Polynomial temp = new Polynomial();
			return temp;
		}
		
		
		for(int i=0; i<plen)
		return p;
	}
}

