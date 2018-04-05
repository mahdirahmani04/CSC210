
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A Binary Search Tree
 * @author kerlin
 */
public class BST {
    private TreeNode root = null;
    
    public void add(Cargo value)
    {
        //Package data
        TreeNode newNode = new TreeNode();
        newNode.setData(value);
        //Is BST empty?
        if (root == null)
        {
            root = newNode;
        }
        else
        {
            root = addToTree(root,newNode);
        }
    }
    
    private TreeNode addToTree(TreeNode localRoot, TreeNode newNode)
    {
        //Add if localRoot is null
        if (localRoot == null)
            return newNode;
        //Determine left/right
        else if (localRoot.getData().compareTo(newNode.getData()) < 0)
        {
            //Going right
            localRoot.setRight(addToTree(localRoot.getRight(), newNode));
            return localRoot;
        }
        else
        {
            //Going Left
            localRoot.setLeft(addToTree(localRoot.getLeft(), newNode));
            return localRoot;

        }
        
    }
    
    public boolean contains(Cargo target)
    {
        return search(root, target);
    }
    
    private boolean search(TreeNode localRoot, Cargo target)
    {
        //If we find a null, we didn't find the target!
        if (localRoot == null)
            return false;
        else if (localRoot.getData().compareTo(target) < 0)
        {
            //Go to right
            return search(localRoot.getRight(), target);
        }
        else if (localRoot.getData().compareTo(target) > 0)
        {
            //Go to Left
            return search(localRoot.getLeft(), target);
        }
        else
        {
            // CompareTo must have returned 0!
            return true;
        }
               
    }
    
    public ArrayList<Cargo> toArrayList()
    {
        ArrayList<Cargo> output = new ArrayList<Cargo>();
        output.addAll(inorder(root));
        return output;
    }
    private ArrayList<Cargo> inorder(TreeNode localRoot)
    {
        ArrayList<Cargo> output = new ArrayList<Cargo>();
        //Check if localRoot is NULL
        if (localRoot == null)
            return output;
       
        //Left Stuff First
        output.addAll(inorder(localRoot.getLeft()));
        //Add Middle (or localRoot) data
        output.add(localRoot.getData());
        //Add Right Stuff
        output.addAll(inorder(localRoot.getRight()));
        return output;
    }
    
    
    
    private class TreeNode
    {
        private Cargo data;
        private TreeNode left = null;
        private TreeNode right = null;

        public Cargo getData() {
            return data;
        }

        public void setData(Cargo data) {
            this.data = data;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
        
        
    }
    
    
}








