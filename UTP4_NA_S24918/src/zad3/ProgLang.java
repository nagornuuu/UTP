package zad3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {
    private ArrayList<String> programmers;  // list of programmers

    public ProgLang(String f) throws IOException {
        this.programmers = new ArrayList<>();                            // create empty list of programmers

            BufferedReader br = new BufferedReader(new FileReader(f));   // open file
            String line;                                                 // line from file

            while ((line = br.readLine()) != null) {                     // while not end of file
                this.programmers.add(line);                              // add line to list of programmers
            }

            br.close();
    }

    public void addOrCreate(LinkedHashMap<String, LinkedHashSet<String>> map, String key, String value) {
         LinkedHashSet<String> set = map.get(key);   // get set of values for key
            if (set == null) {                       // if set is empty
                set = new LinkedHashSet<>();         // create new set
                map.put(key, set);                   // add set to map
            }
            set.add(value);                          // add value to set
    }

    public LinkedHashMap<String, LinkedHashSet<String>> getLangsMap() {             // get map of languages
        LinkedHashMap<String, LinkedHashSet<String>> map = new LinkedHashMap<>();   // create empty map

        for (String programmer : this.programmers) {                 // for each programmer
            String[] split = programmer.split("\t");      // split line by tabulation
            String lang = "";

            for (int i = 0; i < split.length; i++) {            // for each word in line
                if (i == 0) {                                   // if first word in line (programmer name)
                    lang = split[i];                            // set lang to programmer name
                    continue;                                   // continue to next word
                }
                this.addOrCreate(map, lang, split[i]);            // add programmer to map of languages
            }
        }
        return map;
    }

    public LinkedHashMap<String, LinkedHashSet<String>> getProgsMap() {             // get map of programmers
        LinkedHashMap<String, LinkedHashSet<String>> map = new LinkedHashMap<>();   // create empty map

        for (String programmer : this.programmers) {                                     // for each programmer
            String[] split = programmer.split("\t");                          // split line by tabulation
            String prog = "";

            for (int i = 0; i < split.length; i++) {
                if (i == 0) {                                                       // if first word in line (programmer name)
                    prog = split[i];                                                // set prog to programmer name
                    continue;                                                       // continue to next word
                }
                this.addOrCreate(map, split[i], prog);                                // add language to map of programmers
            }
        }
        return map;
    }

    public Map<String, LinkedHashSet<String>> getLangsMapSortedByNumOfProgs() {    // get map of languages sorted by number of programmers
        return this.sorted(                                                        // sort map
                this.getLangsMap(),                                                // get map of languages
                (arg1, arg2) -> {                                                  // comparator
                    int result = arg2.getValue().size() - arg1.getValue().size();  // compare number of programmers
                    if (result == 0) {                                             // if number of programmers is equal for both languages
                        return arg1.getKey().compareTo(arg2.getKey());             // compare languages
                    }
                    return result;                                                 // return result
                }
        );
    }

    public Map<String, LinkedHashSet<String>> getProgsMapSortedByNumOfLangs() {     // get map of programmers sorted by number of languages
        return this.sorted(                                                         // sort map
                this.getProgsMap(),                                                 // get map of programmers
                (arg1, arg2) -> {                                                   // comparator
                    int result = arg2.getValue().size() - arg1.getValue().size();   // compare number of languages
                    if (result == 0) {                                              // if number of languages is equal for both programmers
                        return arg1.getKey().compareTo(arg2.getKey());              // compare programmers
                    }
                    return result;                                                  // return result
                }
        );
    }



    public Map<String, LinkedHashSet<String>> getProgsMapForNumOfLangsGreaterThan(int n) {  // get map of programmers for number of languages greater than n
        return this.filtered(                                                               // filter map
                this.getProgsMap(),                                                         // get map of programmers
                e -> e.getValue().size() > n                                                // predicate (number of languages greater than n)
        );
    }

    public <T, R> Map<T, R> filtered(Map<T, R> map, Predicate<Map.Entry<T,R>> pred) {   // filter map
        return map                                                                      // return filtered map
                .entrySet()                                                             // get set of entries from map
                .stream()                                                               // create stream from set
                .filter(pred)                                                           // filter entries by predicate
                .collect(Collectors.toMap(                                              // collect entries to map (key, value)
                        Map.Entry::getKey,                                              // key
                        Map.Entry::getValue,                                            // value
                        (arg1, arg2) -> arg1,                                           // if key is equal, use first value
                        LinkedHashMap::new                                              // create new linked hash map
                ));
    }
    public <T, R> Map<T, R> sorted(Map<T, R> map, Comparator<Map.Entry<T, R>> comp) {   // sort map
        return map                                                                      // return sorted map
                .entrySet()                                                             // get set of entries from map
                .stream()                                                               // create stream from set
                .sorted(comp)                                                           // sort entries by comparator
                .collect(Collectors.toMap(                                              // collect entries to map (key, value)
                        Map.Entry::getKey,                                              // key
                        Map.Entry::getValue,                                            // value
                        (arg1, arg2) -> arg1,                                           // if key is equal, use first value
                        LinkedHashMap::new                                              // create new linked hash map
                ));

    }

}
