package dev.normanpendzich.personen;
import java.sql.*;

public class Person implements PersonInterface {
    private String firstname;
    private String lastname;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public void createEntryInDatabase(Connection connection) throws SQLException {
        String sql = "INSERT INTO mytable (firstname, lastname) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.executeUpdate();
            System.out.println("Eintrag für " + firstname + " " + lastname + " erfolgreich erstellt.");
        }
    }
}