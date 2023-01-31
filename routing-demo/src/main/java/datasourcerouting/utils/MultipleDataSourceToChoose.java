package datasourcerouting.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-05-30 19:53
 *
 * <p>
 */
public class MultipleDataSourceToChoose extends AbstractRoutingDataSource {


    /**
     * <property name="targetDataSources">
     *             <map key-type="java.lang.String" value-type="javax.sql.DataSource">
     *                 <entry key="datasource0" value-ref="dataSource0" />
     *                 <entry key="datasource1" value-ref="dataSource1" />
     *             </map>
     *         </property>
     *          设置默认的目标数据源
     *         <property name="defaultTargetDataSource" ref="dataSource0" />
     */

    /**
     * @desction: 根据Key获取数据源的信息，上层抽象函数的钩子
     * @author: wangyu
     * @param:
     * @return:
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return HandlerDataSource.getDataSource();
    }
}
