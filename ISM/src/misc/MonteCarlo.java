package misc;

import distributions.Functor;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Taras_Brzezinsky
 * Date: 5/24/12
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class MonteCarlo {
    private MonteCarlo(){}

    static double integral(Functor f, double from, double to, int count, double []values) {
        double result  = 0;
        if (values == null) {
            values = new double[count];
            Random rnd = new Random(System.currentTimeMillis());
            for (int i = 0; i < count; ++i) {
                values[i] = from + rnd.nextDouble() / (to  - from);
            }
        }
        double multiplier = (to - from) / count;
        for (double t : values) {
            result += f.calculate(t);
        }
        return result / multiplier;
    }
}
