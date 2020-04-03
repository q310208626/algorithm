package com.test.lsj.algorithm.impl;

import com.test.lsj.algorithm.bean.AVLTreeNode;
import com.test.lsj.algorithm.utils.AlgorithmTool;

import java.util.Stack;

import com.test.lsj.algorithm.bean.BinTreeNode;
import com.test.lsj.algorithm.interfaces.BinTree;

/**
 * BalanceBinTree
 */
public class BalanceBinTree implements BinTree<AVLTreeNode> {
    AVLTreeNode avlTreeNode;

    public static void main(String[] args) {
        int[] randomArrays = AlgorithmTool.createRandoms(10, 100);
        AlgorithmTool.listArrays();
    }

    @Override
    public AVLTreeNode createTree(AVLTreeNode root, int[] arrays) {
        return null;
    }

    @Override
    public AVLTreeNode addNode(AVLTreeNode node, int value) {
        // 如果是根节点
        if(null == node){
            node = new AVLTreeNode(value);
        }else{
            // 数值大于节点，插入右子树
            if(value > node.getValue()){
                // 如果右子树为空，则右子树节点为插入目标，创建节点
                if(null == node.getRight()){
                    AVLTreeNode avlTreeNode = new AVLTreeNode(value);
                    avlTreeNode.setParent(node);
                    node.setRight(avlTreeNode);
                }else{
                    addNode(node.getRight(),value);
                }
            }else{
                if(null == node.getLeft()){
                    AVLTreeNode avlTreeNode = new AVLTreeNode(value);
                    avlTreeNode.setParent(node);
                    node.setLeft(avlTreeNode);
                }else{
                    addNode(node.getLeft(),value);
                }
            }
        }

        // 插入节点后进行树的平衡操作


        return node;
    }

    @Override
    public AVLTreeNode delNode(AVLTreeNode node, int value) {
        return null;
    }

    @Override
    public AVLTreeNode getNode(AVLTreeNode node, int value) {
        return null;
    }

    @Override
    public AVLTreeNode getMaxNode(AVLTreeNode node) {
        return null;
    }

    @Override
    public AVLTreeNode getMinNode(AVLTreeNode node) {
        return null;
    }

    @Override
    public Stack<AVLTreeNode> getValueStack(AVLTreeNode node, int value) {
        return null;
    }

    /**
    * @Function calDepth
    * @Description // 计算树的深度
    * @param  [avlTreeNode]
    * @return int
    * @throw
    * @Author linsj3
    * @date 2020/4/3 15:41
    **/

    public int calDepth(AVLTreeNode avlTreeNode){
        int depth = 0;
        if(null != avlTreeNode){

            if(null != avlTreeNode.getLeft()){
                depth = avlTreeNode.getLeft().getDepth();
            }

            if(null != avlTreeNode.getRight() && avlTreeNode.getRight().getDepth() > depth){
                depth = avlTreeNode.getRight().getDepth();
            }
        }
        depth++;
        return depth;
    }

    /**
    * @Function calBalance
    * @Description // 计算树的平衡数
    * @param  [avlTreeNode]
    * @return int
    * @throw
    * @Author linsj3
    * @date 2020/4/3 15:41
    **/

    public int calBalance(AVLTreeNode avlTreeNode){
        int balance = 0;
        if(null != avlTreeNode){
            int leftDepth =0;
            int rightDepth =0;

            if(null != avlTreeNode.getLeft()){
                leftDepth = avlTreeNode.getLeft().getDepth();
            }

            if(null != avlTreeNode.getRight()){
                rightDepth = avlTreeNode.getRight().getDepth();
            }

            balance = leftDepth - rightDepth;
        }
        return balance;
    }

    /**
    * @Function leftRotate
    * @Description // 左旋
    * @param  [avlTreeNode]
    * @return void
    * @throw
    * @Author linsj3
    * @date 2020/4/3 15:48
    **/

    public void leftRotate(AVLTreeNode avlTreeNode){
        AVLTreeNode parent = avlTreeNode.getParent();
        AVLTreeNode rightSon = avlTreeNode.getRight();
        AVLTreeNode leftOfRightSon = rightSon.getLeft();

        // 右子节点替换父节点
        rightSon.setParent(parent);
        if(null != parent){
            if(avlTreeNode == parent.getLeft()){
                parent.setLeft(rightSon);
            }else{
                parent.setRight(rightSon);
            }
        }

        // 父节点与右子节点关系变换
        rightSon.setLeft(avlTreeNode);
        avlTreeNode.setParent(rightSon);

        // 右子节点的左节点如果不为空，则设置为原父节点的右节点
        if(null != leftOfRightSon){
            leftOfRightSon.setParent(avlTreeNode);
            avlTreeNode.setRight(leftOfRightSon);
        }

        avlTreeNode.setDepth(calDepth(avlTreeNode));
        avlTreeNode.setBalance(calBalance(avlTreeNode));
        rightSon.setDepth(calDepth(rightSon));
        rightSon.setBalance(calBalance(rightSon));
    }

    public void rightRotate(AVLTreeNode avlTreeNode){
        AVLTreeNode parent = avlTreeNode.getParent();
        AVLTreeNode leftSon = avlTreeNode.getLeft();
        AVLTreeNode rightOfleftSon = leftSon.getRight();

        leftSon.setParent(parent);
        if(null != parent){
            if(avlTreeNode == parent.getLeft()){
                parent.setLeft(leftSon);
            }else{
                parent.setRight(leftSon);
            }
        }

        leftSon.setRight(avlTreeNode);
        avlTreeNode.setParent(leftSon);

        if(null != rightOfleftSon){
            rightOfleftSon.setParent(avlTreeNode);
            avlTreeNode.setLeft(rightOfleftSon);
        }

        avlTreeNode.setDepth(calDepth(avlTreeNode));
        avlTreeNode.setBalance(calBalance(avlTreeNode));
        leftSon.setDepth(calDepth(leftSon));
        leftSon.setBalance(calBalance(leftSon));
    }
}
