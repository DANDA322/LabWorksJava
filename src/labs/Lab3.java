package labs;

public class Lab3 {

    public static void main(String[] args) {
        double a = 0.02;
        double b = 0.2;
        int n = 50;

        for(int i = 0; i < 10; i++){
            System.out.println("n = " + n);
            System.out.println("Метод левых треугольников = " + leftRectangles(a,b,n));
            System.out.println("Метод правых треугольников = " + rightRectangles(a,b,n));
            System.out.println("Метод средних треугольников = " + middleRectangles(a,b,n));
            System.out.println("Метод трапеций = " + trapezoids(a,b,n));
            System.out.println("Метод Симпсона = " + simpsonsMethod(a,b,n));
            System.out.println();
            n += 50;
        }

    }

    /***
     * Исходная функция, для вычисления интеграла
     * @param x - точка x
     * @return значение функции в точке x
     */
    public static double function(double x){
        return Math.sqrt(x + 1) * Math.cos(x * x);
    }

    /***
     * Метод левых треугольников
     * @param a - левая граница
     * @param b - правая граница
     * @param n - равных частей - элементарных отрезков
     * @return значение определенного интеграла
     */
    public static double leftRectangles(double a, double b, int n){
        double h = (b - a) / n;
        double result = function(a);
        for(int i = 1; i < n; i++){
            result += function(a + h * i);
        }
        return result * h;
    }

    /***
     * Метод правых треугольников
     * @param a - левая граница
     * @param b - правая граница
     * @param n - равных частей - элементарных отрезков
     * @return значение определенного интеграла
     */
    public static double rightRectangles(double a, double b, double n){
        double h = (b - a) / n;
        double result = function(b);
        for(int i = 1; i < n; i++){
            result += function(a + h * i);
        }
        return result * h;
    }

    /***
     * Метод средних треугольников
     * @param a - левая граница
     * @param b - правая граница
     * @param n - равных частей - элементарных отрезков
     * @return значение определенного интеграла
     */
    public static double middleRectangles(double a, double b, double n){
        double h = (b - a) / n;
        double result = 0;
        for(int i = 0; i < n; i++){
            result += function(a + h * (i + 0.5));
        }
        return result * h;
    }

    /***
     * Метод трапеций
     * @param a - левая граница
     * @param b - правая граница
     * @param n - равных частей - элементарных отрезков
     * @return значение определенного интеграла
     */
    public static double trapezoids(double a, double b, double n){
        double h = (b - a) / n;
        double y0 = function(a);
        double yn = function(b);
        double result = y0 + yn;
        for(int i = 1; i < n; i++){
            result += function(a + h * i);
        }
        result *= h;
        result -= h * (y0 + yn) / 2;
        return result;
    }

    /***
     * Метод Симпсона
     * @param a - левая граница
     * @param b - правая граница
     * @param n - равных частей - элементарных отрезков
     * @return значение определенного интеграла
     */
    public static double simpsonsMethod(double a, double b, double n){
        double h = (b - a) / n;
        double y0 = function(a);
        double yn = function(b);
        double result = y0 + yn;
        for(int i = 1; i < n; i++){
            if(i % 2 ==  0)
                result += 2 * function(a + h * i);
            else
                result += 4 * function(a + h * i);
        }
        result *= h / 3;
        return result;
    }
}
