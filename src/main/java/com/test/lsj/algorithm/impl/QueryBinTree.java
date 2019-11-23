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
        int[] arrays = new int[]{66,89,87,86,88};
        AlgorithmTool.listArrays();
        BinTreeNode root = new BinTreeNode();
        queryBinTree.createTree(root,arrays);
        // BinTreeUtils.PreViewTree(root);
        //BinTreeNode targetNode =  queryBinTree.getPredecessor(root);
        // BinTreeNode targetNode =  queryBinTree.getNode(root,45);
        BinTreeNode targetNode =  queryBinTree.getPredecessor(root,88);
        System.out.println(targetNode);
        
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
    public void delNode(BinTreeNode node, int value) {
        if(null == node) return;
        if(node.getValue() == value){

        }
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

    // 获取节点前驱
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


    
}