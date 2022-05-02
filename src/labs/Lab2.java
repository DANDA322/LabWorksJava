package labs;

import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) {
//        System.out.print("Метод Хука - Дживиса");
//        Scanner in = new Scanner(System.in);
//        System.out.print("Введите начальную точку x1: ");
//        double x1 = in.nextDouble();
//        System.out.print("Введите начальную точку x2: ");
//        double x2 = in.nextDouble();
//        System.out.print("Введите точность: ");
//        double e = in.nextDouble();
//        System.out.print("Введите шаг: ");
//        double step = in.nextDouble();
//        System.out.print("Введите ускорение: ");
//        double boost = in.nextDouble();
//        System.out.print("Введите коэффициент уменьшения шага: ");
//        double stepReductionRatio = in.nextDouble();

        //hookJeeves2param(x1, x2, e, step, boost, stepReductionRatio);

        System.out.println("\nМетод Хука - Дживса, функция 3 переменных");
        //hookJeeves3param(x1, x2, x3, e, step, boost, stepReductionRatio);
        hookJeeves3param(0, 0, 0, 0.00001, 0.9, 2, 2);
        System.out.print("\nМетод Наискорейшего спуска");
        gradientDescent(0.0,0.0,0.0, 0.00001, 0.1);
    }

    /**
     * Метод Хука-Дживса
     * Стартуем с x0 = x1 - старый базис
     * С каждым шагом проверяем выполнение условия для градиента функции
     * и в качестве базиса берется точка с координатами, полученными в результате удачных шагов
     * x2 - ускоряющий множитель
     * @param ValueX1
     * @param ValueX2
     * @param e
     * @param step
     * @param boost
     * @param stepReductionRatio
     */
    private static void hookJeeves2param(double ValueX1, double ValueX2, double e, double step, double boost, double stepReductionRatio) {
        double x1 = ValueX1, x2 = ValueX2, step_x1 = step, step_x2 = step, new_x1 = 0, new_x2 = 0;
        boolean x_bool = false, y_bool = false;

        while ((step_x1 >= e) || (step_x2 >= e)) {

            if (function(x1, x2) > function(x1 - step_x1, x2)) {
                x1 -= step_x1;
                new_x1 = x1 + step_x1;
                x_bool = true;
            }
            else if (function(x1, x2) > function(x1 + step_x1, x2)) {
                x1 += step_x1;
                new_x1 = x1 - step_x1;
                x_bool = true;
            }

            if (function(x1, x2) > function(x1, x2 - step_x2)) {
                x2 -= step_x2;
                new_x2 = x2 + step_x2;
                y_bool = true;
            }

            else if (function(x1, x2) > function(x1, x2 + step_x2)) {
                x2 += step_x2;
                new_x2 = x2 - step_x2;
                y_bool = true;
            }

            if (x_bool && y_bool) {
                x1 += boost * (x1 - new_x1);
                x2 += boost * (x2 - new_x2);
                x_bool = false;
                y_bool = false;
            }

            if ((function(x1, x2) <= function(x1 + step_x1, x2)) || (function(x1 - step_x1, x2) >= function(x1, x2))) {
                step_x1 = step_x1 / stepReductionRatio;
            }
            if ((function(x1, x2) <= function(x1, x2 + step_x2)) || (function(x1, x2 - step_x2) >= function(x1, x2))) {
                step_x2 = step_x2 / stepReductionRatio;
            }
            System.out.println("x1: " + x1);
            System.out.println("x2: " + x2);
        }
        System.out.println("x1: " + x1);
        System.out.println("x2: " + x2);
        System.out.println("step_x1: " + step_x1);
        System.out.println("step_x2: " + step_x2);
    }


    private static void hookJeeves3param(double ValueX1, double ValueX2, double ValueX3, double e, double step, double boost, double stepReductionRatio) {
        double x1 = ValueX1, x2 = ValueX2, step_x1 = step, step_x2 = step, step_x3 = step, new_x1 = 0, new_x2 = 0, new_x3 = 0;
        double x3 = ValueX3;
        boolean x_bool = false, y_bool = false, z_bool = false;

        while ((step_x1 >= e) || (step_x2 >= e) || (step_x3 >= e)) {

            if (function(x1, x2, x3) > function(x1 - step_x1, x2, x3)) {
                x1 -= step_x1;
                new_x1 = x1 + step_x1;
                x_bool = true;
            }
            else if (function(x1, x2, x3) > function(x1 + step_x1, x2, x3)) {
                x1 += step_x1;
                new_x1 = x1 - step_x1;
                x_bool = true;
            }

            if (function(x1, x2, x3) > function(x1, x2 - step_x2, x3)) {
                x2 -= step_x2;
                new_x2 = x2 + step_x2;
                y_bool = true;
            }
            else if (function(x1, x2, x3) > function(x1, x2 + step_x2, x3)) {
                x2 += step_x2;
                new_x2 = x2 - step_x2;
                y_bool = true;
            }

            if (function(x1, x2, x3) > function(x1, x2, x3- step_x3)) {
                x3 -= step_x3;
                new_x3 = x3 + step_x3;
                z_bool = true;
            }
            else if (function(x1, x2, x3) > function(x1, x2, x3 + step_x3)) {
                x3 += step_x3;
                new_x3 = x3 - step_x3;
                z_bool = true;
            }

            if (x_bool && y_bool && z_bool) {
                x1 += boost * (x1 - new_x1);
                x2 += boost * (x2 - new_x2);
                x3 += boost * (x3 - new_x3);
                x_bool = false;
                y_bool = false;
                z_bool = false;
            }


            if ((function(x1, x2, x3) <= function(x1 + step_x1, x2, x3)) || (function(x1 - step_x1, x2, x3) >= function(x1, x2, x3))) {
                step_x1 = step_x1 / stepReductionRatio;
            }

            if ((function(x1, x2, x3) <= function(x1, x2 + step_x2, x3)) || (function(x1, x2 - step_x2, x3) >= function(x1, x2, x3))) {
                step_x2 = step_x2 / stepReductionRatio;
            }

            if ((function(x1, x2, x3) <= function(x1, x2, x3 + step_x3)) || (function(x1, x2, x3 - step_x3) >= function(x1, x2, x3))) {
                step_x3 = step_x3 / stepReductionRatio;
            }

//            System.out.println("x1: " + x1);
//            System.out.println("x2: " + x2);
//            System.out.println("x3: " + x3);
        }
        System.out.println("x1: " + x1);
        System.out.println("x2: " + x2);
        System.out.println("x3: " + x3);
    }


    public static double function(double x1, double x2)
    {
        return (Math.pow(x1-1, 2) + Math.pow(x2-2, 2));
    }

    public static double function(double x1, double x2, double x3)
    {
        return (Math.pow(x1-1, 2) + Math.pow(x2-2, 2) + Math.pow(x3-3, 2));
    }

    public static double grad_x1(double x1)
    {
        return (2 * x1 - 2);
    }

    public static double grad_x2(double x2)
    {
        return (2 * x2 -4);
    }

    public static double grad_x3(double x3)
    {
        return (2 * x3 - 6);
    }

    private static void gradientDescent(double x1, double x2, double x3, double eps, double h) {

        int k = 0;

        double G, Y, x1_p, x2_p, x3_p;

        do
        {
            x1_p = x1;
            x2_p = x2;
            x3_p = x3;
            G = function(x1, x2, x3);
            x1 = x1_p - h * grad_x1(x1_p);
            x2 = x2_p - h * grad_x2(x2_p);
            x3 = x3_p - h * grad_x3(x3_p);
            Y = function(x1, x2, x3);
            //System.out.println("Y: " + Y);
            k++;
        }
        while ((Math.abs(Y - G)) > eps);

        System.out.println("\nx1= " + x1);
        System.out.println("x2= " + x2);
        System.out.println("x3= " + x3);
    }

}
