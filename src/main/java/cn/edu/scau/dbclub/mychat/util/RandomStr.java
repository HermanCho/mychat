package cn.edu.scau.dbclub.mychat.util;

import java.util.Random;

/**
 * @author 杜科
 * @description
 * @contact AllenDuke@163.com
 * @date 2020/4/30
 */
public class RandomStr {

    /**
     * @description: 返回指定长度的随机字符串
     * @param length 长度
     * @return: java.lang.String
     * @author: 杜科
     * @date: 2020/4/30
     */
    public static String getRandomStr(int length) {
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            builder.append(str.charAt(number));
        }
        return builder.toString();
    }
}
