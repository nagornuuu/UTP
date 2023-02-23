/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad1;


public interface Mapper <T, R>{

    R map (T argument);

}
//Mapper - z metodą map, będącą dowolną funkcją: argument -> wynik
//T - typ argumentu
//R - typ wyniku