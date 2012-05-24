package distributions;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: SONY
 * Date: 25.05.12
 * Time: 2:03
 * To change this template use File | Settings | File Templates.
 */

//TODO работает только для эллипсов (x - c1)^2 / a1 + (y - c2) ^ 2 / a2 = 1
public class UniformDistribution2D {
    double a[];
    double c[];
    private Random rnd;
    
    public UniformDistribution2D(double a1, double a2, double c1, double c2) {
        a = new double[2];
        c = new double[2];
        a[0] = a1;
        a[1] = a2;
        c[0] = c1;
        c[1] = c2;
        rnd = new Random(System.currentTimeMillis());
    }
    
    public double[] next() {
        return next(rnd.nextDouble(), rnd.nextDouble());
    }

    public double[]  next(double urv1, double urv2) {
        double n1 = Math.sqrt(urv1) * Math.cos(2 * Math.PI * urv2);
        double n2 = Math.sqrt(urv1) * Math.sin(2 * Math.PI * urv2);
        double []result = new double[2];
        result[0] = Math.sqrt(a[0]) * n1 + c[0];
        result[1] = Math.sqrt(a[1]) * n2 + c[1];
        return result;
    }
}
