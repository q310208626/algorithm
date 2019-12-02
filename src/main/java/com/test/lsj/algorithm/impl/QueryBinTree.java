package com.test.lsj.algorithm.impl;

import java.util.Stack;

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
        int[] arrays = new int[] { 88, 77, 44, 33, 55, 99 };
        AlgorithmTool.listArrays();
        BinTreeNode root = new BinTreeNode();
        root = queryBinTree.createTree(null, arrays);
        BinTreeUtils.preViewTree(root);
        BinTreeNode targetNode = queryBinTree.getPredecessor(root,88);
        // BinTreeNode targetNode = queryBinTree.getNode(root,45);
        // BinTreeNode targetNode = queryBinTree.getSuccessor(root,33);
        //BinTreeNode targetNode = queryBinTree.delNode(root, 88);
        // BinTreeUtils.preViewTree(targetNode);
        // System.out.println(targetNode);
    }

    @Override
    public BinTreeNode createTree(BinTreeNode root, int[] arrays) {

        if (0 == arrays.length) {
            return null;
        }

        for (int i = 0; i < arrays.length; i++) {
            if(null == root){
                root = addNode(root, arrays[i]);
            }else{
                addNode(root, arrays[i]);
            }
            
        }

        return root;
    }

    @Override
    public BinTreeNode addNode(BinTreeNode node, int value) {
        // 传入空树
        if (null == node) {
            node = new BinTreeNode();
            node.setValue(value);
        } else {
            BinTreeNode tmpNode = null;

            int tmpValue = node.getValue();
            if (value < tmpValue) {
                tmpNode = node.getLeft();
                BinTreeNode leftTree = addNode(tmpNode, value);
                node.setLeft(leftTree);
            } else {
                tmpNode = node.getRight();
                BinTreeNode rightTree = addNode(tmpNode, value);
                node.setRight(rightTree);
            }
        }

        return node;
    }

    @Override
    public BinTreeNode delNode(BinTreeNode node, int value) {
        if (null == node)
            return null;
            Stack<BinTreeNode> valueStack = getValueStack(node,value);
            BinTreeNode valueNode = null;
            if(!valueStack.isEmpty()){
                valueNode = valueStack.pop();
            }

            // 如果删除的值不存在，直接返回
            if (null != valueNode && valueNode.getValue() != value) {
                return node;
            } else {
                BinTreeNode parentNode = null;
                if(!valueStack.empty()){
                    parentNode = valueStack.pop();
                }

                if (null != valueNode.getLeft() && null != valueNode.getRight()) {
                    // 如果同时存在左右节点
                    if (null == parentNode) {
                        // 如果父节点是空，可以选择左子树或者右子树作为根，这里选右子树作为根
                        BinTreeNode rightNode = valueNode.getRight();
                        BinTreeNode leftNode = valueNode.getLeft();
                        // 获取右子树最小节点,接上左子树
                        BinTreeNode rightMinNode = getMinNode(rightNode);
                        rightMinNode.setLeft(leftNode);
                        node = rightNode;
                    } else if (valueNode == parentNode.getLeft()) {
                        // 如果是作为父节点的左子树，则需要用右节点替代父节点
                        BinTreeNode rightNode = valueNode.getRight();
                        BinTreeNode leftNode = valueNode.getLeft();
                        // 获取右子树最小节点,接上左子树
                        BinTreeNode rightMinNode = getMinNode(rightNode);
                        rightMinNode.setLeft(leftNode);
                        parentNode.setLeft(rightNode);
                    } else {
                        // 如果是作为父节点的右子树，则需要用左节点替代父节点
                        BinTreeNode rightNode = valueNode.getRight();
                        BinTreeNode leftNode = valueNode.getLeft();
                        // 获取左子树最大节点,接上右子树
                        BinTreeNode leftMaxNode = getMaxNode(leftNode);
                        leftMaxNode.setRight(rightNode);
                        parentNode.setRight(leftNode);
                    }
                } else if (null != valueNode.getLeft()) {
                    // 如果只存在左节点，用左节点代替被删除的父节点
                    if (null == parentNode) {
                        node = valueNode.getLeft();
                    } else {
                        if (valueNode == parentNode.getLeft()) {
                            node.setLeft(valueNode.getLeft());
                        } else {
                            node.setRight(valueNode.getLeft());
                        }
                    }
                } else if (null != valueNode.getRight()) {
                    // 如果只存在右节点，用右节点代替被删除的父节点
                    if (null == parentNode) {
                        node = valueNode.getRight();
                    } else {
                        if (valueNode == parentNode.getLeft()) {
                            node.setLeft(valueNode.getRight());
                        } else {
                            node.setRight(valueNode.getRight());
                        }
                    }
                } else {
                    // 作为叶子节点被删除
                    if (null == parentNode) {
                        return null;
                    } else {
                        if (valueNode == parentNode.getLeft()) {
                            parentNode.setLeft(null);
                        } else {
                            parentNode.setRight(null);
                        }
                    }
                }
            }
        
        return node;
    }

    @Override
    public BinTreeNode getNode(BinTreeNode node, int value) {
        if (null == node)
            return null;
        if (value < node.getValue()) {
            return getNode(node.getLeft(), value);
        } else if (value > node.getValue()) {
            return getNode(node.getRight(), value);
        } else {
            return node;
        }

    }

    @Override
    public BinTreeNode getMaxNode(BinTreeNode node) {
        if (null == node)
            return null;
        while (null != node.getRight()) {
            node = node.getRight();
        }
        return node;
    }

    @Override
    public BinTreeNode getMinNode(BinTreeNode node) {
        if (null == node)
            return null;
        while (null != node.getLeft()) {
            node = node.getLeft();
        }
        return node;
    }

    // 获取节点前驱(获取比当前节点小的节点集中最大的接节点)
    public BinTreeNode getPredecessor(BinTreeNode root, int value) {
        Stack<BinTreeNode> valueStack = getValueStack(root, value);
        BinTreeNode node = null;
        if(!valueStack.empty()){
            node = valueStack.pop();
        }
        BinTreeNode tmp = null;
        // 树中不存在该值的接节点
        if (null == node || node.getValue() != value){
            return null;
        }

        // 如果有左子树，则左子树中最大值节点是前驱
        if (null != node.getLeft()) {
            tmp = getMaxNode(node.getLeft());
        } else {
            // 如果没有左子树，则需要看父母节点
            // 当node是父节点的右节点时，则返回父节点
            if(!valueStack.empty() && node == valueStack.peek().getRight()){
                tmp = valueStack.pop();
            }else if(!valueStack.empty() && node == valueStack.peek().getLeft()){
                // 当node是父节点的左节点时，需要向上遍历父节点，直到第一次出现节点是父节点的左节点情况
                // 如果遍历一直没有左节点，则说明父母节点值都比该节点大，反回null
                // 弹出父节点
                node = valueStack.pop();
                while(!valueStack.empty() && null == tmp) {
                    if(node == valueStack.peek().getLeft()){
                        node = valueStack.pop();
                    }else if(node == valueStack.peek().getRight()){
                        tmp = node; 
                    }
                    
                }
            }
        }
        return tmp;
    }

    // 获取后继节点(获取比当前节点大的节点集中最小的节点)
    public BinTreeNode getSuccessor(BinTreeNode root, int value) {
        Stack<BinTreeNode> valueStack = getValueStack(root, value);
        BinTreeNode node = null;
        if(!valueStack.empty()){
            node = valueStack.pop();
        }
        
        BinTreeNode tmp = null;
        // 该值的节点不存在
        if (null == node || node.getValue() != value){
            return null;
        }
            
        // 如果有右节点，则后继节点是右节点中的最小值
        if (null != node.getRight()) {
            tmp = node.getRight();
            tmp = getMinNode(node);
        } else {
            // 如果是父节点的左节点，则取父节点
            if (!valueStack.empty() && node == valueStack.peek().getLeft()) {
                tmp = valueStack.pop();
            }else if(!valueStack.empty() && node == valueStack.peek().getRight()){
                // 取出父节点
                node = valueStack.pop();
                while(!valueStack.empty() && null == tmp){
                    if(node == valueStack.peek().getRight()){
                         node = valueStack.pop();
                    }else if(node == valueStack.peek().getLeft()){
                        tmp = valueStack.pop();
                    }
                }
            }
        
        }
        return tmp;
    }

    @Override
    public Stack<BinTreeNode> getValueStack(BinTreeNode root,int value) {
        // TODO Auto-generated method stub
        Stack<BinTreeNode> parentStack = new Stack<>();
        while(null != root){
            if(value == root.getValue()){
                parentStack.push(root);
                break;
            }else if(value < root.getValue()){
                parentStack.push(root);
                root = root.getLeft();
            }else{
                parentStack.push(root);
                root = root.getRight();
            }
        }
        return parentStack;
    }



    
}