package distributions;

import primitives.IStatistical;
import primitives.Statistic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: SONY
 * Date: 24.05.12
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
public class  BinomialDistribution implements IStatistical<Double, Double>{

    private ArrayList<Integer> performed;

    private double p;
    private int N;

    private Random rnd;
    public BinomialDistribution(double p, int N) {
        this.p = p;
        this.N = N;
        this.performed = new ArrayList<Integer>();
        this.rnd = new Random(System.currentTimeMillis());
    }

    public int next() {
        double urv[] = new double[N];
        for (int i = 0; i < N; ++i) {
            urv[i] = rnd.nextDouble();
        }
        return this.next(urv);
    }

    public int next(double []urv) {
        int actual = 0;
        for (double t : urv) {
            if (t < p) ++actual;
        }
        performed.add(actual);
        return actual;
    }

    public Statistic<Double> getMean() {
        int M = performed.size();
        double actual = 0;
        for (int t : performed) {
            actual += t;
        }
        return new Statistic<Double>(N *  p, actual / M);
    }

    public Statistic<Double> getDispersion() {
        int M = performed.size() - 1;
        double e = getMean().real;
        double actual = 0;
        for (int t : performed) {
            actual += Math.pow(e - t, 2);
        }
        return new Statistic<Double>((1 - p)  * N * p, actual / M);
    }
}
