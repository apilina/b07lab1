import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Polynomial {
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new int[] {0};
	}

	public Polynomial(double[] coef, int[] exp) {
		coefficients = new double[coef.length];
		exponents = new int[exp.length];
		
		for(int i=0; i<coef.length; i++) {
			coefficients[i] = coef[i];
		}
		for(int j=0; j<exp.length; j++) {
			exponents[j] = exp[j];
		}
	}

	public Polynomial(File file) {
		double[] ctemp = new double[100];
		int[] etemp = new int[100];

       try {
            int ind = 0;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            if(line!=null) {
                String[] terms = line.split("(?=[+-])"); //fancy split terms by + or -
				// https://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
				// https://www.baeldung.com/java-split-string-keep-delimiters
                // https://stackoverflow.com/questions/6707695/iterate-through-string-array-in-java
				for(int i = 0; i<terms.length; i++) {
					String term = terms[i];
//					System.out.println(terms.length);
//					System.out.println(term);
                    if(term.isEmpty()) {
                        continue; //skip empty terms
                    }
//                    System.out.println(term);
                    //System.out.println("term "+term);
                    String[] parts = term.split("x");

					double c = Double.parseDouble(parts[0]);
					if(c!=0) {
	                    if(parts.length==2) {
	                        ctemp[ind] = Double.parseDouble(parts[0]);
	                        etemp[ind] = Integer.parseInt(parts[1]);
							
	                    } else if (parts.length == 1 && term.contains("x")) {
	                        ctemp[ind] = Double.parseDouble(parts[0]);
	                        etemp[ind] = 1;
	                    } else {
	                        ctemp[ind] = Double.parseDouble(term);
	                        etemp[ind] = 0;
	                    }
	                    ind++;
					}
                }
            }

//            for (int i=0; i<ind; i++) {
//                System.out.println("Coefficient: "+coefficients[i]+", Exponent: "+exponents[i]);
//            }
            reader.close();
            }
       catch(IOException e) {
            e.printStackTrace();
        }
       
       int finallen = 0;
		//find len of array without 0s
		for(int i = 0; i<100; i++) {
			if(ctemp[i] != 0) {
				finallen++;
//				System.out.println(finallen+" "+ctemp[i]+" "+etemp[i]);
			}
		}
		
		coefficients = new double[finallen];
		exponents = new int[finallen];
		int index = 0;
		//fill in final arrays without 0s
		for(int i = 0; i<100; i++) {
			if(ctemp[i] != 0) {
				coefficients[index] = ctemp[i];
				exponents[index] = etemp[i];
				index++;
//				System.out.println("added "+ctemp[i]+" and "+etemp[i]);
			}
		}
	}
	
	public void printpoly() {
		if(this.coefficients.length == 0) {
			System.out.println("nothing here");
		}
//		else {
			for(int i=0; i<this.coefficients.length; i++) {
				System.out.print(this.coefficients[i]+"x^"+this.exponents[i]);
				if(i!=this.coefficients.length-1) {
					System.out.print(" + ");
				}
			}
		//}
		System.out.print("\n");
	}
	
	public Polynomial add(Polynomial p) {
		int max = 0;
		
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
		}
		else {
			max = (int)(p.exponents[plen-1])+1;
		}
		
		double[] coeftemp = new double[max];
		int[] exptemp = new int[max];
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
//			for(int k=0; k<max; k++) {
//				System.out.println(coeftemp[k]+", "+exptemp[k]);
//			}
//			System.out.print("\n");
		}
		
		int finallen = 0;
		//find len of array without 0s
		for(int i = 0; i<max; i++) {
			if(coeftemp[i] != 0) {
				finallen++;
			}
		}
		
		double[] coef = new double[finallen];
		int[] exp = new int[finallen];
		int index = 0;
		//fill in final arrays without 0s
		for(int i = 0; i<max; i++) {
			if(coeftemp[i] != 0) {
				coef[index] = coeftemp[i];
				exp[index] = exptemp[i];
				index++;
//				System.out.println("added "+coeftemp[i]+" and "+exptemp[i]);
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
		
		int max = (int)(p.exponents[plen-1]+this.exponents[tlen-1])+1;
		
		double[] coeftotal = new double[max];
		int[] exptotal = new int[max];
		Polynomial total = new Polynomial(coeftotal, exptotal);
		
		double[] coeftemp = new double[max];
		int[] exptemp = new int[max];
		Polynomial temp = new Polynomial(coeftemp, exptemp);
		
		int order = -1;
		
		for(int i=0; i<max; i++) {
			total.exponents[i] = i;
			temp.exponents[i] = i;
		}
		
		
		for(int i=0; i<plen; i++) {
			for(int j=0; j<tlen; j++) {
				order = (int)(p.exponents[i]+this.exponents[j]);
				temp.coefficients[order] = p.coefficients[i]*this.coefficients[j];
			}
//			System.out.println("looooop\n");
			//temp.printpoly();
			total = total.add(temp);
//			System.out.println("smh\n");
			for(int k=0; k<max; k++) {
				temp.coefficients[k] = 0;
			}
			order = -1;
//			System.out.println("end of loop\n");
		}
		
		int finallen = 0;
		//find len of array without 0s
		for(int i = 0; i<max; i++) {
			if(total.coefficients[i] != 0) {
				finallen++;
			}
		}
		
		double[] coef = new double[finallen];
		int[] exp = new int[finallen];
		int index = 0;
		//fill in final arrays without 0s
		for(int i = 0; i<max; i++) {
			if(total.coefficients[i] != 0) {
				coef[index] = total.coefficients[i];
				exp[index] = total.exponents[i];
				index++;
//				System.out.println("added "+total.coefficients[i]+" and "+total.exponents[i]);
			}
		}
		
		Polynomial newp = new Polynomial(coef, exp);
		
		return newp;
	}

	public void saveToFile(String name) {
        try {
            File file = new File(name);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            double coefficient = 0;
            int exponent = 0;
            
            for(int i=0; i<this.coefficients.length; i++) {
            	coefficient = this.coefficients[i];
                exponent = this.exponents[i];
                
                if(coefficient<0) {
                    bufferedWriter.write("-");
                    coefficient = 0-(coefficient);
                }
                else if(i!=0) {
                    bufferedWriter.write("+");
                }

                bufferedWriter.write(Double.toString(coefficient));

                if(coefficient!=0) {
                	if(exponent!=0) {
                		bufferedWriter.write("x"+exponent);
                	}
                }
            }

            bufferedWriter.close();
//            System.out.println("poly saved to "+name);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
	
}


