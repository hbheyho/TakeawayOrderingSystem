package com.project.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: HB
 * @Description: LogbackTest
 * @CreateDate: 9:31 2020/10/19
 */

// 当一个类用@RunWith注释或继承一个用@RunWith注释的类时，
// JUnit将调用它所引用的类来运行该类中的测试而不是开发者去在junit内部去构建它
@RunWith(SpringRunner.class)
// @SpringBootTest注解是SpringBoot自1.4.0版本开始引入的一个用于测试的注解
// classes 指定启动类, 可以读取配置文件
@SpringBootTest(classes = SellApplication.class)
// @slf4j - lombok注解
public class LogbackTest {
    private final Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void logTest() {
        String name = "HB";
        logger.info("{} use slf4j.", name);
        logger.info("info...");
        logger.debug("debug...");
        logger.error("error...");
    }

}
