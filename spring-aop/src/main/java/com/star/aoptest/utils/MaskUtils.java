package com.star.aoptest.utils;

import com.star.aoptest.aop.Annotations.Mask;
import com.star.aoptest.entity.School;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-08-19 20:20
 *
 * <p>
 */
public class MaskUtils {

    private static final Logger log = LoggerFactory.getLogger(MaskUtils.class);

    public static <T> T maskinfo(T t) throws IllegalAccessException {
        final Class<?> aClass = t.getClass();
        final Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Mask.class) &&
                    field.getType() == String.class) {
                field.setAccessible(true);

                final Mask annotation = field.getAnnotation(Mask.class);
                final String s = annotation.maskStr();//用什么打码
                final int i = annotation.prefixNoMaskLen();//前置不需要打码的长度
                final int i1 = annotation.suffixNoMaskLen();//后置不需要打码的长度
                final String o = (String) field.get(t);
                final int length = o.length();
                if (i < length) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < length; j++) {
                        if (j < i) {
                            sb.append(o.charAt(j));
                            continue;
                        }
                        if (j > (length - i1 - 1)) {
                            sb.append(o.charAt(j));
                            continue;
                        }
                        sb.append(s);
                    }
                    field.set(t,sb.toString());
                }
            }
        }
        return t;
    }


    public static void main(String[] args) throws IllegalAccessException {
        log.info("====================================");
        final School school1 = new School("微智信业2", "哈尔滨2", "wangyu@mvtech.com.cn222", 13796189804L);
        final School school = new School("微智信业", "哈尔滨", "wangyu@mvtech.com.cn", 13796189803L,school1);

        final School maskinfo = maskinfo(school);

        log.info("===========[{}]", maskinfo);
    }


}
