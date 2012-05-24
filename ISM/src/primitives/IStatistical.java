package primitives;

/**
 * Created by IntelliJ IDEA.
 * User: Taras_Brzezinsky
 * Date: 5/24/12
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IStatistical<T, V> {
    public Statistic<T> getMean();
    public Statistic<V> getDispersion();
}
