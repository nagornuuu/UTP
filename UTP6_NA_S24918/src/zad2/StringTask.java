package zad2;

public class StringTask {
    private final String txt;
    private String result = "";
    private final int count;
    private TaskState state;

    public StringTask(String txt, int count) {
        state = TaskState.CREATED;
        this.txt = txt;
        this.count = count;
    }

    public TaskState getState() {
        return state;
    }

    public boolean isDone() {
        return state == TaskState.READY || state == TaskState.ABORTED;
    }

    public String getResult() {
        return result;
    }

    public void start() {
        state = TaskState.RUNNING;              // zmiana stanu
        // tworzenie wątku
        // jeśli wątek został przerwany to zakończ
        // dodawanie tekstu do wyniku
        // w odwrotnej kolejności
        Thread t = new Thread(() -> {                  // tworzenie wątku
            for (int i = 0; i < count; i++) {
                if (state == TaskState.ABORTED) return;         // jeśli wątek został przerwany to zakończ
                for (int j = txt.length() - 1; j >= 0; j--) {   // dodawanie tekstu do wyniku
                    result = result + txt.charAt(j);            // w odwrotnej kolejności
                }
            }
            state = TaskState.READY;
        });
        t.start();                             // uruchomienie wątku
    }

    public void abort() {
        state = TaskState.ABORTED;
    }
}