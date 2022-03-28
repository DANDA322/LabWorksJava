package Labs;

import java.util.Arrays;
import java.util.Scanner;

public class Task {

    public static void main(String[] args) {
        System.out.print("Задайте начальное значение: ");
        Scanner in = new Scanner(System.in);
        double x0 = in.nextDouble();
        System.out.println("Задайте шаг h:");
        double h = in.nextDouble();
        System.out.println("Задайте точность eps:");
        double eps = in.nextDouble();
        double x = Interpolation(x0, eps, h);
        double y = f(x);
        System.out.println(x + " " + y);
    }

    private static double f(Double x){
        return 2 * Math.pow(x, 2) - Math.exp(x);
    }

    /**
     * Алгоритм квадратичной интерполяции
     * для поиска минимума функции
     * @param x1 начальная точка
     * @param eps заданная точность
     * @param h заданный шаг
     * @return минимум функции
     */
    private static double Interpolation(double x1, double eps, double h){
        double x2 = x1 + h;
        double x3;
        if(f(x1) < f(x2)){
            x3 = x1 - h;
        } else {
            x3 = x1 + 2 * h;
        }

        double DN = (x2 - x3) * f(x1) + (x3 - x1) * f(x2)
                + (x1 - x2) * f(x3);
        double NM = (Math.pow(x2, 2) - Math.pow(x3, 2)) * f(x1)
                + (Math.pow(x3, 2) - Math.pow(x1, 2)) * f(x2)
                + (Math.pow(x1, 2) - Math.pow(x2, 2)) * f(x3);
        double x4 = NM / (2 * DN);

        double[] points = Arrays.stream(new double[]{x1, x2, x3, x4}).sorted().toArray();
        x1 = points[0]; x2 = points[1]; x3 = points[2]; x4 = points[3];

        while(Math.abs(x2 - x3) >= eps){
            if((x2 - x1) == (x3 - x1) && (x2 - x1) == -(x4 - x1)){
                x3 = x4;
            }

            DN = (x2 - x3) * f(x1) + (x3 - x1) * f(x2)
                    + (x1 - x2) * f(x3);
            NM = (f(x1) - f(x2)) / (2 * DN)
                    * (x2 - x3) * (x3 - x1);
            x4 = (x1 + x2) / 2 + NM;

            points = Arrays.stream(new double[]{x1, x2, x3, x4}).sorted().toArray();
            x1 = points[0]; x2 = points[1]; x3 = points[2]; x4 = points[3];
        }
        return x3;
    }




}
