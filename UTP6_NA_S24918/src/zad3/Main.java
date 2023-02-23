/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad3;


import javax.swing.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
  public static void main(String[] args) {
    // utworzenie obiektu JFrame
    JFrame frame = new JFrame("Zadania");

    // utworzenie obiektu JList i dodanie do niego elementów
    DefaultListModel<Future> listModel = new DefaultListModel<>();
    JList<Future> list = new JList<>(listModel);
    frame.add(list);

    // utworzenie przycisków
    JButton checkButton = new JButton("Sprawdź stan");
    JButton cancelButton = new JButton("Anuluj");
    JButton showResultsButton = new JButton("Pokaż wyniki");

    // obsługa przycisków
    checkButton.addActionListener(e -> {
      // pobranie zaznaczonego zadania z listy
      Future selectedTask = list.getSelectedValue();
      if (selectedTask != null) {
        // sprawdzenie stanu zadania
        if (selectedTask.isDone()) {
          JOptionPane.showMessageDialog(frame, "Zadanie zostało ukończone");
        } else if (selectedTask.isCancelled()) {
          JOptionPane.showMessageDialog(frame, "Zadanie zostało anulowane");
        } else {
          JOptionPane.showMessageDialog(frame, "Zadanie jest w trakcie wykonywania");
        }
      }
    });
    cancelButton.addActionListener(e -> {
      // pobranie zaznaczonego zadania z listy
      Future selectedTask = list.getSelectedValue();
      if (selectedTask != null) {
        // anulowanie zadania
        selectedTask.cancel(true);
      }
    });
    showResultsButton.addActionListener(e -> {
      // pobranie zaznaczonego zadania z listy
      Future selectedTask = list.getSelectedValue();
      if (selectedTask != null) {
        try {
          // pobranie wyniku zadania
          Object result = selectedTask.get();
          JOptionPane.showMessageDialog(frame, "Wynik zadania: " + result);
        } catch (InterruptedException | ExecutionException ex) {
          JOptionPane.showMessageDialog(frame, "Wystąpił błąd podczas pobierania wyniku");
        }
      }
    });



    // dodanie przycisków do okna
    frame.add(checkButton);
    frame.add(cancelButton);
    frame.add(showResultsButton);

    // wyświetlenie okna
    frame.pack();
    frame.setVisible(true);
    }
}
