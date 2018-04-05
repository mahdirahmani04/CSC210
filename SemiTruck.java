import java.util.*;
public class SemiTruck extends Transporter implements Repairable
{
     private boolean flatTire = false;
     private int trailers = 1; 
     private int daysToFix = 0;
     
     public void emergency() throws BrokeDownException
     {
          Random rng = new Random();
          if (rng.nextInt(2840) == 13)
          {
               gotFlat();
               throw new BrokeDownException("SemiTruck " + getName() + " has punctured a tire!");
          }
     }
     
     public Transporter cloneMe() throws BadDataException
     {
          SemiTruck clone = new  SemiTruck(getName(), getMax(), getSpeed(), getDistance(), getTrailers());
          for (Cargo c : unloadAll())
          {
               clone.load(c);
          }
          replace();
          return clone;
     }
     
     public void fix()
     {
          if (daysToFix > 0)
          {
               if (--daysToFix == 0)
               {
                    fixFlat();
               }
          }
     }
     
     public int daysUntilFixed()
     {
          return daysToFix;
     }
     
     public boolean broken()
     {
          return flatTire;
     }
     
     public Traveller.RouteType getRouteType()
     {
          return RouteType.ROAD;
     }
     
     public String toString()
     {
          return "This Transporter is a SemiTruck\n" + super.toString() + "\tHas " + getTrailers() + " trailers in tow.\n\tCurrently has a flat tire: \t" + getFlatTire();
     }
     
     public void setTrailers(int num) throws BadDataException
     {
          if (num >= 0)
          {
               trailers = num;
          }
          else
          {
               throw new BadDataException("Tried to set a SemiTruck to have zero or fewer trailers.");
          }
     }
     
     public int getTrailers()
     {
          return trailers;
     } 
     
     public void fixFlat()
     {
          flatTire = false;
     } 
     
     public void gotFlat()
     {
          flatTire = true;
     }
     
     public boolean getFlatTire()
     {
          return flatTire;
     }

     public SemiTruck(String inName, double inMax, int inSpeed, int inDist, int inTrailers) throws BadDataException
     {
          super(inName,inMax,inSpeed,inDist);
          setTrailers(inTrailers);
     }
     
     public SemiTruck(String inName, double inMax, int inSpeed, int inDist) throws BadDataException
     {
          super(inName,inMax,inSpeed,inDist);
     }
     
     public SemiTruck(String inName, double inMax, int inSpeed) throws BadDataException
     {
          super(inName,inMax,inSpeed);
     }
     
     public SemiTruck(String inName, double inMax) throws BadDataException
     {
          super(inName,inMax);
     }
     
     public SemiTruck(String inName) throws BadDataException
     {
          super(inName);
     }
     
     public SemiTruck() throws BadDataException
     {
          super();
     }
     
}
