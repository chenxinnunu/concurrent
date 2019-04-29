package com.kykj.demo.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * base64解密
 *
 * @author chenxinnunu@gmail.com
 * @date 2019/4/2 13:43
 */
public class Demo {
        public static void main(String[] args) {
            String s = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48cmVxdWVzdCB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4bWxuczp4c2Q9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hIiB4bWxucz0iaHR0cDovL3BpYW8ucXVuYXIuY29tLzIwMTMvUU1lbnBpYW9SZXF1ZXN0U2NoZW1hIj48aGVhZGVyPjxhcHBsaWNhdGlvbj5QaWFvLk1lbnBpYW8uQWdlbnQ8L2FwcGxpY2F0aW9uPjxwcm9jZXNzb3I U3VwcGxpZXJEYXRhRXhjaGFuZ2VQcm9jZXNzb3I8L3Byb2Nlc3Nvcj48dmVyc2lvbj52MS4wLjA8L3ZlcnNpb24 PGJvZHlUeXBlPk5vdGljZU9yZGVyQ29uc3VtZWRSZXF1ZXN0Qm9keTwvYm9keVR5cGU PGNyZWF0ZVVzZXI U3VwcGxpZXJTeXN0ZW1OYW1lPC9jcmVhdGVVc2VyPjxjcmVhdGVUaW1lPjIwMTktMDQtMDIgMTA6MDQ6MTQ8L2NyZWF0ZVRpbWU PHN1cHBsaWVySWRlbnRpdHk MTAwMzY8L3N1cHBsaWVySWRlbnRpdHk PC9oZWFkZXI PGJvZHkgeHNpOnR5cGU9Ik5vdGljZU9yZGVyQ29uc3VtZWRSZXF1ZXN0Qm9keSI PG9yZGVySW5mbz48c3RhdHVzPjE8L3N0YXR1cz48cGFydG5lcm9yZGVySWQ NDU4MDYxPC9wYXJ0bmVyb3JkZXJJZD48b3JkZXJRdWFudGl0eT4xPC9vcmRlclF1YW50aXR5Pjx1c2VRdWFudGl0eT4xPC91c2VRdWFudGl0eT48Y29uc3VtZUluZm8gLz48b3JkZXJJZD4yMDE5MDQwMjEwMDQyNjQ4NTkyMzgyNDE8L29yZGVySWQ PC9vcmRlckluZm8 PC9ib2R5PjwvcmVxdWVzdD4=";
            System.out.println("原字符串：" + s);
            //String encryptString = encryptBASE64(s);
            //System.out.println("加密后：" + encryptString);
            System.out.println("解密后：" + decryptBASE64(s));
        }

        /**
         * BASE64解密
         *
         * @param key
         * @return
         * @throws Exception
         */
        public static String decryptBASE64(String key) {
            byte[] bt;
            try {
                bt = (new BASE64Decoder()).decodeBuffer(key);
                //如果出现乱码可以改成： String(bt, "utf-8")或 gbk
                return new String(bt);
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        /**
         * BASE64加密
         * @param key
         * @return
         * @throws Exception
         */
        public static String encryptBASE64(String key) {
            byte[] bt = key.getBytes();
            return (new BASE64Encoder()).encodeBuffer(bt);
        }
}
