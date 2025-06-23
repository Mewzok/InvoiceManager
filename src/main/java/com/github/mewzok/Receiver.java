package com.github.mewzok;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Receiver extends Transport  {
    protected String pickupNumber;

    public Receiver() {
    }

    public Receiver(String companyName, String address, String phoneNumber, String reeferTemperature, String deliveryAddress,
                    String pickupDateTime, String approximateWeight, String pickupNumber, String extraInfo) {
        super(companyName, address, phoneNumber, reeferTemperature, deliveryAddress, pickupDateTime,
                approximateWeight, extraInfo);
        this.pickupNumber = pickupNumber;
    }

    // getters
    public String getPickupNumber() {
        return pickupNumber;
    }

    // setters
    public void setPickupNumber(String pickupNumber) {
        this.pickupNumber = pickupNumber;
    }

    // information window
    public void openReceiverWindow() {
        // stage
        Stage receiverStage = new Stage();
        receiverStage.setTitle("Receiver Details");

        // header
        Label receiverHeader = new Label("Receiver Details");
        receiverHeader.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");
        receiverHeader.setUnderline(true);

        // grid pane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER_LEFT);

        // labels
        String[][] details = {
                {"Company Name:", this.companyName},
                {"Address:", this.address},
                {"Phone Number:", this.phoneNumber},
                {"Reefer Temperature:", this.reeferTemperature},
                {"Delivery Address:", this.deliveryAddress},
                {"Pickup Date:", this.pickupDateTime},
                {"Approximate Weight:", this.approximateWeight},
                {"Pickup Number:", this.pickupNumber}
        };

        for (int i = 0; i < details.length; i++) {
            Label fieldLabel = new Label(details[i][0]);
            fieldLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #444;");

            Label valueLabel = new Label(details[i][1]);
            valueLabel.setStyle("-fx-background-color: #f8f8f8; -fx-padding: 5px; -fx-border-color: #ddd; -fx-border-radius: 3px;");

            gridPane.add(fieldLabel, 0, i);
            gridPane.add(valueLabel, 1, i);
        }

        if (this.extraInfo != null && !this.extraInfo.isEmpty()) {
            Label extraInfoLabel = new Label("Additional Info:");
            extraInfoLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #444;");

            Label extraInfoText = new Label(this.extraInfo);
            extraInfoText.setWrapText(true);
            extraInfoText.setStyle("-fx-background-color: #f8f8f8; -fx-padding: 5px; -fx-border-color: #ddd; -fx-border-radius: 3px;");

            gridPane.add(extraInfoLabel, 0, details.length);
            gridPane.add(extraInfoText, 1, details.length);
        }

        // vbox
        VBox receiverBox = new VBox(15, receiverHeader, gridPane);
        receiverBox.setPadding(new Insets(20));
        receiverBox.setAlignment(Pos.TOP_CENTER);
        receiverBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 10px; -fx-padding: 15px; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 2, 2);");

        // scroll pane
        ScrollPane scrollPane = new ScrollPane(receiverBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setContent(receiverBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefViewportHeight(100);

        BorderPane bp = new BorderPane();
        bp.setCenter(scrollPane);

        // scene
        Scene scene = new Scene(bp, 350, 400);
        receiverStage.setScene(scene);

        // minimum size, allow expanding
        receiverStage.setMinWidth(400);
        receiverStage.setMinHeight(400);
        receiverStage.setResizable(true);

        receiverStage.setHeight(bp.getHeight());

        receiverStage.show();
    }
}
