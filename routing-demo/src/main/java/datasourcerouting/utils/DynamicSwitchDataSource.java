package datasourcerouting.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-05-30 19:55
 *
 * <p>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {

    DataSourceeEnum dataSource() default DataSourceeEnum.WRITD;
}
