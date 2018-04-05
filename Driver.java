import java.util.*; // Need Random
import java.io.*; //Need File IO Tools

public class Driver
{

    public static void main(String[] args) throws FileNotFoundException
    {
        //Creating an arrayList of Cargo
        Random rng = new Random();
        String[] places = {"Duluth", "St. Paul", "Minneapolis", "Rochester","Bloomington"};
        int y = 100000;
        //y = 10;
        ArrayList<Cargo> boxes = new ArrayList<Cargo>();
        for (int i = 0; i < y; i++)
        {
            boxes.add(new Cargo(places[rng.nextInt(5)], rng.nextDouble() * 100));
        }
        
        //I want to try some sorting, so I'm making some duplicates of my ArrayList
        ArrayList<Cargo> selection = (ArrayList<Cargo>) boxes.clone();
        ArrayList<Cargo> insertion = (ArrayList<Cargo>) boxes.clone();
        ArrayList<Cargo> quick = (ArrayList<Cargo>) boxes.clone();
        ArrayList<Cargo> merge = (ArrayList<Cargo>) boxes.clone();
        ArrayList<Cargo> collectionSort = (ArrayList<Cargo>) boxes.clone();
        /*
        //Sort
        System.out.println("Selection Sort took: " + selectionSort(selection) + " nano seconds.");
        System.out.println("Insertion Sort took: " + insertionSort(insertion) + " nano seconds.");
        System.out.println("Quick Sort took: " + quickSort(quick) + " nano seconds.");
        System.out.println("Merge Sort took: " + mergeSort(merge) + " nano seconds.");
        System.out.println("Collections.sort() took: " + collectionSort(collectionSort) + " nano seconds.");

        System.out.println("------------------------");

        //double check that the sorts worked
        if (!checkSort(selection))
            System.out.println("HEY!!! Selection didn't sort!!!");
        if (!checkSort(insertion))
            System.out.println("HEY!!! Insertion didn't sort!!!");
        if (!checkSort(quick))
            System.out.println("HEY!!! Quick didn't sort!!!");
        if (!checkSort(merge))
            System.out.println("HEY!!! Merge didn't sort!!!");
        if (!checkSort(collectionSort))
            System.out.println("HEY!!! Collection.sort() didn't sort!!!");
        
        System.out.println("------------------------");
        
        //Search time!  Let's make a list of targets to search for 
        ArrayList<Cargo> targets = new ArrayList<Cargo>();
        int x = 10000; //Number of searches to perform
        //x = 1;
        for (int i = 0; i < x; i++)
        {
            targets.add(boxes.get(rng.nextInt(boxes.size())));
        }
        
        //Search 
        System.out.println("Sequential Search of " + targets.size() + " items took: " + sequentialSearch(boxes, targets) + " nano seconds.");
        System.out.println("Binary Search of a presorted list of " + targets.size() + " items took: " + binarySearch(insertion, targets) + " nano seconds.");
        System.out.println("Searching using ArrayList's contains() method on a list of " + targets.size() + " items took: " + containsSearch(boxes, targets) + " nano seconds.");
        System.out.println("Searching using Collections' binarySearch() method on a presorted list of " + targets.size() + " items took: " + collectionSearch(insertion, targets) + " nano seconds.");                
        */
        
        BST aBST = new BST();
        Cargo forSearch = new Cargo("N/A", 0);
        for (int i=0; i<10000; i++)
        {
            Cargo temp = boxes.get(rng.nextInt(boxes.size()));
            aBST.add(temp);
            if (i == 5000)
                forSearch = temp;
        }
        
        long start = System.nanoTime();
        aBST.toArrayList();
        long end = System.nanoTime();
        System.out.println("Getting a sorted version of BST took: " + (end - start) + " nano seconds.");
        start = System.nanoTime();
        boolean found = aBST.contains(forSearch);
        end = System.nanoTime();
        System.out.println("If the target is found? " + found + "\nSearching the BST took: " + (end - start) + " nano seconds.");
        System.out.println("If the target is removed? " + aBST.remove(forSearch));
        
    }

    private static long binarySearch(ArrayList<Cargo> boxes, ArrayList<Cargo> targets)
    {
        long start = System.nanoTime();
        for (Cargo t: targets)
        {
            
           binary(boxes, t);
        }
        long end = System.nanoTime();
        return end - start;
    }
    
