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

import java.io.Serializable;

public class Shipper extends Transport implements Serializable {
    protected String confirmationNumber;

    public Shipper() {
    }

    public Shipper(String companyName, String address, String phoneNumber, String reeferTemperature, String deliveryAddress,
                   String pickupDateTime, String approximateWeight, String confirmationNumber, String extraInfo) {
        super(companyName, address, phoneNumber, reeferTemperature, deliveryAddress, pickupDateTime,
                approximateWeight, extraInfo);
        this.confirmationNumber = confirmationNumber;
    }

    // getters
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    // setters
    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    // information window
    public void openShipperWindow() {
        // stage
        Stage shipperStage = new Stage();
        shipperStage.setTitle("Shipper Details");

        // header
        Label shipperHeader = new Label("Shipper Details");
        shipperHeader.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");
        shipperHeader.setUnderline(true);

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
                {"Confirmation Number:", this.confirmationNumber}
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
        VBox shipperBox = new VBox(15, shipperHeader, gridPane);
        shipperBox.setPadding(new Insets(20));
        shipperBox.setAlignment(Pos.TOP_CENTER);
        shipperBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 10px; -fx-padding: 15px; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 2, 2);");

        // scroll pane
        ScrollPane scrollPane = new ScrollPane(shipperBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setContent(shipperBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefViewportHeight(100);

        BorderPane bp = new BorderPane();
        bp.setCenter(scrollPane);

        // scene
        Scene scene = new Scene(bp, 350, 400);
        shipperStage.setScene(scene);

        // minimum size, allow expanding
        shipperStage.setMinWidth(400);
        shipperStage.setMinHeight(400);
        shipperStage.setResizable(true);

        shipperStage.setHeight(bp.getHeight());

        shipperStage.show();
    }
}
