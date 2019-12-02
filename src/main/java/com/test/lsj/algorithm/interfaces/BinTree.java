package com.test.lsj.algorithm.interfaces;

import java.util.Stack;

import com.test.lsj.algorithm.bean.BinTreeNode;

/**
 * BinTree
 */
public interface BinTree {
    BinTreeNode createTree(BinTreeNode root,int[] arrays);
    BinTreeNode addNode(BinTreeNode node, int value);
    BinTreeNode delNode(BinTreeNode node, int value);
    BinTreeNode getNode(BinTreeNode node,int value);
    BinTreeNode getMaxNode(BinTreeNode node);
    BinTreeNode getMinNode(BinTreeNode node);
    Stack<BinTreeNode> getValueStack(BinTreeNode node,int value);
}