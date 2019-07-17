package com.chenxin;

import java.util.ArrayList;

/**
 * @author chenxin
 * @date 2019/06/24
 */
public class HeapOOM {
    /**
     * @author chenxin
     * @date 2019-06-24 22:28
     * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    static class OOMObject{

    }

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
