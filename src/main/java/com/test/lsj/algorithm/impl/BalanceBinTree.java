package com.test.lsj.algorithm.impl;

import com.test.lsj.algorithm.utils.AlgorithmTool;

import java.util.Stack;

import com.test.lsj.algorithm.bean.BinTreeNode;
import com.test.lsj.algorithm.interfaces.BinTree;

/**
 * BalanceBinTree
 */
public class BalanceBinTree implements BinTree {
    BinTreeNode root = null;

    public static void main(String[] args) {
        int[] randomArrays = AlgorithmTool.createRandoms(10, 100);
        AlgorithmTool.listArrays();
    }

    @Override
    public BinTreeNode createTree(BinTreeNode root, int[] arrays) {
        if (null == arrays || arrays.length <= 0)
            return null;
        if (null == root)
            root = new BinTreeNode();

        return root;

    }

    @Override
    public void addNode(BinTreeNode node, int value) {
        // TODO Auto-generated method stub

    }

    @Override
    public BinTreeNode delNode(BinTreeNode node, int value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BinTreeNode getMaxNode(BinTreeNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BinTreeNode getMinNode(BinTreeNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BinTreeNode getNode(BinTreeNode node, int value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stack<BinTreeNode> getValueStack(BinTreeNode node, int value) {
        // TODO Auto-generated method stub
        return null;
    }

    
}