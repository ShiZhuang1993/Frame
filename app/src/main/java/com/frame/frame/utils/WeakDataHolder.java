package com.frame.frame.utils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 发现Inetent、Bundle等传递数据时有一个缓冲区，而这个缓冲区最大只有1MB，所以当数据量大时会出现
 * android.os.TransactionTooLargeException
 * 所以再动态添加tab标签给fragment传数据时  使用临时保存数据的方法来传递数据
 *
 * ===============使用方式======================
 * 在需要携带数据的时候，调用WeakDataHolder.getInstance().saveData("", object);
 * （两个参数，第一个是map的key，第二个是需要传递的数据，支持所有的数据类型）；
 *如：int aa=(int)WeakDataHolder.getInstance().getData("")
 *得到数据并转换为需要的类型即可。
 */

public class WeakDataHolder {
    private static WeakDataHolder instance;
    private Map<String, WeakReference<Object>> map = new HashMap<>();

    public static WeakDataHolder getInstance() {
        if (instance == null) {
            synchronized (WeakDataHolder.class) {
                if (instance == null) {
                    instance = new WeakDataHolder();
                }
            }
        }
        return instance;
    }

    /**
     * 数据存储
     *
     * @param id
     * @param object
     */
    public void saveData(String id, Object object) {
        map.put(id, new WeakReference<>(object));
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public Object getData(String id) {
        WeakReference<Object> weakReference = map.get(id);
        return weakReference.get();
    }
}