/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {
    List<Purchase> list;

    public CustomersPurchaseSortFind() {
        list = new ArrayList<Purchase>();
    }

    public void readFile(String f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {                        // read line by line
                String arrays[] = line.split(";");                    // split line by ";"
                list.add(new Purchase(arrays[0], arrays[1], arrays[2], Double.parseDouble(arrays[3]), Double.parseDouble(arrays[4]))); // add new Purchase to list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String word) {
        Comparator<Purchase> sortByName;                                            // create comparator
        Comparator<Purchase> sortByIdOfClients = new Comparator<Purchase>() {
            @Override
            public int compare(Purchase argument1, Purchase argument2) {            // compare by id of clients
                return argument1.idOfClient.compareTo(argument2.idOfClient);
            }
        };
        if (word.equals("Nazwiska")) {
            sortByName = new Comparator<Purchase>() {                               // compare by name of clients
                @Override
                public int compare(Purchase argument1, Purchase argument2) {
                    return argument1.name.compareTo(argument2.name);
                }
            };
        } else {
            sortByName = new Comparator<Purchase>() {                               // compare by name of products
                @Override
                public int compare(Purchase argument1, Purchase argument2) {
                    double price1 = argument1.quantity * argument1.price;          // calculate price of product
                    double price2 = argument2.quantity * argument2.price;
                    if (price1 == price2) {
                        return 0;
                    } else if (price1 < price2) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            };
        }
        ArrayList<Purchase> sortedList = new ArrayList<Purchase>(list);
        sortedList.sort(sortByName.thenComparing(sortByIdOfClients)); // sort list

        System.out.println(word);
        for (int i = 0; i < sortedList.size(); i++) {             // print list
            System.out.println(sortedList.get(i));
        }
        if (word.equals("koszty")) {
            System.out.println(" (koszt: " + sortedList.get(0).quantity * sortedList.get(0).price + ")");
        } else {
            System.out.println();
        }
    }

    public void showPurchaseFor(String id) {
        Object arr[] = list.stream().filter(p -> p.idOfClient.equals(id)).toArray(); // filter list by id of client
        System.out.println("Klient " + id);
        for (int i = 0; i < arr.length; i++) { // print list of purchases
            System.out.println(arr[i]);
        }
        System.out.println();
    }
}
