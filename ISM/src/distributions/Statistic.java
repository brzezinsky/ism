package distributions;

/**
 * Created by IntelliJ IDEA.
 * User: Taras_Brzezinsky
 * Date: 5/24/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class Statistic<T> {
    T expected;
    T real;

    public Statistic(T expected, T real) {
        this.expected = expected;
        this.real = real;
    }
}
