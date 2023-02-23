package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class ListCreator<T> {

    private List<T> catalog;

    private ListCreator(List<T> catalog) {
        this.catalog = catalog;    //lista z ktorej bedziemy tworzyc nowa liste zgodnie z warunkami
    }

    public static <R> ListCreator<R> collectFrom(List<R> catalog) {
        return new ListCreator<>(catalog);
    }
    //statyczną metodę collectFrom (lista) zwracającą obiekt klasy ListCreator

    public ListCreator<T> when(Predicate<T> pred) {  //metoda when przyjmująca obiekt klasy Predicate
        List<T> newCatalog = new ArrayList<>();      //tworzymy nowa liste
        for (T argument : this.catalog) {            //dla kazdego elementu listy
            if (pred.test(argument)) {               //jesli element spelnia warunek zapisany w metodzie test
                newCatalog.add(argument);            //dodajemy element do nowej listy
            }
        }
        this.catalog = newCatalog;          //nowa lista staje sie lista glowna
        return this;                        //zwracamy obiekt klasy ListCreator
    }
    public <R> List<R> mapEvery(Function<T, R> func) {     //metoda mapEvery przyjmujaca obiekt klasy Function
        List<R> newCatalog = new ArrayList<>();            //tworzymy nowa liste
        for (T argument : this.catalog) {                  //dla kazdego elementu listy
            newCatalog.add(func.apply(argument));          //dodajemy do nowej listy wynik zastosowania metody apply
        }
        return newCatalog;                                 //zwracamy nowa liste
    }
}
