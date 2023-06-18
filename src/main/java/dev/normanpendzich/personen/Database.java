package dev.normanpendzich.personen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public void createTableIfNotExists(Connection connection) throws SQLException {
        if (!tableExists(connection, "mytable")) {
            String sql = "CREATE TABLE mytable (id INT PRIMARY KEY AUTO_INCREMENT, firstname VARCHAR(255), lastname VARCHAR(255))";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
                System.out.println("Tabelle mytable erfolgreich erstellt.");
            }
        } else {
            System.out.println("Tabelle mytable existiert bereits.");
        }
    }

    public boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();
        try (ResultSet resultSet = metadata.getTables(null, null, tableName, null)) {
            return resultSet.next();
        }
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Razer2825!";
        return DriverManager.getConnection(url, username, password);
    }

    public void createEntry(Connection connection, String firstName, String lastName) throws SQLException {
        String sql = "INSERT INTO mytable (firstname, lastname) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.executeUpdate();
        }
    }
    public void readEntry(Connection connection, int id) throws SQLException {
        String sql = "SELECT firstname, lastname FROM mytable WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    System.out.println("Vorname: " + firstName + ", Nachname: " + lastName);
                } else {
                    System.out.println("Kein Eintrag mit ID " + id + " gefunden.");
                }
            }
        }
    }


    public List<String> readEntries(Connection connection) throws SQLException {
        List<String> entries = new ArrayList<>();
        String sql = "SELECT * FROM mytable";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String entry = "ID: " + id + ", Name: " + firstName + " " + lastName;
                entries.add(entry);
            }
        }
        return entries;
    }


    public void updateEntry(Connection connection, int id, String firstName, String lastName) throws SQLException {
        String sql = "UPDATE mytable SET firstname = ?, lastname = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Eintrag mit ID " + id + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Kein Eintrag mit ID " + id + " gefunden.");
            }
        }
    }

    public void deleteEntry(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM mytable WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Eintrag mit ID " + id + " erfolgreich gelöscht.");
            } else {
                System.out.println("Kein Eintrag mit ID " + id + " gefunden.");
            }
        }
    }
}