    private static boolean binary(List list, Cargo target)
    {

        if (list.size() <= 0)
            return false;

        if (((Cargo)list.get(list.size()/2)).compareTo(target) == 0)
            return true;

        if (((Cargo)list.get(list.size()/2)).compareTo(target) > 0)
            return binary(list.subList(0,list.size()/2), target);
        
        return binary(list.subList(list.size()/2 + 1, list.size()), target);

    }

    private static boolean checkSort(ArrayList<Cargo> sortedList)
    {
        for (int i = 1; i < sortedList.size(); i++)
        {
            if (sortedList.get(i-1).compareTo(sortedList.get(i)) >= 0)
                return false;
        }
        return true;
    }

    private static long collectionSearch(ArrayList<Cargo> boxes, ArrayList<Cargo> targets)
    {
        long start = System.nanoTime();
        for (int i = 0; i < targets.size(); i++)
        {
            Collections.binarySearch((List) boxes, targets.get(i));
        }
        long end = System.nanoTime();
        return end - start;
    }

    private static long collectionSort(ArrayList<Cargo> collectionSort)
    {
        long start = System.nanoTime();
        Collections.sort(collectionSort);
        long end = System.nanoTime();
        return end - start;
    }

    private static long containsSearch(ArrayList<Cargo> boxes, ArrayList<Cargo> targets)
    {
        long start = System.nanoTime();
        for (int i = 0; i < targets.size(); i++)
        {
            boxes.contains(targets.get(i));
        }
        long end = System.nanoTime();
        return end - start;
    }

    private static long insertionSort(ArrayList<Cargo> insertion)
    {
        long start = System.nanoTime();
        for (int i = 0; i < insertion.size(); i++)
        {
            int t = i-1;
            boolean fit = false;
            while (t >= 0 && !fit)
            {
                if (insertion.get(t+1).compareTo(insertion.get(t)) < 0)
                {
                    Cargo temp = insertion.get(t);
                    insertion.set(t, insertion.get(t+1));
                    insertion.set(t+1, temp);
                }
                else
                    fit = true;
                t--;
            }
                
        }
        long end = System.nanoTime();
        return end - start;

    }

    private static long mergeSort(ArrayList<Cargo> merge)
    {
        long start = System.nanoTime();
        merge(merge);
        long end = System.nanoTime();
        return end - start;
    }
    
    private static ArrayList<Cargo> merge(ArrayList<Cargo> list)
    {
        if (list.size() == 1)
            return list;
        
        ArrayList<Cargo> l1 = merge(new ArrayList<Cargo>(list.subList(0, list.size()/2)));
        ArrayList<Cargo> l2 = merge(new ArrayList<Cargo>(list.subList(list.size()/2, list.size())));
        
        list.clear();
        while(!l1.isEmpty() && !l2.isEmpty())
        {
            if (l1.get(0).compareTo(l2.get(0)) < 0)
                list.add(l1.remove(0));
            else
                list.add(l2.remove(0));
        }
        list.addAll(l1);
        list.addAll(l2);
        
        return list;
    }

    private static ArrayList<Cargo> quick(ArrayList<Cargo> list)
    {
        if (list.size() > 1)
        {
            ArrayList<Cargo> small = new ArrayList<Cargo>();
            ArrayList<Cargo> large = new ArrayList<Cargo>();
            Cargo mid = list.remove(list.size()/2);
            for (Cargo c: list)
            {
                if (c.compareTo(mid) > 0)
                    large.add(c);
                else
                    small.add(c);
            }
            list.clear();
            list.addAll(quick(small));
            list.add(mid);
            list.addAll(quick(large));
            
        }
            
        return list;
    }
    
    private static long quickSort(ArrayList<Cargo> quick)
    {
        long start = System.nanoTime();
        quick(quick);
        long end = System.nanoTime();
        return end - start;
    }

    private static long selectionSort(ArrayList<Cargo> selection)
    {
        long start = System.nanoTime();
        for (int i = 0; i < selection.size(); i++)
        {
            Cargo temp = Collections.min(selection.subList(i, selection.size()));
            selection.set(selection.indexOf(temp), selection.get(i));
            selection.set(i,temp);
        }
        long end = System.nanoTime();
        return end - start;
    }

    private static long sequentialSearch(ArrayList<Cargo> boxes, ArrayList<Cargo> targets)
    {
        long start = System.nanoTime();
        
        for(Cargo t : targets)
        {
            boolean match = false;
            int i = 0;
            while ( i < boxes.size() && !match)
            {
                if (boxes.get(i).equals(t))
                {
                    match = true;
                }
                i++;
            }
        }
        
        long end = System.nanoTime();
        return end - start;
    }
    
    


}