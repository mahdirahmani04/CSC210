import java.util.*; // Need Random
import java.io.*; //Need File IO Tools
import java.util.logging.Level;
import java.util.logging.Logger;
public class Driver
{

    public static void main(String[] args)
    {
        File transFile = new File("Ports.txt");
        Scanner inputPorter = null;
         try
         {
              inputPorter = new Scanner(transFile);
         }
         catch (FileNotFoundException e)
         {
              System.err.println(e.getMessage());
              System.err.println("The Transporters.txt file is not in the current directory!  Exiting Program!");
         }

         ArrayList<Transporter> myPorters = new ArrayList<Transporter>();

         String line;
         String[] parts;
         Transporter st = null;
         while(inputPorter.hasNext())
         {
              line = inputPorter.nextLine();
              parts = line.split("%");
              try
              {
                   if (parts[0].equals("SHIP"))
                        myPorters.add(new Ship(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                   else if (parts[0].equals("AIRPLANE"))
                        myPorters.add(new Airplane(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[5])));
                   else if (parts[0].equals("TRAIN"))
                        myPorters.add(new Train(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5])));
                   else if (parts[0].equals("SEMITRUCK"))
                   {
                        st = new SemiTruck(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                        myPorters.add(st);
                        
                   }
              }
              catch (BadDataException e)
              {
                   System.err.println(e.getMessage());
              }

         }
         inputPorter.close();
         
         File portsFile = new File("Ports.txt");
         
         
         Scanner inputPorts = null;
         try
         {
              inputPorts = new Scanner(portsFile);
         }
         catch (FileNotFoundException e)
         {
              System.err.println(e.getMessage());
              System.err.println("The Ports.txt file is not in the current directory!  Exiting Program!");
         }

         ArrayList<Port> myPorts = new ArrayList<Port>();

         Port pt = null;
         while(inputPorts.hasNext())
         {
              line = inputPorts.nextLine();
              parts = line.split("%");
                        pt = new Port(parts[0],Boolean.valueOf(parts[1]),Boolean.valueOf(parts[2]),Boolean.valueOf(parts[3]),Boolean.valueOf(parts[4]));
                        myPorts.add(pt);

         }
         inputPorts.close();
         
         String[] Dest = new String[10];
         Dest[0] = "Winnipeg";
         Dest[1] = "Dublin";
         Dest[2] = "Beverly Hills";
         Dest[3] = "London";
         Dest[4] = "Barcelona";
         Dest[5] = "New York";
         Dest[6] = "Wichita";
         Dest[7] = "Grand Forks";
         Dest[8] = "Ely";
         Dest[9] = "St. Theresa Point";
         
         Random rng = new Random();
         for (Port p : myPorts)
         {
             for (int i=0; i<100; i++)
             {
                 try {
                     p.addOutbound(new Cargo(Dest[rng.nextInt(10)], rng.nextDouble()*10000));
                 } catch (BadDataException ex) {
                     Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         
        for (Transporter t: myPorters)
        {
            t.setLocation(Dest[rng.nextInt(10)]);
        }
    }
    /*
    public static void main(String[] args)
    {
         //Create a link to an input file
         File transFile = new File("Transporters.txt");
         
         
         Scanner inputPorter = null;
         try
         {
              inputPorter = new Scanner(transFile);
         }
         catch (FileNotFoundException e)
         {
              System.err.println(e.getMessage());
              System.err.println("The Transporters.txt file is not in the current directory!  Exiting Program!");
         }

         //Setup rest of reading tools and Array Lists
         ArrayList<Transporter> myPorters = new ArrayList<Transporter>();

         String line;
         String[] parts;
         Transporter st = null;
         while(inputPorter.hasNext())
         {
              //Read line
              line = inputPorter.nextLine();
              //Split line into parts
              parts = line.split("%");
              //Store Data
              try
              {
                   if (parts[0].equals("SHIP"))
                        myPorters.add(new Ship(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                   else if (parts[0].equals("AIRPLANE"))
                        myPorters.add(new Airplane(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[5])));
                   else if (parts[0].equals("TRAIN"))
                        myPorters.add(new Train(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5])));
                   else if (parts[0].equals("SEMITRUCK"))
                   {
                        st = new SemiTruck(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                        myPorters.add(st);
                        
                   }
              }
              catch (BadDataException e)
              {
                   System.err.println(e.getMessage());
              }

         }
         //Close input file, we are done with the input file
         inputPorter.close();

  System.out.println(myPorters + "\n\n\n\n\n\n");


         //Create a link to an input file
         File portsFile = new File("Ports.txt");
         
         
         Scanner inputPorts = null;
         try
         {
              inputPorts = new Scanner(portsFile);
         }
         catch (FileNotFoundException e)
         {
              System.err.println(e.getMessage());
              System.err.println("The Ports.txt file is not in the current directory!  Exiting Program!");
         }

         //Setup rest of reading tools and Array Lists
         ArrayList<Port> myPorts = new ArrayList<Port>();

         Port pt = null;
         while(inputPorts.hasNext())
         {
              //Read line
              line = inputPorts.nextLine();
              //Split line into parts
              parts = line.split("%");
              //Store Data
                        pt = new Port(parts[0],Boolean.valueOf(parts[1]),Boolean.valueOf(parts[2]),Boolean.valueOf(parts[3]),Boolean.valueOf(parts[4]));
                        myPorts.add(pt);

         }
         //Close input file, we are done with the input file
         inputPorts.close();

        System.out.println(myPorts + "\n\n\n\n\n\n");

        
        
        try {
            myPorts.get(0).addOutbound(new Cargo("Dublin", 1000));
            myPorts.get(0).addOutbound(new Cargo("Ely", 1234));
            myPorts.get(0).addOutbound(new Cargo("Dublin", 2000));
            myPorts.get(0).addOutbound(new Cargo("Ely", 4321));
            myPorts.get(0).addOutbound(new Cargo("Dublin", 3000));
        } catch (BadDataException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorters.get(0).setDistance(0);
        } catch (BadDataException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(myPorters.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorts.get(0).enterPort(myPorters.get(0));
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorts.get(0).load();
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Notice how when you look at the transporter,
        //The stack is upside down compared to the how
        //The cargo was stacked in the port.
        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();

        Transporter Tom = myPorts.get(0).leavePort();
        
        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println(Tom);
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println(myPorters.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorts.get(1).enterPort(myPorters.get(0));
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(myPorts.get(1));
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorts.get(1).unload();
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Again, note that the removed boxes are reversed
        //What was on top, is no on the bottom
        System.out.println(myPorts.get(1));
        System.out.println();
        System.out.println();
        System.out.println();   
        
        myPorts.get(1).leavePort();
        
            
        try {
            myPorts.get(0).addOutbound(new Cargo("New York", 1000));
            myPorts.get(0).addOutbound(new Cargo("Ely", 1234));
            myPorts.get(0).addOutbound(new Cargo("New York", 2000));
            myPorts.get(0).addOutbound(new Cargo("Ely", 4321));
            myPorts.get(0).addOutbound(new Cargo("New York", 3000));
        } catch (BadDataException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorters.get(3).setDistance(0);
        } catch (BadDataException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(myPorters.get(3));
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorts.get(0).enterPort(myPorters.get(3));
            myPorts.get(0).enterPort(myPorters.get(0));
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorts.get(0).load();
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Notice how when you look at the transporter,
        //The stack is upside down compared to the how
        //The cargo was stacked in the port.
        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();

        Tom = myPorts.get(0).leavePort();
        
        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println(Tom);
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorts.get(0).load();
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        Transporter Tami = myPorts.get(0).leavePort();
        System.out.println(myPorts.get(0));
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println(Tom);
        System.out.println();
        System.out.println();
        System.out.println();      
        
        
        System.out.println(Tami);
        System.out.println();
        System.out.println();
        System.out.println();
        
        try {
            myPorts.get(5).enterPort(Tami);
            myPorts.get(5).enterPort(Tom);
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(myPorts.get(5));
        System.out.println();
        System.out.println();
        System.out.println();

        
        try {
            myPorts.get(5).unload();
            myPorts.get(5).leavePort();
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Again, note that the removed boxes are reversed
        //What was on top, is no on the bottom
        System.out.println(myPorts.get(5));
        System.out.println();
        System.out.println();
        System.out.println();        
        System.out.println(Tami);
        System.out.println();
        System.out.println();
        System.out.println(); 
        
        try {
            myPorts.get(5).unloadAll();
            myPorts.get(5).leavePort();
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Again, note that the removed boxes are reversed
        //What was on top, is no on the bottom
        System.out.println(myPorts.get(5));
        System.out.println();
        System.out.println();
        System.out.println();        
        System.out.println(Tom);
        System.out.println();
        System.out.println();
        System.out.println(); 
        
        
        try {
            myPorts.get(8).enterPort(Tami);
        } catch (FacilitiesMismatchException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Since Ely doesn't have an airport,
        //This the Airplane shouldn't be here (can't land)
        //BUT since this airplane CAN airdrop,
        //The Cargo should arrive in Ely!
        System.out.println(myPorts.get(8));
        System.out.println();
        System.out.println();
        System.out.println();      

        System.out.println(Tami);
        System.out.println();
        System.out.println();
        System.out.println();      
        


        
  
    }
*/
}
