import java.util.*;
public class Ship extends Transporter
{
     private boolean isSunk = false;
     
     public void emergency() throws BrokeDownException
     {
          Random rng = new Random();
          if (rng.nextInt(1000000) == 13)
          {
               sink();
               throw new BrokeDownException("Ship " + getName() + " has been sunk!");
          }
     }
     
     public Transporter cloneMe() throws BadDataException
     {
          Ship clone = new  Ship(getName(), getMax(), getSpeed(), getDistance());
          for (Cargo c : unloadAll())
          {
               clone.load(c);
          }
          replace();
          return clone;
     }
     
     
     public boolean broken()
     {
          return isSunk;
     }
     
     public Traveller.RouteType getRouteType()
     {
          return RouteType.WATER;
     }
     
     public String toString()
     {
          return "This Transporter is a Ship\n" + super.toString() + "\tCurrently is sunk: \t" + getSunk();
     }
     
     public void sink()
     {
          isSunk = true;
     }
     
     public boolean getSunk()
     {
          return isSunk;
     } 
     
     public Ship(String inName, double inMax, int inSpeed, int inDist) throws BadDataException
     {
          super(inName,inMax,inSpeed,inDist);
     }
     
     public Ship(String inName, double inMax, int inSpeed) throws BadDataException
     {
          super(inName,inMax,inSpeed);
     }
     
     public Ship(String inName, double inMax) throws BadDataException
     {
          super(inName,inMax);
     }
     
     public Ship(String inName) throws BadDataException
     {
          super(inName);
     }
     
     public Ship() throws BadDataException
     {
          super();
     }
     
}
