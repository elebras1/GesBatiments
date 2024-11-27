module org.elebras.gesbatiments {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    exports org.elebras.gesbatiments.model to com.fasterxml.jackson.databind;

    opens org.elebras.gesbatiments.vue to javafx.fxml;
    exports org.elebras.gesbatiments;
    exports org.elebras.gesbatiments.vue;
    exports org.elebras.gesbatiments.visiteur;
    exports org.elebras.gesbatiments.facade;
    exports org.elebras.gesbatiments.observer;
    exports org.elebras.gesbatiments.verificateur;
}
