/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * For Short Passwords
 * @author kerlin
 */
public class TooShortPasswordException extends PasswordException
{

    /**
     * Creates a new instance of <code>TooShortPasswordException</code> without
     * detail message.
     */
    public TooShortPasswordException()
    {
    }

    /**
     * Constructs an instance of <code>TooShortPasswordException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TooShortPasswordException(String msg)
    {
        super(msg);
    }
}
