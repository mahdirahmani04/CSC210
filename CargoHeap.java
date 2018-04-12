/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * minHeap
 * @author Zishuo
 */
public class CargoHeap implements Heap{
    private Node root = null;
    private static int counter = 0;
    
    
    public Node Find (int index)
    {
        int count = (int) (Math.log(index) / Math.log(2));
        double mid = 2^count + (((2^(count+1) - 1) - (2^count)) / 2);
        return findingNode(count, count, mid, index, root);
    }
    
    private Node findingNode (int count, int constant, double mid, int index, Node root)
    {
        if (count == 0)
            return root;

        if ((double)index < mid)
        {
            root = root.getLeft();
            mid = (double) ((2^constant) + (((int)mid - (2^constant)) / 2));
            return findingNode(count - 1, constant, mid, index, root);
        }
        else
        {
            root = root.getRight();
            mid = (double) ((int)(mid + 1) + ((((2^constant) - 1) - (int)(mid + 1)) / 2));
            return findingNode(count - 1, constant, mid, index, root);
        }
        
    }
    @Override
    public void add(Cargo entry) {
        Node newNode = new Node();
        newNode.setData(entry);
        
        if (root == null)
        {
            root = newNode;
            root.setIndex(counter+1);
            counter++;
        }
        else
        {
            int newIndex = counter + 1;
            int parentIndex = newIndex / 2;
            newNode.setParent(Find(parentIndex));
            if (newIndex % 2 == 0)
                newNode.getParent().setLeft(newNode);
            else
                newNode.getParent().setRight(newNode);
            
            while (parentIndex > 0 && entry.compareTo(Find(parentIndex)) < 0)
            {
                Find(newIndex).setData(Find(parentIndex).getData());
                newIndex = parentIndex;
                parentIndex = newIndex / 2;
            }
            Find(newIndex).setData(entry);
            newNode.setIndex(counter+1);
            counter++;
        }
    }

    @Override
    public Cargo removeRoot() {
        if (root == null)
            return null;
        else
        {
            Cargo result = root.getData();
            root.setData(Find(counter).getData());
            reheap(root);
            if (counter % 2 == 0)
                Find(counter).getParent().setLeft(null);
            else
                Find(counter).getParent().setRight(null);
            Find(counter).setData(null);
            Find(counter).setParent(null);
            Find(counter).setIndex(-1);
            counter--;
            return result;
        }
    }

    private Node reheap (Node root)
    {
        boolean leftSmaller = true;
        if (root.getLeft().getData().compareTo(root.getRight().getData()) > 0)
                leftSmaller = false;
        if (leftSmaller)
            if (root.getData().compareTo(root.getLeft().getData()) <= 0 || root.getLeft() == null)
                return root;
            else
            {
                Cargo curData = root.getData();
                root.setData(root.getLeft().getData());
                root.getLeft().setData(curData);
                return reheap(root.getLeft());
            }
        else
        {
            if (root.getData().compareTo(root.getRight().getData()) <= 0 || root.getRight() == null)
                return root;
            else
            {
                Cargo curData = root.getData();
                root.setData(root.getRight().getData());
                root.getRight().setData(curData);
                return reheap(root.getRight());
            }
        }
    }
    
    @Override
    public boolean contains(Cargo target) {
        int i = 0;
        boolean found = false;
        while (!found && i < counter)
        {
            if (Find(i).getData().compareTo(target) == 0)
                found = true;
            i++;
        }
        return found;
    }

    @Override
    public Cargo[] toArray() {
        Cargo[] cur = new Cargo[counter+1];
        for (int i=1; i<=counter; i++)
        {
            cur[i] = Find(i).getData();
        }
        return cur;
    }
    
    public class Node
    {
        private Cargo data;
        private Node left = null;
        private Node right = null;
        private Node parent = null;
        private int index = -1;

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
