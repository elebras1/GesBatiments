package org.elebras.gesbatiments.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ListeBatimentsController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}