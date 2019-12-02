package com.test.lsj.algorithm.bean;

/**
 * BinTreeNode
 */
public class BinTreeNode {
    int value;
    int height;
    //BinTreeNode parent;
    BinTreeNode left;
    BinTreeNode right;

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the left
     */
    public BinTreeNode getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public BinTreeNode getRight() {
        return right;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(BinTreeNode left) {
        this.left = left;
    }

    /**
     * @param right the right to set
     */
    public void setRight(BinTreeNode right) {
        this.right = right;
    }

    // /**
    //  * @return the parent
    //  */
    // public BinTreeNode getParent() {
    //     return parent;
    // }

    // /**
    //  * @param parent the parent to set
    //  */
    // public void setParent(BinTreeNode parent) {
    //     this.parent = parent;
    // }
    


}