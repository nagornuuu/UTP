package zad1;

import java.util.List;

public class WatekB extends Thread {

        final List<Towar> towar;                                                  //List of Towar objects
        int length = 0;                                                           //variable to store the length of the list
        int sum = 0;                                                              //variable to store the sum of the weight of the objects
        int tmp;                                                                  //variable to store the length of the list

        WatekB(List<Towar> towar) {                                               //Constructor of WatekB class
            this.towar = towar;                                                   //Assigning the list of Towar objects to the field of WatekB class
        }

        public void run() {
            try {
                Thread.sleep(100);                                          //waiting for the WatekA thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (length != towar.size()) {                                //while the length of the list is not equal to the length of the list stored in the formerLength variable
                for (int i = length; i < tmp; i++){                         //for loop to iterate through the list
                    if (i % 100 == 0) {
                        System.out.println("policzono wage " + i + " towarów");
                    }
                    synchronized (towar) {                                        //synchronized block to avoid the situation when the sum of the weight of the objects is calculated incorrectly
                        sum = sum + towar.get(i).weight;                          //adding the weight of the object to the sum
                    }
                }
                length = tmp;
                synchronized (towar) {                                            //synchronized block to avoid the situation when the sum of the weight of the objects is calculated incorrectly
                    tmp = towar.size();                                           //assigning the length of the list to the tmp variable
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(sum);
        }
}

