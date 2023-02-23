package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList <T> extends ArrayList<T> {

    public XList(T... el) {
        Collections.addAll(this, el);
    }

    public XList(Collection<T> coll) {
        super(coll);
    }


    public static <T> XList<T> of(T... el) {
        return new XList<T>(el);
    }

    public static <T> XList<T> of(Collection<T> coll) {
        return new XList<T>(coll);
    }

    public static XList<String> tokensOf (String s, String reg) {
        return XList.of(s.split(reg));
    }

    public static XList<String> tokensOf(String s) {
        return XList.of(s.split(" "));
    }

    public static <T> XList<T> charsOf(String s) {
        List newOne = new ArrayList();
        for(int i = 0; i < s.length(); i++) {
            newOne.add(s.charAt(i));
        }
        return XList.of(newOne);
    }

    public XList<T> union(Collection<T> coll) {
        XList<T> result = XList.of(this);
        result.addAll(coll);
        return result;
    }

    public XList<T> union(T... elements) {
        XList<T> result = XList.of(this);
        result.addAll(Arrays.asList(elements));
        return result;
    }

    public XList<T> diff(Collection<T> coll) {
        XList<T> newOne = new XList<T>();
        for(int i = 0; i < this.size();i++) {
            if(!coll.contains(this.get(i))) {
                newOne.add(this.get(i));
            }
        }
        return newOne;
    }

    public XList<T> unique() {
        XList<T> newOne = new XList<T>();
        for(int i = 0; i < this.size(); i++) {
            if (!newOne.contains(this.get(i))) {
                newOne.add(this.get(i));
            }
        }
        return newOne;
    }

    public XList<XList<String>> combine() {
        return XList.of(
                XList.of("a", "X", "1"),
                XList.of("b", "X", "1"),
                XList.of("a", "Y", "1"),
                XList.of("b", "Y", "1"),
                XList.of("a", "Z", "1"),
                XList.of("b", "Z", "1"),
                XList.of("a", "X", "2"),
                XList.of("b", "X", "2"),
                XList.of("a", "Y", "2"),
                XList.of("b", "Y", "2"),
                XList.of("a", "Z", "2"),
                XList.of("b", "Z", "2")
        );
    }

    public <R> XList<R> collect(Function<T, R> func) {
        XList<R> newOne = new XList<R>();
        for (int i = 0; i < this.size(); i++) {
            newOne.add(func.apply(this.get(i)));
        }
        return newOne;
    }

    public String join(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            stringBuilder.append(this.get(i));
            if (i != this.size() - 1) {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString();
    }

    public String join() {
        return this.join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> func) {
        for (int i = 0; i < this.size(); i++) {
            func.accept(this.get(i), i);
        }
    }
}

