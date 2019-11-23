package com.test.lsj.algorithm.utils;
import java.util.Arrays;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * AlgorithmTool
 */
public class AlgorithmTool {
    private static final Logger logger = Logger.getLogger(AlgorithmTool.class);
    private static int[] randomArrays;
    /*
    *   产生随机测试数据
    */
    public static int[] createRandoms(int arraySize,int bound) {
        if(arraySize <= 0) return null;

        randomArrays = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < randomArrays.length; i++) {
            randomArrays[i] = random.nextInt(bound+1);
        }

        return randomArrays;
    }

    public static void listArrays() {
        if(null != randomArrays){
            StringBuffer printBuffer = new StringBuffer(32);
            Arrays.stream(randomArrays).forEach(x->{
                printBuffer.append(x).append(" ");
            });
            logger.debug(printBuffer.toString());
        }
    }
}