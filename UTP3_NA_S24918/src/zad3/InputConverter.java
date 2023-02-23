package zad3;

import java.util.function.Function;

public class InputConverter<T> {
    T file;

    InputConverter(T file) {
        this.file = file;
    }

    public<T> T convertBy(Function... functions) {
        Object in = this.file;
        Object out = null;

        for (int i = 0; i < functions.length; i++) {
            out = functions[i].apply(in);
            in = out;
        }
        return (T)out;
    }

}
