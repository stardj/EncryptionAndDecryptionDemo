package org.stardj.Tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * 字符串工具类
 * Created by stardj on 17/2/10.
 */
public class StringUtils {

    /**
     * 空字符串转换及尾部去空格方法
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null?"":str.trim();
    }

    /**
     * 判空方法
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        str = trim(str);
        return "".equals(str);
    }

    /**
     * 判非空方法
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 对象去空方法
     * @param obj
     * @return
     */
    public static Object trimObjectFields(Object obj) {
        if(obj == null) {
            return "";
        } else {
            Class cls = obj.getClass();
            if(obj instanceof String) {
                return trim(obj.toString());
            } else {
                ArrayList allFields;
                for(allFields = new ArrayList(); cls != null && cls != Object.class; cls = cls.getSuperclass()) {
                    Field[] e = cls.getDeclaredFields();
                    Field[] field = e;
                    int value = e.length;

                    for(int i$ = 0; i$ < value; ++i$) {
                        Field field1 = field[i$];
                        allFields.add(field1);
                    }
                }

                try {
                    Iterator var9 = allFields.iterator();

                    while(var9.hasNext()) {
                        Field var10 = (Field)var9.next();
                        if(!Modifier.isFinal(var10.getModifiers())) {
                            var10.setAccessible(true);
                            Object var11 = var10.get(obj);
                            if(var11 instanceof String) {
                                var10.set(obj, trim((String)var11));
                            }
                        }
                    }
                } catch (Exception var8) {
                    var8.printStackTrace();
                }

                return obj;
            }
        }
    }

    /**
     * map去空方法
     * @param map
     */
    public static void trimMap(Map map) {
        if(map != null) {
            Iterator i$ = map.keySet().iterator();

            while(i$.hasNext()) {
                Object key = i$.next();
                Object value = map.get(key);
                if(value != null && value instanceof String) {
                    map.put(key, trim(value.toString()));
                }
            }

        }
    }
}
