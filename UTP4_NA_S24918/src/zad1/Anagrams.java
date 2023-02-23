/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams {

    public List<ArrayList<String>> listOfAnagrams = new ArrayList<ArrayList<String>>();
    public List<String> listOfWords = new ArrayList<String>();

    public Anagrams(String f) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(f)); // read file

        while (scanner.hasNext()) {            // while file has next line
            listOfWords.add(scanner.next());   // add next line to list
        }
        scanner.close();
        testAnagram();
        }

        public void testAnagram() {
            while (!(listOfWords.isEmpty())) {                        // while list of words is not empty
                List<String> array = new ArrayList<String>();
                String str = listOfWords.remove(0);             // remove first word from list
                array.add(str);                                       // add this word to new list

                for (int i = 0; i < listOfWords.size(); i++) {
                    String word = listOfWords.get(i);
                    char tab1[] = str.toCharArray();
                    char tab2[] = word.toCharArray();
                    Arrays.sort(tab1);
                    Arrays.sort(tab2);

                    if (Arrays.equals(tab1, tab2)) {
                        listOfWords.remove(i);
                        array.add(word);
                        i--; // decrement i
                    }
                }
                listOfAnagrams.add(new ArrayList<String>(array));
            }
        }

    public List<ArrayList<String>> getSortedByAnQty() {
        listOfAnagrams.sort(new Comparator<List<String>>() {                     // sort list of anagrams by size
            public int compare(List<String> arr1, List<String> arr2) {           // compare method
                if (arr2.size() != arr1.size()) {                                // if size of lists are not equal
                    return arr2.size() - arr1.size();                            // return difference
                } else if (arr1.get(0).compareTo(arr2.get(0)) > 0) {             // if first words are not equal
                    return 1;                                                    // return 1
                }
                return -1;
            }
        });
        return listOfAnagrams;
    }

    public String getAnagramsFor(String f) {                                    // method to find anagrams for word
        for (int i = 0; i < listOfAnagrams.size(); i++) {                       // for each list in list of anagrams
            ArrayList<String> tmp = listOfAnagrams.get(i);                      // get list
            String str = tmp.get(0);                                            // get first word from list
            if (str.equals(f)) {                                                // if first word is equal to word
                tmp.remove(0);                                            // remove first word
                return f + ": " + tmp;                                          // return word and list of anagrams
            }
        }
        return null;
    }
}  
