package dev.normanpendzich.personen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        initializeData(controller);

        primaryStage.setTitle("Personen");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    private void initializeData(Controller controller) {
        try {
            Database db = new Database();
            Connection connection = db.getConnection();
            List<String> databaseEntries = db.readEntries(connection);
            connection.close();
            controller.setEntries(databaseEntries);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
