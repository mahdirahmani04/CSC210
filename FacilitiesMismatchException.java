public class FacilitiesMismatchException extends Exception
{
     public FacilitiesMismatchException()
     {
          super("Error: You attempted to transfer Cargo between a Port and a Transporter for which the Port does not have Cargo transfer facilities.\n");
     }
     
     public FacilitiesMismatchException(Port badPort, Transporter badPorter)
     {
          super("Error: You attempted to transfer Cargo between " + badPorter.getName() + ", a " + badPorter.getRouteType() + " Route Transporter, and Port " + badPort.getName() + ".  However, " + badPort.getName() + " does not have facilities for transferring cargo with " + badPorter.getRouteType() + " Route Transporters.\n");
     }
}