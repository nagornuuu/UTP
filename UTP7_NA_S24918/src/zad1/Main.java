package zad1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */


public class Main {

  public static void main(String[] args) {

    List<Towar> towar = Collections.synchronizedList(new ArrayList<>());  //creating a synchronized list of Towar objects
    WatekA load = new WatekA(towar);
    WatekB count = new WatekB(towar);
    load.start();
    count.start();

  }
}

//https://www.geeksforgeeks.org/collections-synchronizedlist-method-in-java-with-examples/
//https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html
