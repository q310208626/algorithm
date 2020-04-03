package com.test.lsj.algorithm.bean;

/**
 * @descriptions: 平衡树节点
 * @version: v1.0.0
 * @author: linsj3
 * @create: 2020-04-03 15:19
 **/
public class AVLTreeNode extends TreeNode<AVLTreeNode>{
    int depth;
    int balance;
    AVLTreeNode parent;

    public AVLTreeNode(int x) {
        super(x);
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public AVLTreeNode getParent() {
        return parent;
    }

    public void setParent(AVLTreeNode parent) {
        this.parent = parent;
    }
}
