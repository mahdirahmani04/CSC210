
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Checks password from the user
 * @author kerlin
 */
public class PasswordDriver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Get input
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your Username: ");
        String username = scan.nextLine();
        System.out.print("\nEnter your Password: ");
        String password = scan.nextLine();
        
        try
        {
            //Call passwordChecker
            passwordChecker(username, password);
        }
        catch (TooShortPasswordException ex)
        {
            //This Logger stuff is a bit overkill.  Basically, it:
            // 1) Throws out a time stamp of when the exception occured
            // 2) Notes that the exception is severe
            // 3) Prints out the message of the Exception (which we passed in when we threw the exception
            // 4) Prints out where the exception was triggered from (file name, method name, and line numbers)
            // If you let NetBeans create your try/catch block, this will be the result!
            Logger.getLogger(PasswordDriver.class.getName()).log(Level.SEVERE, null, ex);
            
            //Adding exit call
            System.exit(15);
        }
        catch (LowComplexityPasswordException ex)
        {
            Logger.getLogger(PasswordDriver.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(11);
        }
        catch (ObviousPasswordException ex)
        {
            Logger.getLogger(PasswordDriver.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(123);
        }
        catch (PasswordException ex)
        {
            Logger.getLogger(PasswordDriver.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(5);
        }
        
        
    }

    private static void passwordChecker(String username, String password) throws TooShortPasswordException, LowComplexityPasswordException, ObviousPasswordException, PasswordException
    {
        //Check Length
        if (password.length() < 16)
            throw new TooShortPasswordException("EXCEPTION: You should have at LEAST 16 characters in your password");
        
        //Check Complexity
        boolean hasUpper = false, hasLower = false, hasDigit = false; //Set to false, so if they end up true, we found something!
        for (char c : password.toCharArray()) //For each character (c) in password
        {
            if (Character.isUpperCase(c))  //If we find an UPPERcase, note this!
                hasUpper = true;
            else if (Character.isLowerCase(c)) //If we find a LOWERcase, note this!
                hasLower = true;
            else if (Character.isDigit(c)) //If we find a digit, note this!
                hasDigit = true;
        }
        //Did we find at least 1 character of each type?
        //  hasUpper && hasLower && hasDigit  == true if all three are there.
        //Since I want to know if even 1 is missing, I'll Not this
        if (!(hasUpper && hasLower && hasDigit))
        {
            throw new LowComplexityPasswordException("EXCEPTION: You didn't include at least 1 of: lowercase, uppercase, and digits.");
        }
        
        //Check for obvious passwords
        //Create an array of obvious passwords
        String[] obvious = {"password","1234","111111","qwerty","abc123","monkey","football","dragon","letmein","iloveyou","admin","login","welcome","flower","zaq1"};        
        
        //Run through each obvious password to make sure that they aren't part of the password
        boolean hasBad = false;
        for (String bad: obvious) //For each bad word in the list of obvious passwords
        {
            if (password.contains(bad)) //If we find a bad word, note that we found one
                hasBad = true;
        }
        
        //Did we find any bad words?
        if (hasBad)
            throw new ObviousPasswordException("EXCEPTION: You used an obvious word as part of your password!");
        
        
        //Check the other stuff
        //create Backwards username
        String backwardsUsername = "";
        for (int i = 0; i < username.length(); i++)
        {
            backwardsUsername = username.charAt(i) + backwardsUsername;
        }
            
        //My other thing is that if password contains the username
        if ((password.equals(username)) || password.equals(backwardsUsername) || password.contains(username))
            throw new PasswordException("EXCEPTION: You had something else wrong with your password.");
    }
    
}
