package com.test.lsj.algorithm.bean;

public class TreeNode<T extends TreeNode<T>> {
    int value;
    T left;
    T right;

    TreeNode(){}
    TreeNode(int x) { value = x; }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getRight() {
        return right;
    }

    public void setRight(T right) {
        this.right = right;
    }
}
