package distributions;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: SONY
 * Date: 25.05.12
 * Time: 2:18
 * To change this template use File | Settings | File Templates.
 */
public class RandomWalk {
    double [][]px;
    ArrayList<Double[]> x;
    int dimension;
    double step;
    Random rnd;

    public RandomWalk(double step, double pplus[], double pminus[], Double []start) {
        this.step = step;
        this.x = new ArrayList<Double[]>();
        this.dimension = pplus.length;
        px = new double[dimension][2];
        for (int i = 0; i < dimension; ++i) {
            px[i][0] = pplus[i];
            px[i][1] = px[i][0] + pminus[i];
        }
        x.add(start);
        this.rnd = new Random(System.currentTimeMillis());
    }
    
    public Double[] nextPoint() {
        double []urv = new double[dimension];
        for (int i = 0; i < dimension; ++i) {
            urv[i] = rnd.nextDouble();
        }
        return nextPoint(urv);
    }
    
    public Double[] nextPoint(double urv[]) {
        Double prev[] = x.get(x.size() - 1);
        Double []next = new Double[dimension];
        for (int i = 0; i < dimension; ++i) {
            double a = urv[i];
            if (a < px[i][0]) {
                next[i] = prev[i] + step;  
            }  else if (a < px[i][1]) {
                next[i] = prev[i] - step;
            } else {
                next[i] = prev[i];
            }
        }
        x.add(next);
        return next;
    }
}
