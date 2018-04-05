import java.util.*;
public class Airplane extends Transporter
{
     private boolean crashed = false;
     private boolean canAirDrop = false;
     
     public Transporter cloneMe() throws BadDataException
     {
          Airplane clone = new  Airplane(getName(), getMax(), getSpeed(), getDistance(), getCanAirDrop());
          for (Cargo c : unloadAll())
          {
               clone.load(c);
          }
          replace();
          return clone;
     }
     
     public void emergency() throws BrokeDownException
     {
          Random rng = new Random();
          if (rng.nextInt(11000000) == 13)
          {
               crash();
               throw new BrokeDownException("Airplane " + getName() + " has crashed.");
          }
     }
     
     public Traveller.RouteType getRouteType()
     {
          return RouteType.AIR;
     }
     
     public boolean broken()
     {
          return crashed;
     }
     
     public String toString()
     {
          return "This Transporter is an Airplane\n" + super.toString() + "\tHas the ability to Air Drop: \t" + getCanAirDrop() + "\n\tIs currently crashed: \t" + getCrashed();
     }
     
     public void changeDropType()
     {
          canAirDrop = !canAirDrop;
     }
     
     public boolean getCanAirDrop()
     {
          return canAirDrop;
     }
     
     public void crash()
     {
          crashed = true;
     }
     
     public boolean getCrashed()
     {
          return crashed;
     } 
     
     public Airplane (String inName, double inMax, int inSpeed, int inDist, boolean inAirDrop) throws BadDataException
     {
          super(inName,inMax,inSpeed,inDist);
          canAirDrop = inAirDrop;
          
     }
     
     public Airplane (String inName, double inMax, int inSpeed, int inDist) throws BadDataException
     {
          super(inName,inMax,inSpeed,inDist);
          
     }
     
     public Airplane(String inName, double inMax, int inSpeed) throws BadDataException
     {
          super(inName,inMax,inSpeed);
     }
     
     public Airplane(String inName, double inMax) throws BadDataException
     {
          super(inName,inMax);
     }
     
     public Airplane(String inName) throws BadDataException
     {
          super(inName);
     }
     
     public Airplane() throws BadDataException
     {
          super();
     }
     
}
