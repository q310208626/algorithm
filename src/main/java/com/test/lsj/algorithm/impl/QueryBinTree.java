package com.test.lsj.algorithm.impl;

import com.test.lsj.algorithm.bean.BinTreeNode;
import com.test.lsj.algorithm.interfaces.BinTree;
import com.test.lsj.algorithm.utils.AlgorithmTool;
import com.test.lsj.algorithm.utils.BinTreeUtils;

/**
 * QueryBinTree
 */
public class QueryBinTree implements BinTree {

    public static void main(String[] args) {
        QueryBinTree queryBinTree = new QueryBinTree();
        // int[] arrays = AlgorithmTool.createRandoms(10, 100);
        int[] arrays = new int[]{88,77,44,33,55,99};
        AlgorithmTool.listArrays();
        BinTreeNode root = new BinTreeNode();
        queryBinTree.createTree(root,arrays);
        // BinTreeUtils.PreViewTree(root);
        //BinTreeNode targetNode =  queryBinTree.getPredecessor(root);
        // BinTreeNode targetNode =  queryBinTree.getNode(root,45);
        // BinTreeNode targetNode =  queryBinTree.getSuccessor(root,55);
        BinTreeNode targetNode = queryBinTree.delNode(root, 88);
        BinTreeUtils.preViewTree(targetNode);
        //System.out.println(targetNode);
    }

    @Override
    public BinTreeNode createTree(BinTreeNode root,int[] arrays) {

        if(null == arrays || 0 == arrays.length){
            return null;             
        }

        for (int i = 0; i < arrays.length; i++) {
            addNode(root,arrays[i]);
        }
        
        return root;
    }

    @Override
    public void addNode(BinTreeNode node,int value) {
        // 传入空树
        if(0 == node.getValue()){
            node.setValue(value);
        }else{
            BinTreeNode tmpNode = node;
        
            int tmpValue = tmpNode.getValue();
            if(value < tmpValue){
                tmpNode = tmpNode.getLeft();
            }else{
                tmpNode = tmpNode.getRight();
            }
    
            if(null == tmpNode){
                tmpNode = new BinTreeNode();
                tmpNode.setValue(value);
                tmpNode.setParent(node);
                if(value < tmpValue){
                    node.setLeft(tmpNode);
                }else{
                    node.setRight(tmpNode);
                }
            }else{
                addNode(tmpNode,value);
            }
        }
    }

