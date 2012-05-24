package primitives;

/**
 * Created by IntelliJ IDEA.
 * User: Taras_Brzezinsky
 * Date: 5/24/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class Statistic<T> {
    public T expected;
    public T real;

    public Statistic(T expected, T real) {
        this.expected = expected;
        this.real = real;
    }
}
