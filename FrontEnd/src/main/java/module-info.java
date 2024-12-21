module org.example.avanceradjavafredrikadolfssonslutprojekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.base;
    requires java.net.http;

    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;

    opens org.example.avanceradjavafredrikadolfssonslutprojekt to javafx.fxml;
    exports org.example.avanceradjavafredrikadolfssonslutprojekt;
    exports org.example.avanceradjavafredrikadolfssonslutprojekt.models to com.fasterxml.jackson.databind;
    opens org.example.avanceradjavafredrikadolfssonslutprojekt.models to com.fasterxml.jackson.databind;
    exports org.example.avanceradjavafredrikadolfssonslutprojekt.controllers;
    opens org.example.avanceradjavafredrikadolfssonslutprojekt.controllers to javafx.fxml;
    exports org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons;
    opens org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons to javafx.fxml;
}