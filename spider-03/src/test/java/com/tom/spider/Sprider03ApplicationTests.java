package com.tom.spider;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Sprider03ApplicationTests {

    @Test
    void contextLoads() {
    }

    static int i = 0;
    @Test
    public void testThread() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int j=0;j<100;j++){
                    i++;
                    System.out.println(i+"thread1");
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int j=0;j<100;j++){
                    i++;
                    System.out.println(i+"thread2");
                }

            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("result"+i);

    }
}
