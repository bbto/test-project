package com.sres.util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author bbto
 */
public class Util {

    public static String quote(String value) {
        return "'" + value + "'";
    }

    public static String startsWith(String value) {
        return "'" + value + "%'";
    }

    public static String endsWith(String value) {
        return "'%" + value + "'";
    }

    public static String has(String value) {
        return "'%" + value + "%'";
    }

    public static String concat(ArrayList values, String separator) {
        String result = "";
        Iterator i = values.iterator();
        while (i.hasNext()) {
            result = result.concat(separator.concat((String) i.next()));
        }
        return result.substring(separator.length());
    }
}
