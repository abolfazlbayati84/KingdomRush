module org.example.kingdomrush {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.kingdomrush to javafx.fxml;
    exports org.example.kingdomrush;
}