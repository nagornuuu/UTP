package zad1;

import java.util.ArrayList;

public class Letters extends Thread {

    ArrayList<Thread> threads = new ArrayList<>(); // lista wątków

    public Letters(String letters) {
        for(int i = 0; i < letters.length(); i++) {             // tworzenie wątków
            String letter = letters.substring(i, i + 1);        // pobieranie pojedynczej litery
            Runnable thread = new Thread(() -> {
                while (true) {
                    try {
                        System.out.print(letter);
                        Thread.sleep(1000);                 // wątek czeka 1 sekundę
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            threads.add(new Thread(thread, "Thread " + letter)); // dodawanie wątku do listy
        }
    }
    public ArrayList<Thread> getThreads() { // zwraca listę wątków
        return threads;
    }
}