    @Override
    public BinTreeNode delNode(BinTreeNode node, int value) {
        if(null == node) return null;
<<<<<<< Updated upstream
        if(node.getValue() == value){

=======
        BinTreeNode valueNode =  getNode(node, value);
        if(null == valueNode){
            return node;
        }else{
            if(null != valueNode.getLeft() && null !=valueNode.getRight()){
                // 如果同时存在左右节点
                BinTreeNode parentNode =  valueNode.getParent();
                if(null == parentNode){
                    // 如果父节点是空，可以选择左子树或者右子树作为根，这里选右子树作为根
                    BinTreeNode rightNode = valueNode.getRight();
                    BinTreeNode leftNode = valueNode.getLeft();
                    // 获取右子树最小节点,接上左子树
                    BinTreeNode rightMinNode = getMinNode(rightNode);
                    rightMinNode.setLeft(leftNode);
                    node = rightNode; 
                }else if(valueNode == parentNode.getLeft()){
                    // 如果是作为父节点的左子树，则需要用右节点替代父节点
                    BinTreeNode rightNode = valueNode.getRight();
                    BinTreeNode leftNode = valueNode.getLeft();
                    // 获取右子树最小节点,接上左子树
                    BinTreeNode rightMinNode = getMinNode(rightNode);
                    rightMinNode.setLeft(leftNode);
                    parentNode.setLeft(rightNode);
                }else{
                    // 如果是作为父节点的右子树，则需要用左节点替代父节点
                    BinTreeNode rightNode = valueNode.getRight();
                    BinTreeNode leftNode = valueNode.getLeft();
                    // 获取左子树最大节点,接上右子树
                    BinTreeNode leftMaxNode = getMaxNode(leftNode);
                    leftMaxNode.setRight(rightNode);
                    parentNode.setRight(leftNode);
                }
            }else if(null != valueNode.getLeft()){
                // 如果只存在左节点，用左节点代替被删除的父节点
                BinTreeNode parentNode =  valueNode.getParent();
                if(null == parentNode){
                    node = valueNode.getLeft();
                }else{
                    if(valueNode == parentNode.getLeft()){
                        node.setLeft(valueNode.getLeft());
                    }else{
                        node.setRight(valueNode.getLeft());
                    }
                }
            }else if(null != valueNode.getRight()){
                // 如果只存在右节点，用右节点代替被删除的父节点
                BinTreeNode parentNode =  valueNode.getParent();
                if(null == parentNode){
                    node = valueNode.getRight();
                }else{
                    if(valueNode == parentNode.getLeft()){
                        node.setLeft(valueNode.getRight());
                    }else{
                        node.setRight(valueNode.getRight());
                    }
                }
            }else{
                // 作为叶子节点被删除
                BinTreeNode parentNode =  valueNode.getParent();
                if(null == parentNode){
                    return null;
                }else{
                    if(valueNode == parentNode.getLeft()){
                        parentNode.setLeft(null);
                    }else{
                        parentNode.setRight(null);
                    }
                }
            }
>>>>>>> Stashed changes
        }
        return node;
    }

    @Override
    public BinTreeNode getNode(BinTreeNode node,int value) {
        if(null == node) return null;
        if(value < node.getValue()){
            return getNode(node.getLeft(),value);
        }else if(value > node.getValue()){
            return getNode(node.getRight(),value);
        }else{
           return node; 
        }
        
    }

    @Override
    public BinTreeNode getMaxNode(BinTreeNode node) {
        if(null == node) return null;
        while(null != node.getRight()){
            node = node.getRight();
        }
        return node;
    }

    @Override
    public BinTreeNode getMinNode(BinTreeNode node) {
        if(null == node) return null;
        while(null != node.getLeft()){
            node = node.getLeft();
        }
        return node;
    }

    // 获取节点前驱(获取比当前节点小的节点集中最大的接节点)
    public BinTreeNode getPredecessor(BinTreeNode root,int value){
        BinTreeNode node = getNode(root,value);
        BinTreeNode tmp;
        if(null == node) return null;
        // 如果有左子树，则左子树是前驱
        if(null != node.getLeft()){
            tmp = node.getLeft();
        }else{
            // 当node是父节点的右节点时，则返回父节点
            BinTreeNode parentNode = node.getParent();
            // 当node是父节点的左节点时，需要向上遍历父节点，直到第一次出现节点是父节点的右节点情况
            while(null != parentNode && node == parentNode.getLeft()){
                node = parentNode;
                parentNode = parentNode.getParent();
            }
            tmp = parentNode;
        }

        return tmp;
    }

    // 获取后继节点(获取比当前节点大的节点集中最小的节点)
    public BinTreeNode getSuccessor(BinTreeNode root,int value){
        BinTreeNode node = getNode(root,value);
        BinTreeNode tmp = null;
        if(null == node) return null;
        // 如果有右节点，则后继节点是右节点中最左的节点
        if(null != node.getRight()){
            tmp = node.getRight();
            while(null != tmp && null != tmp.getLeft()){
                tmp = tmp.getLeft();
            }
        }else{
            BinTreeNode parent = node.getParent();
            // 如果是父节点的左节点，则取父节点
            if(parent != null && parent.getLeft() == node){
                tmp = parent;
            }
            
            // 如果是父节点的右节点，且父节点是其父节点的左节点，则取父节点
            if(parent != null && parent.getRight() == node){
                if(null != parent.getParent() && parent.getParent().getLeft() == parent){
                    tmp = parent.getParent();
                }
            }
        }
        return tmp;
    }


    
}