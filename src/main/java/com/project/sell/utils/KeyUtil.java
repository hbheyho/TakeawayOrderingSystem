package com.project.sell.utils;

import java.util.Random;

/**
 * @Author: HB
 * @Description: 生成唯一主键工具类
 * @CreateDate: 20:38 2020/10/27
 */

public class KeyUtil {

    /**
     * @Author: HB
     * @Description: 生成唯一主键 时间 + 随机数
     * @Date: 20:39 2020/10/27
     * @Params: null
     * @Returns:
    */
    public static synchronized String generateUniqueKey() {

        Random random = new Random();
        // 生成的随机数 100000 ~ 999999
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);

    }

}
