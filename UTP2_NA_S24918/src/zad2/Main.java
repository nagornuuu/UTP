/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    List<String> dest = Arrays.asList(
            "bleble bleble 2000",
            "WAW HAV 1200",
            "xxx yyy 789",
            "WAW DPS 2000",
            "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
            .filter((String departure) -> departure.startsWith("WAW"))   //filtrujemy liste
            .map((String departure) ->
                    {                                                                         //mapujemy liste,zatem
                      String[] z = departure.split(" ");                                //rozbijamy string na tablice stringow
                      int priceInEuro = Integer.parseInt(z[2]);                               //zatem,zamieniamy string na int
                      double priceInPLN = priceInEuro * ratePLNvsEUR;                         //obliczamy cene w PLN
                      return "to " + z[1] + " - price in PLN:   " + (int) priceInPLN;    //zwracamy string
                    }
            )
            .collect(Collectors.toList());    //zwracamy liste
    for (String r : result) System.out.println(r);
  }
}

