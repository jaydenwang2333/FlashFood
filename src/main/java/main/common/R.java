package main.common;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * Return result object
 *
 *
 * @param <T>
 */
@Data
public class R<T> {

    /**
     * 1: sucess 0:fail
     */
    private Integer code;

    /**
     * Wrong msg
     */
    private String msg;

    /**
     *
     */
    private T data;

    /**
     * dynamic data
     */
    private Map map = new HashMap();


    /**
     * Return data and success code on success
     *
     * @param object successful information object
     * @param <T>
     * @return Returns a generic result R object of this class, containing the success code and data
     */
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    /**
     *
     *
     * @param msg Wrong msg
     * @param <T>
     * @return Returns a generic result R object of this class, containing failure codes and error messages
     */
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
