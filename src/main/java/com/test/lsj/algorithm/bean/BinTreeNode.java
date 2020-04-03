package com.test.lsj.algorithm.bean;

/**
 * BinTreeNode
 */
public class BinTreeNode extends TreeNode<BinTreeNode>{
    int height;

    public BinTreeNode(){

    }
    public BinTreeNode(int x) {
        super(x);
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