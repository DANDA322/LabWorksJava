package Labs;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Lab1 {

    public static void main(String[] args) {
        while (true){
            Lab1 method = new Lab1();
            Scanner in = new Scanner(System.in);
            System.out.println("Выберите метод для поиска минимума (1...3)");
            System.out.println("1.Метод бисекции.");
            System.out.println("2.Метод Фибоначи.");
            System.out.println("3.Метод Ньютона.");
            System.out.println("4.Выход из программы.");
            Integer number = in.nextInt();

            switch (number){
                case 1:
                    method.bisection();
                    break;
                case 2:
                    method.fibonacci();
                    break;
                case 3:
                    method.newton();
                    break;
                default:
                    System.out.println("Выход из программы.");
                    return;
            }
        }
    }

    //private double eps = 0.01;
    //private double newtonEps = 0.000001;

    private void bisection() {
        System.out.println("1.Метод бисекции.");
        Scanner in = new Scanner(System.in);
        System.out.println("Введите интервал (a...b):");
        System.out.print("a = ");
        double a = in.nextDouble();
        System.out.print("b = ");
        double b = in.nextDouble();
        System.out.println("Введите интервал eps:");
        double eps = in.nextDouble();
        bisectionCalc(a,b,eps);
    }

    private void bisectionCalc(double a, double b, double eps){
        double x, function, delta = 1;
        int n = 0;
        while (delta > eps){
            n++;
            x = (a + b) / 2;
            delta = (b - a) / 2;
            function = 3 * Math.pow(x, 2) - 6;
            a = (function < 0) ? x : a;
            b = (function >= 0) ? x : b;
        }
        System.out.println("Минимум функции методом Биссекции = " + b);
        System.out.println("Количество вычислений функции от заданной точности = " + n);
    }


    private void fibonacci() {
        System.out.println("2.Метод Фибоначи.");
        Scanner in = new Scanner(System.in);
        System.out.println("Введите интервал (a...b):");
        System.out.print("a = ");
        double a = in.nextDouble();
        System.out.print("b = ");
        double b = in.nextDouble();
        System.out.print("n = ");
        int n = in.nextInt();

        fibonacciCalc(a,b,n);
    }

    private void fibonacciCalc(double a, double b, int n){
        double[] fibonaciArray = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
        int j = fibonaciArray.length - 1;
        double delta, alpha = 0, fAlpha = 0, beta, fBeta;
        for (int i = 0; i < n; i++, j--) {
            delta = b - a;
            alpha = a + (fibonaciArray[j - 2] / fibonaciArray[j]) * delta;
            beta = a + (fibonaciArray[j - 1] / fibonaciArray[j]) * delta;
            fAlpha = Math.pow(alpha, 3) - 6 * alpha + 2;
            fBeta = Math.pow(beta, 3) - 6 * beta + 2;
            a = (fAlpha > fBeta) ? alpha : a;
            b = (fAlpha <= fBeta) ? beta : b;
        }
        System.out.printf("Минимум функции методом Фибоначчи в точке (%f;%f)\n%n", alpha, fAlpha);
    }

    private void newton(){
        System.out.println("3.Метод Ньютона.");
        Scanner in = new Scanner(System.in);
        System.out.println("Введите x:");
        System.out.print("x = ");
        double x = in.nextDouble();
        System.out.println("Введите интервал eps:");
        double eps = in.nextDouble();
        newtonCalc(x, eps);
    }


    private void newtonCalc(double x, double eps) {
        double xn, firstDer, secDer, dif = 1;
        int n = 0;
        while (dif > eps) {
            n++;
            firstDer = 3 * Math.pow(x, 2) - 6;
            secDer = 6 * x;
            xn = x;
            x = x - firstDer / secDer;
            dif = Math.abs(x - xn);
        }
        System.out.println("Минимум функции методом Ньютона = " + x);
        System.out.println("Количество вычислений функции от заданной точности = " + n);
    }


}
