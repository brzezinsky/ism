package usages; /**
 * Created by IntelliJ IDEA.
 * User: Taras_Brzezinsky
 * Date: 5/24/12
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */

import primitives.Functor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IntegralUsage extends Thread {
    public IntegralUsage(int type) {
        try {
            if (type == 0) {
                this.input = new BufferedReader(new InputStreamReader(System.in));
                this.output = new PrintWriter(System.out);
            } else {
                this.input = new BufferedReader(new FileReader(INPUT_FILE));
                this.output = new PrintWriter(OUTPUT_FILE);
            }
            this.setPriority(Thread.MAX_PRIORITY);
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(666);
        }
    }

    private void solve() throws Throwable {
        //trying to solve int{-2, 4} (x ^ 2) = x ^ 3 / 3 |(-2, 4) =  24

        double expected = 24, real = misc.MonteCarlo.integral(new Functor() {
            public double calculate(double... params) {
                double x = params[0];
                return x * x;
            }
        }, -2, 4, 20000000, null);
        output.println(real + "\t" + expected);

    }

    public void run() {
        try {
            solve();
        } catch (Throwable e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(666);
        } finally {
            output.flush();
            output.close();
        }
    }

    public static void main(String[] args) {
        new IntegralUsage(0).start();
    }

    private int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    private double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    private String nextToken() throws IOException {
        while (tokens == null || !tokens.hasMoreTokens()) {
            tokens = new StringTokenizer(input.readLine());
        }
        return tokens.nextToken();
    }

    private static final String INPUT_FILE = null;
    private static final String OUTPUT_FILE = null;
    private BufferedReader input;
    private PrintWriter output;
    private StringTokenizer tokens = null;
}