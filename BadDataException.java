public class BadDataException extends Exception
{
     public BadDataException()
     {
          super("An attribute was attempted to be set to a value outside of the approved range for the specified attribute.\n");
     }
     
     public BadDataException(String specificInfo)
     {
          super(specificInfo + "\n");
     }
     
}