package com.test.lsj.algorithm.practice;

import com.test.lsj.algorithm.bean.TreeNode;
import com.test.lsj.algorithm.utils.BinTreeUtils;

/*
 * 重组二叉树
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 思路: 前序遍历节点 中 -> 左 -> 右
 *       中序遍历节点 左 -> 中 -> 右
 *       遍历中序数组，找到与前序数组第一个数相同的节点,下标为i，则i为根节点，0~i-1 为左子树，i+1~length 为右子树
 *       递归下去，前序遍历左子树取(1~0+左子树数量) 右子树取(0+左子树数量+1~length)     中序遍历序列左子树取0~i-1   右子树取 i+1~length
 *       递归下去，前序遍历左子树取(1~0+左子树数量) 右子树取(0+左子树数量+1~length)     中序遍历序列左子树取0~i-1   右子树取 i+1~length
 */
public class RebulidBinTree {
    public static void main(String [] args){
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode treeNode = reConstructBinaryTree(pre,in);
        BinTreeUtils.preViewTree(treeNode);
    }

    public static TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if(null == pre || null == in) {
            return null;
        }
        TreeNode targetNode = buildTree(pre,0,pre.length - 1,in,0,in.length -1);
        return targetNode;
    }

    private static TreeNode buildTree(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){

        TreeNode rootNode = null;
        if(preStart > preEnd || inStart >inEnd){
            return rootNode;
        }

        int index = inStart;
        for(int i = inStart;i <= inEnd ; i++){
            if(in[i] == pre[preStart]){
                rootNode = new TreeNode(in[i]);
                index = i;
                break;
            }
        }

        rootNode.left = buildTree(pre,preStart + 1,preStart + index - inStart,in,inStart,index -1);
        rootNode.right = buildTree(pre,preStart + index - inStart + 1,preEnd,in,index + 1,inEnd);
        return rootNode;
    }
}
