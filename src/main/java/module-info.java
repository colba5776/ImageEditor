module com.mycompany.imageeditornet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.mycompany.imageeditornet to javafx.fxml;
    exports com.mycompany.imageeditornet;
}
