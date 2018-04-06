import java.util.*;
public abstract class Transporter implements Traveller, Comparable
{
    private String name = "";
    private double mct = 0.0; //Max Cargo Tonnage; using this for compareTo()
    private Stack<Cargo> cct = new Stack<Cargo>();
    private int speed = 0; //Speed of Transporter
    private int dist = 0; //Distance between Transporter and its current destination
    private String dest = "";
    private Random gen = new Random();
    private int myID; //equals()
    private static int nextID = 0;
    private boolean replaced = false;
    private Port location;
    
    public abstract boolean broken();
    public abstract void emergency() throws BrokeDownException;

    public abstract Transporter cloneMe() throws BadDataException;
    
    public void replace()
    {
         replaced = true;
    }
    
    public boolean replaced()
    {
         return replaced;
    }
    
    public void setDest(String inDest)
    {
         dest = inDest;
    }
    
    public String getDest()
    {
         return dest;
    }
    
    public boolean equals(Transporter other)
     {
          if (getID() == other.getID())
               return true;
          return false;
     }
     
     public int compareTo(Object other)
     {
          if (other instanceof Transporter)
          {
               if (getMax() < ((Transporter) other).getMax())
                    return -1;
               else if (getMax() > ((Transporter) other).getMax())
                    return 1;
               else
                    return 0;
          }
          return 0;
     }
    
    public String toString()
    {
         String output = "";
         output = "This is the transporter " +getName()+ ":\n";
         output = output + "\tTransporter Number: " + getID() + "\n";
         output = output + "\tCurrent Distance to Next Destination: " + getDistance() + "\n";
         output = output + "\tNext Destination: " + getDest() + "\n";
         output = output + "\tTravelling at a Speed of : " + getSpeed() + "\n";
         output = output + "\tMax Tonnage Able to Carry: " + getMax() + "\n*****CARGO ONBOARD*********\n";
         for (Cargo unit : cct)
         {
              output = output + unit;
         }
         output = output + "*****END CARGO LIST*********\n\tTotal Tonnage of Cargo: " + getCurrentCargoTonnage() + "\n";
         return output;
    }
    
    
    public Transporter(String inName, double inMax, int inSpeed, int inDist) throws BadDataException
    {
         setName(inName);
         setMax(inMax);
         setSpeed(inSpeed);
         setDistance(inDist);
         setID();
    }
    
    public Transporter(String inName, double inMax, int inSpeed) throws BadDataException
    {
         setName(inName);
         setMax(inMax);
         setSpeed(inSpeed);
         int tmp = gen.nextInt(900)+101;
         setDistance(tmp);
         setID();
    }
    
    public Transporter(String inName, double inMax) throws BadDataException
    {
         setName(inName);
         setMax(inMax);
         int tmp = gen.nextInt(51)+10;
         setSpeed(tmp);
         tmp = gen.nextInt(900)+101;
         setDistance(tmp);
         setID();
    }
    
    public Transporter(String inName) throws BadDataException
    {
         setName(inName);
         double temp = (gen.nextDouble() * 700) + 50;
         setMax(temp);
         int tmp = gen.nextInt(51)+10;
         setSpeed(tmp);
         tmp = gen.nextInt(900)+101;
         setDistance(tmp);
         setID();
    }
    
    public Transporter() throws BadDataException
    {
         double temp = (gen.nextDouble() * 700) + 50;
         setMax(temp);
         int tmp = gen.nextInt(51)+10;
         setSpeed(tmp);
         tmp = gen.nextInt(900)+101;
         setDistance(tmp);
         setID();
    }
    
    public int getID()
    {
         return myID;
    }
    
    private void setID()
    {
         myID = nextID++;
    }
    
    public void setSpeed(int inSpeed) throws BadDataException
    {
         if (inSpeed >= 0)
         {
              speed = inSpeed;
         }
         else
         {
              throw new BadDataException("Tried to set a Transporter's Speed to a negative number.");
         }
    }

    public void setDistance(int inDist) throws BadDataException
    {
         if (inDist >= 0)
         {
              dist = inDist;
         }
         else
         {
              throw new BadDataException("Tried to set a Transporter's Distance to next Destination to a negative number.");
         }
    }
    
    public int getSpeed()
    {
         return speed;
    }
    
    public int getDistance()
    {
         return dist;
    }

    public ArrayList<Cargo> getCurrentCargo()
    {
         return new ArrayList<Cargo>(cct); 
    }

    public double getMax()
    {
         return mct; 
    }

    public String getName()
    {
         return name;
    }

    /*
      This is stars inside
      it does multiple lines
     */

    public void setMax(double inMax) throws BadDataException
    {
         if (inMax >= 0)
         {
              mct = inMax;
         }
         else
         {
              throw new BadDataException("Tried to set a Transporter's Max Tonnage to a negative number.");
         }
    }

    public void setName(String inName)
    {
         name = inName;
    }
    
    public double getCurrentCargoTonnage()
    {
         double total = 0.0;
         for (Cargo unit : cct)
         {
              total += unit.getTonnage();
         }
         return total;
    }
    
    public boolean load(Cargo inCargo)
    {
         if (inCargo.getTonnage() < 0)
              return false;
         if (inCargo.getTonnage() + getCurrentCargoTonnage() > getMax())
              return false;
         
         cct.add(inCargo);
         return true;

    }

    public ArrayList<Cargo> unload(String port)
    {
         ArrayList<Cargo> toUnload = new ArrayList<Cargo>();
         for (int x = cct.size() - 1; x >= 0; x--)
         {
              if (cct.get(x).getDest().equals(port))
              {
                   toUnload.add(cct.get(x));
                   cct.remove(x);
              }
         }
         return toUnload;
    }
    
    public ArrayList<Cargo> unloadAll()
    {
         ArrayList<Cargo> toUnload = new ArrayList<Cargo>();
         for (Cargo unit : cct)
         {
              toUnload.add(unit);
         }
         cct.clear();
         return toUnload;
    }
    
    public String travel() throws BadDataException, BrokeDownException
    {
         if (broken() && this instanceof Repairable)
         {
              int days = ((Repairable) this).daysUntilFixed();
              ((Repairable) this).fix();
              throw new BrokeDownException(getName() + " is still broke down for " + days +" day(s).");
         }
         else if (broken())
         {
              throw new BrokeDownException(getName() + " is still broke down.");
         }
         
         emergency();
         
         int traveled = 0;
         String output = "";
         
         if (getDistance() == 0)
         {
              output = "Transporter is already in port or has no destination";
         }
         else if (getDistance() > getSpeed())
         {
              traveled = getDistance() - getSpeed();
              setDistance(traveled);
              output = "Transporter traveled " + getSpeed() + " units and is now " + getDistance() + " units from destination";
         }
         else if (getDistance() <= getSpeed())
         {
              setDistance(traveled);
              output = "The Transporter: " + getName() + " has arrived at " + getDest();
         }
         return output;
    }

    public Port getLocation() {
        return location;
    }

    public void setLocation(Port location) {
        this.location = location;
    }
    
    public boolean nextDest()
    {
         if (cct.size() != 0)
         {
              setDest(cct.get(0).getDest());
              return true;
         }
         return false;
    }

}
