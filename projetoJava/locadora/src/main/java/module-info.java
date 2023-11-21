module com.locadora.locadora {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.locadora.locadora to javafx.fxml;
    exports com.locadora.locadora;
    exports com.locadora.locadora.controladoresAlterações;
    requires jbcrypt;
}
