package com.test.lsj.algorithm.interfaces;

import java.util.Stack;

import com.test.lsj.algorithm.bean.BinTreeNode;
import com.test.lsj.algorithm.bean.TreeNode;

/**
 * BinTree
 */
public interface BinTree<T extends TreeNode>{
    T createTree(T root,int[] arrays);
    T addNode(T node, int value);
    T delNode(T node, int value);
    T getNode(T node,int value);
    T getMaxNode(T node);
    T getMinNode(T node);
    Stack<T> getValueStack(T node,int value);
}