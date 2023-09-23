public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		coefficients = new double[] {0};
	}

	public Polynomial(double[] arr) {
		coefficients = new double[arr.length];
		for(int i=0; i<arr.length; i++) {
			coefficients[i] = arr[i];
		}
	}

	public Polynomial add(Polynomial p) {
		int max = 0;
		int min = 0;

		if((p.coefficients).length<(this.coefficients).length) {
			max = (this.coefficients).length;
			min = (p.coefficients).length;
		}
		else if((p.coefficients).length>(this.coefficients).length) {
			max = (p.coefficients).length;
			min = this.coefficients.length;
		}
		else {
			max = p.coefficients.length;
			min = max;
		}
		
		double[] arr = new double[max];
		
		for(int i=0; i<max; i++) {
			if(i>=min) {
				if(p.coefficients.length < max) {
					arr[i] = this.coefficients[i];
				}
				else if(this.coefficients.length < max) {
					arr[i] = p.coefficients[i];
				}
			}
			else {
				arr[i] = this.coefficients[i] + p.coefficients[i];
			}
			//System.out.println(arr[i]);
		}
		Polynomial temp = new Polynomial(arr);
		
		return temp;
	}

	public double evaluate(double x) {
		int len = coefficients.length;
		double total = 0;
		int xc = 0;

		for(int i=0; i<len; i++) {
			total += coefficients[i]*(Math.pow(x, xc));
			xc++;
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
}

