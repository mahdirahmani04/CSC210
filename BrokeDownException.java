public class BrokeDownException extends Exception
{
     public BrokeDownException()
     {
          super("The current Transporter is Broken Down.\n");
     }
     
     public BrokeDownException(String altMSG)
     {
          super(altMSG + "\n");
     }
}