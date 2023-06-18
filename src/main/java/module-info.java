module dev.normanpendzich.personen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens dev.normanpendzich.personen to javafx.fxml;
    exports dev.normanpendzich.personen;
}