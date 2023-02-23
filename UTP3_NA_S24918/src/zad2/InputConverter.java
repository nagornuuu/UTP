package zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {
    T file;

    InputConverter(T file) {
        this.file = file;
    }

    public<T> T convertBy(Function... function) {
        List functions = new ArrayList();
        functions.add(function[0].apply(file));

        for(int i = 1; i < function.length; i++) {
            functions.add(function[i].apply(functions.get(i-1)));
        }

        return (T)functions.get(functions.size() - 1);
    }
}
