package qqclientpackage.utils;
/*
 * @author Curry
 * @version 1.0
 *
 * 工具类，用于读取键盘输入
 */


import java.util.Scanner;

public class Utils {
    private final static Scanner input = new Scanner(System.in);

    //返回输入字符串指定索引处的字符
    public static char readChar(int index) {
        String readStr = input.next();
        return readStr.charAt(index);
    }

    //返回输入字符串指定索引处开始指定长度的字符串
    public static String readString(int offset, int length) {
        String readStr = input.nextLine();
        char[] chars = new char[readStr.length()];
        readStr.getChars(0, readStr.length(),
                chars, 0);
        return new String(chars);
    }

    //返回从第一位开始指定长度的字符串
    public static String readString(int length) {
        String readStr = input.next();
        return readStr.substring(0, length);
    }

    //返回下一行输入的内容，以回车结束
    public static String readString(){
        return input.nextLine();
    }

    //返回下一个空格前输入的内容，取出行首空格
    public static String readLine(){
        return input.next();
    }
}
