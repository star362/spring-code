package datasourcerouting.utils;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-05-30 19:54
 *
 * <p>
 */
public class HandlerDataSource {

    private static ThreadLocal<DataSourceeEnum> handlerThredLocal = new ThreadLocal<>();

    /**
     * @desction: 提供给AOP去设置当前的线程的数据源的信息
     * @author: wangji
     * @date: 2017/8/21
     * @param: [datasource]
     * @return: void
     */
    public static void putDataSource(DataSourceeEnum datasource) {
        handlerThredLocal.set(datasource);
    }

    /**
     * @desction: 提供给AbstractRoutingDataSource的实现类，通过key选择数据源
     * @author: wangji
     * @date: 2017/8/21
     * @param: []
     * @return: java.lang.String
     */
    public static DataSourceeEnum getDataSource() {
        return handlerThredLocal.get();
    }

    /**
     * @desction: 使用默认的数据源
     */
    public static void clear() {
        handlerThredLocal.remove();
    }
}
