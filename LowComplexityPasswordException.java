/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * For when low complexity passwords are detected
 * @author kerlin
 */
public class LowComplexityPasswordException extends Exception
{

    /**
     * Creates a new instance of <code>LowComplexityPasswordException</code>
     * without detail message.
     */
    public LowComplexityPasswordException()
    {
    }

    /**
     * Constructs an instance of <code>LowComplexityPasswordException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LowComplexityPasswordException(String msg)
    {
        super(msg);
    }
}
