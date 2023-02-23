/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad1;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when((String destination) -> destination.startsWith("WAW"))  //filtrujemy liste
                       .mapEvery((String destination) ->
                                {                                                                          //mapujemy liste,zatem
                                  String[] z = destination.split(" ");                               //rozbijamy string na tablice stringow
                                  int priceInEuro = Integer.parseInt(z[2]);                                //zatem,zamieniamy string na int
                                  double priceInPLN = priceInEuro * xrate;                                 //obliczamy cene w PLN
                                  return "to " + z[1] + " - price in PLN:   " + (int) priceInPLN;     //zwracamy string
                                }
                        );
  }

  public static void main(String[] args) {
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
