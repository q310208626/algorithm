package com.test.lsj.algorithm.impl;

import com.test.lsj.algorithm.bean.AVLTreeNode;
import com.test.lsj.algorithm.utils.AlgorithmTool;

import java.util.Stack;

import com.test.lsj.algorithm.bean.BinTreeNode;
import com.test.lsj.algorithm.interfaces.BinTree;
import com.test.lsj.algorithm.utils.BinTreeUtils;

/**
 * BalanceBinTree
 */
public class BalanceBinTree implements BinTree<AVLTreeNode> {
    AVLTreeNode avlTreeNode;

    public static void main(String[] args) {
//        int[] randomArrays = AlgorithmTool.createRandoms(10, 100);
        int[] randomArrays = new int[]{72,89,88,34,71};
        AlgorithmTool.listArrays();
        BalanceBinTree balanceBinTree = new BalanceBinTree();
        AVLTreeNode node = balanceBinTree.createTree(null,randomArrays);
        node = balanceBinTree.delNode(node,88);
        BinTreeUtils.midViewTree(node);
    }

    @Override
    public AVLTreeNode createTree(AVLTreeNode root, int[] arrays) {
        if(null == arrays || 0 == arrays.length){
            return null;
        }

        for (int value : arrays) {
            root = addNode(root,value);
        }

        return root;

    }

    @Override
    public AVLTreeNode addNode(AVLTreeNode node, int value) {
        // 如果节点为空，生成新节点
        if(null == node){
            node = new AVLTreeNode(value);
        }else{
            // 数值大于节点，插入右子树
            if(value > node.getValue()){
                // 如果右子树为空，则右子树节点为插入目标，创建节点
                AVLTreeNode sonNode = addNode(node.getRight(),value);
                sonNode.setParent(node);
                node.setRight(sonNode);
            }else{
                AVLTreeNode sonNode = addNode(node.getLeft(),value);
                sonNode.setParent(node);
                node.setLeft(sonNode);
            }
        }

        // 插入节点后计算平衡
        node.setDepth(calDepth(node));
        node.setBalance(calBalance(node));

        // 平衡树
        node = balanceTree(node);
        return node;
    }

    @Override
    public AVLTreeNode delNode(AVLTreeNode node, int value) {
        // 查询要删除的节点
        AVLTreeNode target = getNode(node,value);

        if(null != target){
            AVLTreeNode parent = target.getParent();
            AVLTreeNode leftSon = target.getLeft();
            AVLTreeNode rightSon = target.getRight();

            // 获取树的平衡
            int balance = target.getBalance();
            // 替代删除节点的节点
            AVLTreeNode swapNode = null;
            if(balance >= 0){
                // 如果左子树比较高，取左子树最大节点代替删除的节点
                swapNode = getMaxNode(leftSon);
            }else{
                // 如果右子树比较高，取左子树最小节点代替删除的节点
                swapNode = getMinNode(rightSon);
            }

            // 如果删除的节点不是叶子节点
            if(null != swapNode) {

                // 删除替代节点与其父节点的关系
                AVLTreeNode swapParentNode = swapNode.getParent();
                swapNode.setParent(null);
                if(swapParentNode.getLeft() == swapNode){
                    swapParentNode.setLeft(null);
                }else{
                    swapParentNode.setRight(null);
                }

                // 重新获取删除节点的左右子树
                leftSon = target.getLeft();
                rightSon = target.getRight();

                // 设置替代节点与原左右子树的关系
                swapNode.setLeft(leftSon);
                swapNode.setRight(rightSon);

                if(null != leftSon){
                    leftSon.setParent(swapNode);
                }

                if(null != rightSon){
                    rightSon.setParent(swapNode);
                }

                // 删除的节点是根节点
                if (null == parent) {
                    node = swapNode;
                } else {
                    // 删除的节点不是根节点，则配置替代节点与原父节点的关系
                    if(parent.getLeft() == target){
                        parent.setLeft(swapNode);
                        swapNode.setParent(parent);
                    }else{
                        parent.setRight(swapNode);
                        swapNode.setParent(parent);
                    }
                    target.setParent(null);
                }

                // 重新计算左右子树的深度
                if(null != leftSon){
                    leftSon.setDepth(calDepth(leftSon));
                }

                if(null != rightSon){
                    rightSon.setDepth(calDepth(rightSon));
                }

            }else{
                // 删除的节点是叶子节点

                // 删除的节点是根节点
                if (null == parent) {
                    node = swapNode;
                } else {
                    if (parent.getLeft() == target) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                    target.setParent(null);
                }
            }
            // 重新平衡
            AVLTreeNode newParent = balanceTree(parent);
            // 如果以根节点旋转,返回新的根节点
            if(null != parent &&null == parent.getParent()){
                node = newParent;
            }
        }

        // 返回根节点
        return node;
    }

    @Override
    public AVLTreeNode getNode(AVLTreeNode node, int value) {
        if(null != node){

            if(value == node.getValue()){
                return node;
            }else if(value < node.getValue()){
                node = getNode(node.getLeft(),value);
            }else {
                node = getNode(node.getRight(),value);
            }

        }
        return node;
    }

    @Override
    public AVLTreeNode getMaxNode(AVLTreeNode node) {
        if (null == node) {
            return null;
        }
        while (null != node.getRight()) {
            node = node.getRight();
        }
        return node;
    }

    @Override
    public AVLTreeNode getMinNode(AVLTreeNode node) {
        if (null == node) {
            return null;
        }
        while (null != node.getLeft()) {
            node = node.getLeft();
        }
        return node;
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

    public AVLTreeNode leftRotate(AVLTreeNode avlTreeNode){
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
        }else{
            avlTreeNode.setRight(null);
        }

        avlTreeNode.setDepth(calDepth(avlTreeNode));
        avlTreeNode.setBalance(calBalance(avlTreeNode));
        rightSon.setDepth(calDepth(rightSon));
        rightSon.setBalance(calBalance(rightSon));

        return rightSon;
    }

    public AVLTreeNode rightRotate(AVLTreeNode avlTreeNode){
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
        }else{
            avlTreeNode.setLeft(null);
        }

        avlTreeNode.setDepth(calDepth(avlTreeNode));
        avlTreeNode.setBalance(calBalance(avlTreeNode));
        leftSon.setDepth(calDepth(leftSon));
        leftSon.setBalance(calBalance(leftSon));

        return leftSon;
    }

    public AVLTreeNode balanceTree(AVLTreeNode node){
        // 进行平衡操作
        int balance = node.getBalance();
        if(balance > 1){
            // 左边深度大于右边深度
            if(null != node.getLeft() && null != node.getLeft().getRight()){
                // 先对左子节点进行左旋
                leftRotate(node.getLeft());
            }
            // 进行右旋
            node = rightRotate(node);
        }else if(balance < -1){
            // 右边深度大于左边深度
            if(null != node.getRight() && null != node.getRight().getLeft()){
                // 先对右子节点进行右旋
                rightRotate(node.getRight());
            }
            // 进行左旋
            node = leftRotate(node);
        }

        return node;
    }
}
