package com.test.lsj.algorithm.utils;

import com.test.lsj.algorithm.bean.BinTreeNode;

import com.test.lsj.algorithm.bean.TreeNode;
import org.apache.log4j.Logger;

/**
 * BinTreeUtils
 */
public class BinTreeUtils {
    private static final Logger logger = Logger.getLogger(BinTreeUtils.class);

    public static void preViewTree(TreeNode root){
        if(null == root) {
            return;
        }
        logger.debug(root.getValue());
        preViewTree(root.getLeft());
        preViewTree(root.getRight());
    }

    public static void midViewTree(TreeNode root){
        if(null == root) {
            return;
        }
        midViewTree(root.getLeft());
        logger.debug(root.getValue());
        midViewTree(root.getRight());
    }

    public static void suffViewTree(TreeNode root){
        if(null == root) {
            return;
        }
        suffViewTree(root.getLeft());
        suffViewTree(root.getRight());
        logger.debug(root.getValue());
    }
}