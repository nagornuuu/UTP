/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {
  public static void main(String[] args)  {

    Function<String, List<String>> flines =
            s -> {
              try {
                BufferedReader br = new BufferedReader(new FileReader(s));
                List<String> list = new ArrayList<>();
                String l;
                while ((l = br.readLine()) != null) {
                  list.add(l);
                }
                br.close();
                return list;
              } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<String>();
              }
            };

    Function<List<String>, String> join = s -> String.join("", s);

    Function<String, List<Integer>> collectInts =
            s -> {
              StringBuilder sb = new StringBuilder();
              List<Integer> integer = new ArrayList<>();
              for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                  sb.append(s.charAt(i));
                } else {
                  sb.append(",");
                }
              }
              String str = sb.toString();
              List<String> numbers = new ArrayList<String>(Arrays.asList(str.split(",")));
              numbers.removeAll(Arrays.asList(""));
              for (int i = 0; i < numbers.size(); i++) {
                integer.add(Integer.parseInt(numbers.get(i)));
              }
              return integer;
            };

    Function<List<Integer>, Integer> sum =
            s -> {
              int result = 0;
                for (int i = 0; i < s.size(); i++) {
                    result += s.get(i);
                }
                return result;
            }; //sumujemy liczby

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}


