module org.elebras.gesbatiments {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    opens org.elebras.gesbatiments.vue to javafx.fxml;
    exports org.elebras.gesbatiments;
    exports org.elebras.gesbatiments.vue;
}