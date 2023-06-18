package dev.normanpendzich.personen;

import java.sql.Connection;
import java.sql.SQLException;

public interface PersonInterface {
    void createEntryInDatabase(Connection connection) throws SQLException;
}
