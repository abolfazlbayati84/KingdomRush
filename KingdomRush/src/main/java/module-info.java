module org.example.kingdomrush {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens org.example.kingdomrush to javafx.fxml;
    exports org.example.kingdomrush;
}