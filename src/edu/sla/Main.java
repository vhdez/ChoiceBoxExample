package edu.sla;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    // static variables ONLY needed by RickRoll_click method
    //                   NOT needed by ChoiceBox's changed method
    private static Image     imageAhead;
    private static ImageView imageView;
    private static ComboBox comboBox;

    private static void RickRoll_click() {
        // button clicked, so show Rick looking ahead
        imageView.setImage(imageAhead);
        // turn on the choicebox
        if (comboBox.isDisabled()) {
            comboBox.setDisable(false);
        }
        // Make sure my choicebox's selection matches with image
        comboBox.setValue("Ahead");
    }

    //@Override
    public void start(Stage primaryStage) throws Exception {
        // Load all 5 images
        imageAhead = new Image("RickRolly.png" , 800, 500,false, true);
        Image imageLeft = new Image("RickLeft.jpg", 800, 500, false, true);
        Image imageRight = new Image("RickRight.png", 800 , 500, false ,true);
        Image imageDown = new Image("RickRollDown.png", 800, 500, false, true);
        Image imageUp = new Image("RickRollUp.jpg", 800, 500, false, true);
        Image imageSecret = new Image("RickRollSecret.jpg", 800, 500, false, true);
        // Create imageView where 5 images will be displayed (initially NO image is displayed)
        imageView = new ImageView();

        // Create button that initiates Rick-Rolling
        Button RickRoll = new Button("Click Here");
        // When button is clicked, display RickRoll image, enable choicebox, and make choicebox match image
        RickRoll.setOnAction(e -> RickRoll_click());

        // Create comboBox that lets user choose between 5 types of Rick-Rolling
        comboBox = new ComboBox(FXCollections.observableArrayList("Ahead", "Left", "Right", "Up", "Down"));
        comboBox.setPromptText("Direction");
        comboBox.setEditable(true);
        // Add "changed" method that listens for comboBox selection changing
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldString, String newString) {
                // when comboBox selection changes, match image to selection number
                if (newString.equals("Ahead")) {
                    // comboBox had "Ahead" selected
                    imageView.setImage(imageAhead);
                } else if (newString.equals("Left")) {
                    // comboBox had "Left" selected
                    imageView.setImage(imageLeft);
                } else if (newString.equals("Right")) {
                    // comboBox had "Right" selected
                    imageView.setImage(imageRight);
                } else if (newString.equals("Up")) {
                    // comboBox had "Up" selected
                    imageView.setImage(imageUp);
                } else if (newString.equals("Down")) {
                    // comboBox had "Down" selected
                    imageView.setImage(imageDown);
                } else if (newString.equalsIgnoreCase("Secret")) {
                    // comboBox had "Secret" typed
                    imageView.setImage(imageSecret);
                }
            }
        });

        // initially, comboBox is disabled until RickRolling button is pressed
        comboBox.setDisable(true);

        // Use a BorderPane as the root for scene
        // A border pane has 5 regions to put controls: Top, Bottom, Left, Right, and Center
        BorderPane border = new BorderPane();

        // Create AnchorPane for RickRoll button and comboBox
        //    Use AnchorPane instead of HBox to display comboBox on right edge of window
        AnchorPane topAnchorPane = new AnchorPane();
        topAnchorPane.getChildren().addAll(RickRoll, comboBox);
        // Anchor RickRoll button on left after 5.0 pixels from leftmost edge
        AnchorPane.setLeftAnchor(RickRoll, 5.0);
        // Anchor comboBox on right before 5.0 pixels to rightmost edge
        AnchorPane.setRightAnchor(comboBox, 5.0);
        // Set the Top region of the BorderPane to be this AnchorPane
        border.setTop(topAnchorPane);

        // Set the Center region of the BorderPane to be the imageView
        border.setCenter(imageView);

        // Make a Scene from the BorderPane
        Scene scene = new Scene(border, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ChoiceBox Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
