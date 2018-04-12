
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * minheap test
 * @author Zishuo
 */
public class driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CargoHeap aHeap = new CargoHeap();
        Random rng = new Random();
        ArrayList<Cargo> box = new ArrayList<>();
        for (int i=0; i<10; i++)
        {
            box.add(new Cargo("lab11", rng.nextDouble()*100));
        }
        System.out.println(box.toString());
        
        for (int i=0; i<10; i++)
        {
            aHeap.add(box.get(i));
        }
        System.out.println(aHeap.toArray().toString());
        
        System.out.println("Cargo is found? " + aHeap.contains(box.get(0)));
        
        aHeap.removeRoot();
        System.out.println(aHeap.toArray().toString());
        aHeap.removeRoot();
        System.out.println(aHeap.toArray().toString());
        
        
    }
    
}
