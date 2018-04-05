import java.util.*;
public class Train extends Transporter
{
     private boolean derailed = false;
     private int engines = 1;
     
     public Transporter cloneMe() throws BadDataException
     {
          Train clone = new  Train(getName(), getMax(), getSpeed(), getDistance(), getEngines());
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
          if (rng.nextInt(50000) == 13)
          {
               derail();
               throw new BrokeDownException("Train " + getName() + " has derailed!");
          }
     }
     
     public boolean broken()
     {
          return derailed;
     }  
     
     public Traveller.RouteType getRouteType()
     {
          return RouteType.RAIL;
     }
       
     public String toString()
     {
          return "This Transporter is a Train\n" + super.toString() + "\tHas " + getEngines() + " engines pulling.\n\tCurrently is derailed: \t" + getDerailed();
     }
     
     public void setEngines(int num) throws BadDataException
     {
          if (num > 0)
          {
               engines = num;
          }
          else
          {
               throw new BadDataException("Tried to set a Train to have zero or less engines.");
          }
     }
     
     public int getEngines()
     {
          return engines;
     } 
     
     public void derail()
     {
          derailed = true;
     }
     
     public boolean getDerailed()
     {
          return derailed;
     }
     
     public Train(String inName, double inMax, int inSpeed, int inDist, int inEngines) throws BadDataException
     {
          super(inName,inMax,inSpeed,inDist);
          setEngines(inEngines);
     }
     
     public Train(String inName, double inMax, int inSpeed, int inDist) throws BadDataException
     {
          super(inName,inMax,inSpeed,inDist);
     }
     
     public Train(String inName, double inMax, int inSpeed) throws BadDataException
     {
          super(inName,inMax,inSpeed);
     }
     
     public Train(String inName, double inMax) throws BadDataException
     {
          super(inName,inMax);
     }
     
     public Train(String inName) throws BadDataException
     {
          super(inName);
     }
     
     public Train() throws BadDataException
     {
          super();
     }
     
}
