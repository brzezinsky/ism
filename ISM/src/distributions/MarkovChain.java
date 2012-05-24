package distributions;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Taras_Brzezinsky
 * Date: 5/24/12
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */

public class MarkovChain implements IStatistical<Double[], Double[][] > {
    private double[][] Q;
    private double[] pi;
    private Double[] p;
    private Double [][]P;
    private int []cnt;
    private int [][]continuousCnt;
    int performed;
    int prev;
    Random rnd;

    public MarkovChain(int N, Double[][] P, Double[] p) {
        Q = new double[N][N + 1];
        this.P = P;
        this.p = p;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                Q[i][j + 1] = Q[i][j] + P[i][j];
            }
        }
        pi = new double[N + 1];
        for (int i = 0; i < N; ++i) {
            pi[i + 1] = pi[i] + p[i];
        }
        performed = 0;
        this.cnt =  new int[N];
        continuousCnt = new int[N][N];
        prev = -1;
        rnd = new Random(System.currentTimeMillis());
    }

    public int next() {
        return this.next(rnd.nextDouble());
    }

    public int next(double urv) {
        int actual;
        if (prev == -1) {
            actual = find(pi, urv);
        } else {
            actual = find(Q[prev], urv);
            ++continuousCnt[prev][actual];
        }
        ++cnt[actual];
        ++performed;
        prev = actual;
        return actual;
    }

    private static int find(double[] t, double val) {
        for (int can = 0; can < t.length - 1; ++can) {
            if (val  >= t[can] && val < t[can + 1]) return can;
        }
        return t.length - 2;
    }


    public Statistic<Double[]> getMean() {
        Double actual[] = new Double[cnt.length];
        for (int i = 0; i < actual.length; ++i) {
            actual[i] = (double)cnt[i] / performed;
        }
        return new Statistic<Double[]>(p, actual);
    }

    public Statistic<Double[][]> getDispersion() {
        Double [][]actual = new Double[P.length][P.length];
        for (int i = 0; i < actual.length; ++i) {
            for (int j = 0; j < actual.length; ++j) {
                actual[i][j] = (double)(continuousCnt[i][j]) / performed;
            }
        }
        return new Statistic<Double[][]>(P, actual);
    }
}