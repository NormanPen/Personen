package dev.normanpendzich.personen;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    public Label textLabel;

    @FXML
    private TextField textField;

    private List<String> allEntries = new ArrayList<>();

    public void initialize() {
        // Initialisiere die UI mit allen Einträgen
        StringBuilder allEntriesText = new StringBuilder();
        for (String entry : allEntries) {
            allEntriesText.append(entry).append("\n");
        }
        textLabel.setText(allEntriesText.toString());

        // Füge einen Listener zum Textfeld hinzu, um die Einträge zu filtern
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String input = newValue.trim();
            filterEntries(input);
        });
    }

    private void filterEntries(String input) {
        StringBuilder filteredEntries = new StringBuilder();
        for (String entry : allEntries) {
            if (entry.contains(input)) {
                filteredEntries.append(entry).append("\n");
            }
        }
        textLabel.setText(filteredEntries.toString());
    }

    public void setEntries(List<String> entries) {
        allEntries = entries;
    }
}
