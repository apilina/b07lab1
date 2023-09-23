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

		if((p.coefficients).length<(this.coefficients).length) {
			max = (this.coefficients).length;
		}
		else if((p.coefficients).length>(this.coefficients).length) {
			max = (p.coefficients).length;
		}

		return;
	}

	public double evaluate(double x) {
		int len = coefficients.length;
		int total = 0;
		int xc = 0;

		for(int i=0; i<len; i++) {
			total = coefficients[i]*(Math.pow(x, xc));
			xc++;
		}

		return total;
	}

	public boolean hasRoot(double x) {
		//check if x is a root of the polymonial
	}
