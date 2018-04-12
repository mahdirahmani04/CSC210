/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A Dual Heap (Max AND Min Heap)
 * @author kerlin
 */
public interface DualHeap
{
    void add(Object newObject);
    Object removeMax();
    Object removeMin();
    Object peekMax();
    Object peekMin();
}
