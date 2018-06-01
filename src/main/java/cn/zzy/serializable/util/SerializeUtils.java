package cn.zzy.serializable.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 缓存数据转换工具类
 *
 * @author dell
 */
public class SerializeUtils {

    public static Logger RedisLog = Logger.getLogger(SerializeUtils.class);

    /**
     * 将传入的数据序列化
     *
     * @param object
     * @return
     */
    public static byte[] serialize(final Object object) {
        if (object == null) {
            return null;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            RedisLog.error("序列化异常", e);
            return null;
        } finally {
            close(oos);
            close(baos);
        }
    }

    /**
     * 将传入的数据反序列化
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object unserialize(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            RedisLog.error("反序列化异常", e);
            return null;
        } finally {
            close(ois);
            close(bais);
        }

    }

    /**
     * 序列化 list 集合
     *
     * @param list
     * @return
     */
    public static byte[] serializeList(List<?> list) {
        if (list == null) {
            return null;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            for (Object obj : list) {
                oos.writeObject(obj);
            }
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(oos);
            close(baos);
        }
        return bytes;
    }

    /**
     * 反序列化 list 集合
     *
     * @param lb
     * @return
     */
    public static List<?> unserializeList(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        List<Object> list = new ArrayList<Object>();
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            while (bais.available() > 0) {
                Object obj = (Object) ois.readObject();
                if (obj == null) {
                    break;
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bais);
            close(ois);
        }
        return list;
    }

    /**
     * 关闭io流对象
     *
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
