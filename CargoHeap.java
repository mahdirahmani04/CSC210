/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author liz
 */
public class CargoHeap implements Heap{
    private Node root;
    private static int counter = 0;
    
    public Node Find (int index, int count)
    {
        
    }
    
    @Override
    public void add(Cargo entry) {
        Node newNode = new Node();
        newNode.setData(entry);
        
        if (root == null)
        {
            root = newNode;
            counter++;
            root.setIndex(counter);
        }
        else
        {
            int newIndex = counter + 1;
            int parentIndex = newIndex / 2;
        }
    }

    @Override
    public Cargo removeRoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Cargo target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cargo[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class Node
    {
        private Cargo data;
        private Node left = null;
        private Node right = null;
        private Node parent = null;
        private int index;

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
        
        public int getIndex ()
        {
            return index;
        }
        
        public void setIndex (int index)
        {
            this.index = index;
        }
        
        public Cargo getData() {
            return data;
        }

        public void setData(Cargo data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
