package com.test.lsj.algorithm.utils;

import com.test.lsj.algorithm.bean.BinTreeNode;

import org.apache.log4j.Logger;

/**
 * BinTreeUtils
 */
public class BinTreeUtils {
    private static final Logger logger = Logger.getLogger(BinTreeUtils.class);

    public static void PreViewTree(BinTreeNode root){
        if(null == root) return;
        logger.debug(root.getValue());
        PreViewTree(root.getLeft());
        PreViewTree(root.getRight());
    }
}