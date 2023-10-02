public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		
		double [] c1 = {6,5};
		double [] e1 = {0,3};
		Polynomial p1 = new Polynomial(c1, e1);
		p1.printpoly();
		
		double [] c2 = {-2,-9};
		double [] e2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		p2.printpoly();
		
		double [] c3 = {3,-2, 1};
		double [] e3 = {1, 3, 4};
		Polynomial p3 = new Polynomial(c3, e3);
		p3.printpoly();
		
		double [] c4 = {};
		double [] e4 = {};
		Polynomial p4 = new Polynomial(c4, e4);
		p4.printpoly();
		
//		Polynomial s = p2.add(p3);
//		s.printpoly();
		Polynomial s = p1.add(p2);
		s.printpoly();
		
		Polynomial t = p1.add(p4);
		t.printpoly();
		
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(0.1))
			System.out.println("0.1 is a root of s");
		else
			System.out.println("0.1 is not a root of s");
		
		System.out.println("s(1) = " + s.evaluate(1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		
//		Polynomial p = new Polynomial();
//		System.out.println(p.evaluate(3));
//		double [] c1 = {6,0,0,5};
//		Polynomial p1 = new Polynomial(c1);
//		double [] c2 = {0,-2,0,0,-9};
//		Polynomial p2 = new Polynomial(c2);
//		Polynomial s = p1.add(p2);
//		System.out.println("s(0.1) = " + s.evaluate(0.1));
//		if(s.hasRoot(1))
//			System.out.println("1 is a root of s");
//		else
//			System.out.println("1 is not a root of s");
	}
}