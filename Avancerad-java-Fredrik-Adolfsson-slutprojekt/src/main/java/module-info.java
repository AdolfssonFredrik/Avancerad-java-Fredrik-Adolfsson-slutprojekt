module org.example.avanceradjavafredrikadolfssonslutprojekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.avanceradjavafredrikadolfssonslutprojekt to javafx.fxml;
    exports org.example.avanceradjavafredrikadolfssonslutprojekt;
}