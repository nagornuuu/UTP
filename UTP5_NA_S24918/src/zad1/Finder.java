/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Finder {

    private ArrayList<String> lines;

    // reading file
    public Finder(String path) throws IOException {
            this.lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                this.lines.add(line);
            }
            br.close();
        }

        public int getIfCount() {

            int count = 0;
            for (int i = 0; i < this.lines.toArray().length; i++) { // for each line in file
                if (this.lines.get(i).contains("if")) {             // if line contains "if"
                    count++;                                        // increase counter
                }
            }
            return count;
        }

        public int getStringCount(String str) {

            int count = 0;
            int index = 0;

            for (int i = 0; i < this.lines.toArray().length; i++) {     // for each line in file
                while (index != -1) {                                   // while line contains "str"
                    index = this.lines.get(i).indexOf(str, index);      // find index of "str"
                    if (index != -1) {                                  // if index is not -1
                        count++;                                        // increase counter
                        index += str.length();                          // increase index
                    }
                }
                index = 0;
            }
            return count;
        }


}    
