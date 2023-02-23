/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad1;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");
    System.out.println(test2(src2));

    List<Integer> src4 = Arrays.asList(
            Integer.MAX_VALUE + 1,
            Integer.MIN_VALUE + 2,
            0b10000000_00000000_00000000_00000000 + 4,
            0x80_00_00_00,
            020_000_000_000 + 5);
        //    -1, -2, -4, -5);
    System.out.println(test4(src4));

  }

  public List<Integer> test1(List<Integer> src) {
    Selector<Integer> sel = new Selector<Integer>() {
      @Override
      public boolean select(Integer argument) {
        return argument < 10;                    // wybiera liczby mniejsze od 10
      }
    };

    Mapper<Integer,Integer> map = new Mapper<Integer, Integer>() {
      @Override
      public Integer map(Integer argument) {
        return argument + 10;                   // dodaje 10 do wybranych liczb
      }
    };
    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector<String> sel = new Selector<String>() {
      @Override
      public boolean select(String argument) {
        return argument.length() > 3;           // wybiera napisy dłuższe niż 3 znaki
      }
    };

    Mapper<String, Integer> map = new Mapper<String, Integer>() {
      @Override
      public Integer map(String argument) {
        return argument.length() + 10;           // dodaje 10 do długości wybranych napisów
      }
    };
    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<String> test4(List<Integer> src) {

    Selector<Integer> negativeSelector = new Selector<Integer>() {
      @Override
      public boolean select(Integer argument) {
        return argument < 0;                     // wybiera liczby ujemne
      }
    };


    Mapper<Integer, Long> absoluteMapper = new Mapper<Integer, Long>() {
      @Override
        public Long map(Integer argument) {
          return (long) argument * -1; // zamienia liczby ujemne na dodatnie
      }
    };

    Selector<Long> evenSelector = new Selector<Long>() {
      @Override
      public boolean select(Long argument) {
        return (long) argument % 2 == 0;             // wybiera liczby parzyste
      }
    };

    Mapper<Long, String> reverseMapper = new Mapper<Long, String>() {
      @Override
      public String map(Long argument) {
         return new StringBuilder(argument.toString()).reverse().toString(); // zamienia liczby na napisy
      }
    };
   return ListCreator.collectFrom(ListCreator.collectFrom(src).when(negativeSelector).mapEvery(absoluteMapper)).when(evenSelector).mapEvery(reverseMapper);
  }

  public static void main(String[] args) {
    new Main();
  }
}
