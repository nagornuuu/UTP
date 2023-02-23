package zad4;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {
    public T object;

    private Maybe(T object){
        this.object = object;
    }

    public static<R> Maybe<R> of(R object){
        return new Maybe(object);
    } // zwraca obiekt Maybe z obiektem object

    public void ifPresent(Consumer<T> cons){
        if (object != null)
            cons.accept(object); // wykonuje akcję cons na obiekcie object
    }

    public <R> Maybe<R> map(Function<T,R> func) {
        if (object == null)
            return new Maybe(null);
            R newObj = func.apply(object); // wykonuje funkcję func na obiekcie object
            return new Maybe(newObj);
    }

    public T get() {
        if (object == null)
            throw new NoSuchElementException("Maybe is empty");
        return object;
    }

    public boolean isPresent(){
        return object != null;
    }  // zwraca true jeśli obiekt object nie jest null

    public T orElse(T def) {
        if (object != null)
            return object;
        else
            return def; // zwraca obiekt object lub domyślny obiekt def
    }

    public Maybe<T> filter (Predicate<T> pred) {
        if (pred.test(object)) // wykonuje predykat pred na obiekcie object
            return this;  // zwraca obiekt Maybe z obiektem object
            return new Maybe(null); // lub z obiektem null
    }
    public String toString(){
        if (object != null)
            return "Maybe has value " + object;
        return "Maybe is empty";
    }
}
