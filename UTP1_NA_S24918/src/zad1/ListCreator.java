/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> {

    private List<T> catalog;

    public ListCreator(List<T> catalog) {
        this.catalog = catalog; //lista z ktorej bedziemy tworzyc nowa liste zgodnie z warunkami
    }

    public static <T> ListCreator<T> collectFrom(List<T> catalog) {
        return new ListCreator<T>(catalog);
    }
    //statyczną metodę collectFrom (lista) zwracającą obiekt klasy ListCreator

    public ListCreator<T> when(Selector<T> selector) { //metoda when przyjmująca obiekt klasy Selector
        List<T> catalog1 = new ArrayList<T>();

        for (T x : this.catalog) {              //dla kazdego elementu listy
            if (selector.select(x)) {           //jesli element spelnia warunek zapisany w metodzie select
                catalog1.add(x);                //dodajemy element do nowej listy
            }
        }

        this.catalog = catalog1;       //nowa lista staje sie lista glowna
        return this;                   //zwracamy obiekt klasy ListCreator
    }

    public <R> List<R> mapEvery(Mapper<T, R> mapper) { //metoda mapEvery przyjmujaca obiekt klasy Mapper
        List<R> catalog1 = new ArrayList<R>();

        for (T x: this.catalog) {              //dla kazdego elementu listy
            catalog1.add(mapper.map(x));       //dodajemy do nowej listy wynik zastosowania metody map
        }
        return catalog1;                       //zwracamy nowa liste
    }
}