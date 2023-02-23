package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class WatekA extends Thread {

    List<Towar> towar;                                                                                       //List of Towar objects

    WatekA(List<Towar> towar) {                                                                              // Constructor of WatekA class
        this.towar = towar;                                                                                  // Assigning the list of Towar objects to the field of WatekA class
    }

    public void run() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\andrii\\IntelljProjects\\utp\\UTP7_NA_S24918\\Towary.txt")); //path to file
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {                                                         //read line by line
                String[] parts = line.split(" ");                                                      //split line by space
                towar.add(new Towar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));                //add new object to list
                count++;
                if (count % 200 == 0) {                                                                      //print every 200th line
                    System.out.println("utworzono " + count + " obiektów");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



