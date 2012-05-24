package distributions;

import primitives.IStatistical;
import primitives.Statistic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Taras_Brzezinsky
 * Date: 5/24/12
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeometricDistribution implements IStatistical<Double, Double> {

    private ArrayList<Integer> performed;

    private double p;

    private Random rnd;

    public GeometricDistribution(double p) {
        this.p = p;
        this.performed = new ArrayList<Integer>();
        this.rnd = new Random(System.currentTimeMillis());
    }

    public int next() {
        return this.next(rnd.nextDouble());
    }

    public int next(double urv) {
        int  actual = (int)(Math.log(urv) / Math.log(1 - p));
        performed.add(actual);
        return actual;
    }

    public Statistic<Double> getMean() {
        int N = performed.size();
        double actual = 0;
        for (int t : performed) {
            actual += t;
        }
        return new Statistic<Double>((1 - p) / p, actual / N);
    }

    public Statistic<Double> getDispersion() {
        int N = performed.size() - 1;
        double e = getMean().real;
        double actual = 0;
        for (int t : performed) {
            actual += Math.pow(e - t, 2);
        }
        return new Statistic<Double>((1 - p) / (p * p), actual / N);
    }
}
