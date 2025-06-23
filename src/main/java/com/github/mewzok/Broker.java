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

public class Broker extends Logistic implements Serializable {
    protected String email;
    protected String brokerName;
    protected String poNumber;

    public Broker() {
    }

    public Broker(String companyName, String address, String phoneNumber, String reeferTemperature,
                  String email, String brokerName, String poNumber, String extraInfo) {
        super(companyName, address, phoneNumber, reeferTemperature, extraInfo);
        this.email = email;
        this.brokerName = brokerName;
        this.poNumber = poNumber;
    }

    // getters
    public String getEmail() {
        return email;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public String getPoNumber() {
        return poNumber;
    }

    // setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    // information window
    public void openBrokerWindow() {
        // stage
        Stage brokerStage = new Stage();
        brokerStage.setTitle("Broker Details");

        // header
        Label brokerHeader = new Label("Broker Details");
        brokerHeader.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");
        brokerHeader.setUnderline(true);

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
                {"Email:", this.email},
                {"Broker Name:", this.brokerName},
                {"PO Number:", this.poNumber}
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
        VBox brokerBox = new VBox(15, brokerHeader, gridPane);
        brokerBox.setPadding(new Insets(20));
        brokerBox.setAlignment(Pos.TOP_CENTER);
        brokerBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 10px; -fx-padding: 15px; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 2, 2);");

        // scroll pane
        ScrollPane scrollPane = new ScrollPane(brokerBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setContent(brokerBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefViewportHeight(100);

        BorderPane bp = new BorderPane();
        bp.setCenter(scrollPane);

        // scene
        Scene scene = new Scene(bp, 350, 400);
        brokerStage.setScene(scene);

        // minimum size, allow expanding
        brokerStage.setMinWidth(400);
        brokerStage.setMinHeight(400);
        brokerStage.setResizable(true);

        brokerStage.setHeight(bp.getHeight());

        brokerStage.show();
    }
}
