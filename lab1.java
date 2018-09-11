import java.util.Scanner;
class lab1{
public static void squareroot(double a, double b, double c){
	if(a != 0){
			double d = b*b - 4*a*c;
			double b_2a = -b / 2*a;
			double d_2a = (d < 0 ? Math.sqrt(-d) / 2 / a : Math.sqrt(d) / 2 / a );
			if(d<0){
				System.out.println("X1 = " + b_2a + " + i*" + d_2a);
				System.out.println("X2 = " + b_2a + " - i*" + d_2a);
			}
			else{
				System.out.println("X1 = " + (b_2a + d_2a));
				System.out.println("X2 = " + (b_2a - d_2a));
			}
		}
	else if (b!=0) System.out.println("X1 = " + (-c/b));
	else if (c!=0) System.out.println("Mnozhestvo korney!");
	else System.out.println("Korney net!");
}
public static void main(String args[]){
	double a=0, b=0, c=0;
	int alen = args.length;
	if (alen == 0){
		Scanner conin = new Scanner(System.in);
		System.out.println("Vvedite a: ");
		a = conin.nextDouble();
		System.out.println("Vvedite b: ");
		b = conin.nextDouble();
		System.out.println("Vvedite c: ");
		c = conin.nextDouble();
	}
	else if (alen == 1) {
		a = Double.valueOf(args[0]);
	} else if (alen == 2) {
		a = Double.valueOf(args[0]);
		b = Double.valueOf(args[1]);
	} else {
		a = Double.valueOf(args[0]);
		b = Double.valueOf(args[1]);
		c = Double.valueOf(args[2]);
	}
	squareroot(a,b,c);
	}//main
}//class lab1