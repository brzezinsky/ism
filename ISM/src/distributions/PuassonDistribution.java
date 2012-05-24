package distributions;

import primitives.IStatistical;
import primitives.Statistic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: SONY
 * Date: 25.05.12
 * Time: 0:28
 * To change this template use File | Settings | File Templates.
 */
public class PuassonDistribution implements IStatistical<Double, Double>{

    private double lambda;
    private int MAX_SIZE;
    private Random rnd;
    private double constant;

    ArrayList<Integer> performed;

    public PuassonDistribution(double lambda, int MAX_SIZE) {
        this.lambda = lambda;
        this.MAX_SIZE = MAX_SIZE;
        this.rnd = new Random(System.currentTimeMillis());
        this.performed = new ArrayList<Integer>();
    }

    public int next() {
        double urv[] = new double[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; ++i) {
            urv[i] = rnd.nextDouble();
        }
        return this.next(urv);
    }

    public int next(double []urv) {
        int actual = 0;
        double has = Math.log(urv[0]);
        while (actual < MAX_SIZE && has >= -lambda) {
            ++actual;
            has += Math.log(urv[actual]);
        }
        performed.add(actual);
        return actual;
    }

    public Statistic<Double> getMean() {
        int N = performed.size();
        double actual = 0;
        for (int t : performed) {
            actual += t;
        }
        return new Statistic<Double>(lambda, actual / N);
    }

    public Statistic<Double> getDispersion() {
        int N = performed.size() - 1;
        double e = getMean().real;
        double actual = 0;
        for (int t : performed) {
            actual += Math.pow(e - t, 2);
        }
        return new Statistic<Double>(lambda, actual / N);
    }
}
