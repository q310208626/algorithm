package com.test.lsj.algorithm.interfaces;
import com.test.lsj.algorithm.bean.BinTreeNode;

/**
 * BinTree
 */
public interface BinTree {
    BinTreeNode createTree(BinTreeNode root,int[] arrays);
    void addNode(BinTreeNode node, int value);
    void delNode(BinTreeNode node, int value);
    BinTreeNode getNode(BinTreeNode node,int value);
    BinTreeNode getMaxNode(BinTreeNode node);
    BinTreeNode getMinNode(BinTreeNode node);
}