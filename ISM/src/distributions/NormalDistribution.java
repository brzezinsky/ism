package distributions;

import primitives.IStatistical;
import primitives.Statistic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Taras_Brzezinsky
 * Date: 5/24/12
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class  NormalDistribution implements IStatistical<Double, Double> {
    private ArrayList<Double> performed;

    private double mu;
    private double D;

    private Random rnd;

    public NormalDistribution(double mu, double D) {
        this.mu = mu;
        this.D = D;
        this.performed = new ArrayList<Double>();
        this.rnd = new Random(System.currentTimeMillis());
    }

    public double next() {
        return this.next(rnd.nextDouble(), rnd.nextDouble());
    }

    public double next(double urv1, double urv2) {
        double actual = Math.sqrt(-2 * Math.log(urv1)) * Math.cos(2 * Math.PI * urv2);
        actual = actual * Math.sqrt(D) + mu;
        performed.add(actual);
        return actual;
    }

    public Statistic<Double> getMean() {
        int N = performed.size();
        double actual = 0;
        for (double t : performed) {
            actual += t;
        }
        return new Statistic<Double>(mu, actual / N);
    }

    public Statistic<Double> getDispersion() {
        int N = performed.size() - 1;
        double e = getMean().real;
        double actual = 0;
        for (double t : performed) {
            actual += Math.pow(e - t, 2);
        }
        return new Statistic<Double>(D, actual / N);
    }
}
