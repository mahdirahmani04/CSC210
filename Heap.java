/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Interface for a Cargo Heap
 * @author kerlin
 */
public interface Heap
{
    /**
     * Adds an entry to the heap
     * @param entry Cargo to add to the Heap
     */
    void add(Cargo entry);
    
    /**
     * Removes the current Root of the heap
     * @return the Cargo which was at the root of the heap
     */
    Cargo removeRoot();
    
    /**
     * Determines if a given target is in the heap or not
     * @param target Cargo to look for in the heap
     * @return true if target Cargo was in the heap, false otherwise
     */
    boolean contains(Cargo target);
    
    /**
     * Performs a LEVEL-ORDER traversal of the heap to generate an array of the heap elements
     * @return a LEVEL-ORDER array of the Cargo objects stored in the heap
     */
    Cargo[] toArray();
}
