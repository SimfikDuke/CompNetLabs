import java.util.Scanner;
class lab1{
public static void squareroot(String ax, String bx, String cx){
		Double a=0.0,b=0.0,c=0.0;
	try{
		a = Double.valueOf(ax);
	}
	catch (java.lang.NumberFormatException e){
		System.out.println("A введен неверно, примем A=0");
	}
	try{
		b = Double.valueOf(bx);
	}
	catch (java.lang.NumberFormatException e){
		System.out.println("B введен неверно, примем B=0");
	}
	try{
		c = Double.valueOf(cx);
	}
	catch (java.lang.NumberFormatException e){
		System.out.println("C введен неверно, примем C=0");
	}
		System.out.println("Корни уравнения для коефициентов: A=" + a +" B=" +b+ " C="+c);
		if(a != 0){
				double d = b*b - 4*a*c;
				double b_2a = -b / 2*a;
				double d_2a = (d < 0 ? Math.sqrt(-d) / 2 / a : Math.sqrt(d) / 2 / a );
				System.out.println("Тип: Квадратное уравнение.");
				System.out.println("Дискриминант равен "+d);
				if(d<0){
					System.out.println("X1 = " + b_2a + " + i*" + d_2a);
					System.out.println("X2 = " + b_2a + " - i*" + d_2a);
				}
				else{
					System.out.println("X1 = " + (b_2a + d_2a));
					System.out.println("X2 = " + (b_2a - d_2a));
				}
			}
		else if (b!=0) {
			System.out.println("Тип: Линейное уравнение.");
			System.out.println("X = " + (-c/b));
		}
		else if (c!=0) System.out.println("Решением является любое число!");
		else System.out.println("Корней нет!");
	System.out.println("\n");
}
public static void main(String args[]){
	String a="0", b="0", c="0";
	int alen = args.length;
	if (alen == 0){
		Scanner conin = new Scanner(System.in);
		System.out.println("Введите a: ");
		a = conin.next();
		System.out.println("Введите b: ");
		b = conin.next();
		System.out.println("Введите c: ");
		c = conin.next();
	}
	else if (alen == 1) {
		a = args[0];
	} else if (alen == 2) {
		a = args[0];
		b = args[1];
	} else {
		a = args[0];
		b = args[1];
		c = args[2];
	}
	squareroot(a,b,c);
	}//main
}//class lab1