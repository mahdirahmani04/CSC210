/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * For when obvious passwords are detected
 * @author kerlin
 */
public class ObviousPasswordException extends Exception
{

    /**
     * Creates a new instance of <code>ObviousPasswordException</code> without
     * detail message.
     */
    public ObviousPasswordException()
    {
    }

    /**
     * Constructs an instance of <code>ObviousPasswordException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ObviousPasswordException(String msg)
    {
        super(msg);
    }
}
