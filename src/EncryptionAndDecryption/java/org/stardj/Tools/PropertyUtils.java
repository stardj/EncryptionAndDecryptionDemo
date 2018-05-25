package org.stardj.Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 配置文件工具类
 * Created by stardj on 17/2/10.
 */
public class PropertyUtils {

    private static final String[] files = {"my"};//相关配置文件前缀

    private static Properties properties = new Properties();

    static {
        load();
    }

    /**
     * 加载配置文件
     */
    private static void load() {
        InputStream in = null;
        for (String file : files) {
            URL url = PropertyUtils.class.getClassLoader().getResource(file + ".properties");//反射获取
            try {
                String path = URLDecoder.decode(url.getPath());
                int index = path.indexOf("!/");
                if (index != -1) {
                    path = path.substring(0, index);
                    JarFile earFile = new JarFile(path.substring(path.indexOf("/")));
                    JarEntry jarEntry = earFile.getJarEntry(file + "properties");
                    in = earFile.getInputStream(jarEntry);
                } else {
                    in = new FileInputStream(path);
                }
                properties.load(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                //打日志
            } finally {
                //释放所有资源
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    in = null;
                }
            }
        }
    }

    /**
     * 从消息定义文件中取出code对应的消息
     * @param key
     * @return
     */
    public static String getValue(String key){
        return StringUtils.trim(properties.getProperty(key));
    }


    public static void main(String[] args) {

        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, String> test = new HashMap<String, String>();

        test.put("1","test1");
        test.put("2","test2");
        test.put("3","test3");
        test.put("4", "test4");

        data.putAll(test);

        System.out.println(data.isEmpty());

    }
}
