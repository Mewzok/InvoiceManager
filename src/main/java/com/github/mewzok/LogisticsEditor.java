package com.github.mewzok;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogisticsEditor extends Stage {

    private WindowCloseCallback callback;
    private BorderPane borderPane = new BorderPane();
    private VBox selectionScreen;
    private ComboBox<String> logisticsTypeComboBox = new ComboBox<>();
    private ListView<String> logisticsListView = new ListView<>();
    private Button editButton = new Button("Edit");
    private Button deleteButton = new Button("Delete");

    private ColumnConstraints labelColumn = new ColumnConstraints(180);
    private ColumnConstraints textFieldColumn = new ColumnConstraints(280);

    public LogisticsEditor(WindowCloseCallback callback) {
        this.callback = callback;
        setMinWidth(500);
        setMinHeight(300);

        // create selection screen
        selectionScreen();

        // scene
        Scene scene = new Scene(borderPane);
        setScene(scene);
        show();
    }

    private void selectionScreen() {
        // selection layout
        selectionScreen = new VBox(10);
        selectionScreen.setPadding(new Insets(10));
        selectionScreen.setAlignment(Pos.CENTER);

        // logistic selection combobox
        logisticsTypeComboBox.getItems().addAll("Brokers", "Shippers", "Receivers");
        logisticsTypeComboBox.setValue("Brokers");

        // listview to display all saved selected logistics
        updateLogisticsList("Brokers"); // default

        // event handlers
        logisticsTypeComboBox.setOnAction(e -> {
            updateLogisticsList(logisticsTypeComboBox.getValue());
        });

        editButton.setOnAction(this::handleEdit);
        deleteButton.setOnAction(this::handleDelete);

        // add everything to layout
        selectionScreen.getChildren().addAll(logisticsTypeComboBox, logisticsListView, editButton, deleteButton);

        borderPane.setCenter(selectionScreen);
    }

    // handle editor setup
    private void handleEditor(ActionEvent event) {
        String selection = logisticsListView.getSelectionModel().getSelectedItem();

        if(selection != null) {
            Object logistic = getSelectedLogistic(selection);
            if(logistic != null) {
                editorScreen(logistic);
            }
        }
    }

    // retrieve and assign selected logistic
    private Object getSelectedLogistic(String selectedLogistic) {
        switch (logisticsTypeComboBox.getValue()) {
            case "Brokers":
                return InvoiceStorage.getBroker(selectedLogistic);
            case "Shippers":
                return InvoiceStorage.getShipper(selectedLogistic);
            case "Receivers":
                return InvoiceStorage.getReceiver(selectedLogistic);
            default:
                return null;
        }
    }

    // editor screen
    private void editorScreen(Object logistic) {
        VBox editorVBox = new VBox(10);
        editorVBox.setPadding(new Insets(10));

        if(logistic instanceof Broker) {
            editorVBox.getChildren().addAll(new Label("Edit Broker"), createBrokerEditor((Broker) logistic));
        } else if(logistic instanceof Shipper) {
            editorVBox.getChildren().addAll(new Label("Edit Shipper"), createShipperEditor((Shipper) logistic));
        } else if(logistic instanceof Receiver) {
            editorVBox.getChildren().addAll(new Label("Edit Receiver"), createReceiverEditor((Receiver) logistic));
        }

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> borderPane.setCenter(selectionScreen));
        editorVBox.getChildren().add(cancelButton);

        borderPane.setCenter(editorVBox);
    }

    // create layouts
    private GridPane createBrokerEditor(Broker broker) {
        // design layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // set column constraints
        gridPane.getColumnConstraints().addAll(labelColumn, textFieldColumn);

        // create labels and textfields
        Label brokerCompanyNameLabel = new Label("Broker Company Name: ");
        Label brokerAddressLabel = new Label("Broker Address: ");
        Label brokerPhoneNumberLabel = new Label("Broker Phone Number: ");
        Label brokerReeferTemperatureLabel = new Label("Broker Reefer Temperature: ");
        Label brokerEmailLabel = new Label("Broker Email: ");
        Label brokerNameLabel = new Label("Broker Name: ");
        Label brokerPONumberLabel = new Label("Broker PO#: ");
        Label brokerExtraInfoLabel = new Label("Extra Broker Information: ");
        TextField brokerCompanyNameTF = new TextField(broker.getCompanyName());
        TextField brokerAddressTF = new TextField(broker.getAddress());
        TextField brokerPhoneNumberTF = new TextField(broker.getPhoneNumber());
        TextField brokerReeferTemperatureTF = new TextField(broker.getReeferTemperature());
        TextField brokerEmailTF = new TextField(broker.getEmail());
        TextField brokerNameTF = new TextField(broker.getBrokerName());
        TextField brokerPONumberTF = new TextField(broker.getPoNumber());
        TextArea brokerExtraInfoTA = new TextArea(broker.getExtraInfo());

        // handle save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            broker.setCompanyName(brokerCompanyNameTF.getText());
            broker.setAddress(brokerAddressTF.getText());
            broker.setPhoneNumber(brokerPhoneNumberTF.getText());
            broker.setReeferTemperature(brokerReeferTemperatureTF.getText());
            broker.setEmail(brokerEmailTF.getText());
            broker.setBrokerName(brokerNameTF.getText());
            broker.setPoNumber(brokerPONumberTF.getText());
            broker.setExtraInfo(brokerExtraInfoTA.getText());

            // save broker
            InvoiceStorage.saveLogistic(broker);
            updateLogisticsList(logisticsTypeComboBox.getValue()); // update list
            borderPane.setCenter(selectionScreen);
        });

        // populate grid
        gridPane.add(brokerCompanyNameLabel, 0, 0);
        gridPane.add(brokerCompanyNameTF, 1, 0);
        gridPane.add(brokerAddressLabel, 0 ,1);
        gridPane.add(brokerAddressTF, 1, 1);
        gridPane.add(brokerPhoneNumberLabel, 0, 2);
        gridPane.add(brokerPhoneNumberTF, 1, 2);
        gridPane.add(brokerReeferTemperatureLabel, 0, 3);
        gridPane.add(brokerReeferTemperatureTF, 1, 3);
        gridPane.add(brokerEmailLabel, 0, 4);
        gridPane.add(brokerEmailTF, 1, 4);
        gridPane.add(brokerNameLabel, 0, 5);
        gridPane.add(brokerNameTF, 1, 5);
        gridPane.add(brokerPONumberLabel, 0, 6);
        gridPane.add(brokerPONumberTF, 1, 6);
        gridPane.add(brokerExtraInfoLabel, 0, 7);
        gridPane.add(brokerExtraInfoTA, 1, 7);
        gridPane.add(saveButton, 0, 8, 2, 1);

        return gridPane;
    }

    private GridPane createShipperEditor(Shipper shipper) {
        // design layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // set column constraints
        gridPane.getColumnConstraints().addAll(labelColumn, textFieldColumn);

        // create labels and textfields
        Label shipperCompanyNameLabel = new Label("Shipper Company Name: ");
        Label shipperAddressLabel = new Label("Shipper Address: ");
        Label shipperPhoneNumberLabel = new Label("Shipper Phone Number: ");
        Label shipperReeferTemperatureLabel = new Label("Shipper Reefer Temperature: ");
        Label shipperDeliveryAddressLabel = new Label("Shipper Delivery Address: ");
        Label shipperPickupDateTimeLabel = new Label("Shipper Pickup Date: ");
        Label shipperApproxWeightLabel = new Label("Shipper Approximate Weight: ");
        Label shipperConfirmationNumberLabel = new Label("Shipper Confirmation Number: ");
        Label shipperExtraInfoLabel = new Label("Extra Shipper Information: ");
        TextField shipperCompanyNameTF = new TextField(shipper.getCompanyName());
        TextField shipperAddressTF = new TextField(shipper.getAddress());
        TextField shipperPhoneNumberTF = new TextField(shipper.getPhoneNumber());
        TextField shipperReeferTemperatureTF = new TextField(shipper.getReeferTemperature());
        TextField shipperDeliveryAddressTF = new TextField(shipper.getDeliveryAddress());
        TextField shipperPickupDateTimeTF = new TextField(shipper.getPickupDateTime());
        TextField shipperApproxWeightTF = new TextField(shipper.getApproximateWeight());
        TextField shipperConfirmationNumberTF = new TextField(shipper.getConfirmationNumber());
        TextArea shipperExtraInfoTA = new TextArea(shipper.getExtraInfo());

        // handle save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            shipper.setCompanyName(shipperCompanyNameTF.getText());
            shipper.setAddress(shipperAddressTF.getText());
            shipper.setPhoneNumber(shipperPhoneNumberTF.getText());
            shipper.setReeferTemperature(shipperReeferTemperatureTF.getText());
            shipper.setDeliveryAddress(shipperDeliveryAddressTF.getText());
            shipper.setPickupDateTime(shipperPickupDateTimeTF.getText());
            shipper.setApproximateWeight(shipperApproxWeightTF.getText());
            shipper.setConfirmationNumber(shipperConfirmationNumberTF.getText());
            shipper.setExtraInfo(shipperExtraInfoTA.getText());

            // save shipper
            InvoiceStorage.saveLogistic(shipper);
            updateLogisticsList(logisticsTypeComboBox.getValue()); // update list
            borderPane.setCenter(selectionScreen);
        });

        // populate grid
        gridPane.add(shipperCompanyNameLabel, 0, 0);
        gridPane.add(shipperCompanyNameTF, 1, 0);
        gridPane.add(shipperAddressLabel, 0, 1);
        gridPane.add(shipperAddressTF, 1, 1);
        gridPane.add(shipperPhoneNumberLabel, 0, 2);
        gridPane.add(shipperPhoneNumberTF, 1, 2);
        gridPane.add(shipperReeferTemperatureLabel, 0, 3);
        gridPane.add(shipperReeferTemperatureTF, 1, 3);
        gridPane.add(shipperDeliveryAddressLabel, 0, 4);
        gridPane.add(shipperDeliveryAddressTF, 1, 4);
        gridPane.add(shipperPickupDateTimeLabel, 0, 5);
        gridPane.add(shipperPickupDateTimeTF, 1, 5);
        gridPane.add(shipperApproxWeightLabel, 0, 6);
        gridPane.add(shipperApproxWeightTF, 1, 6);
        gridPane.add(shipperConfirmationNumberLabel, 0, 7);
        gridPane.add(shipperConfirmationNumberTF, 1, 7);
        gridPane.add(shipperExtraInfoLabel, 0, 8);
        gridPane.add(shipperExtraInfoTA, 1, 8);
        gridPane.add(saveButton, 0, 9, 2, 1);

        return gridPane;
    }

    private GridPane createReceiverEditor(Receiver receiver) {
        // design layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // set column constraints
        gridPane.getColumnConstraints().addAll(labelColumn, textFieldColumn);

        // create labels and textfields
        Label receiverCompanyNameLabel = new Label("Receiver Company Name: ");
        Label receiverAddressLabel = new Label("Receiver Address: ");
        Label receiverPhoneNumberLabel = new Label("Receiver Phone Number: ");
        Label receiverReeferTemperatureLabel = new Label("Receiver Reefer Temperature: ");
        Label receiverDeliveryAddressLabel = new Label("Receiver Delivery Address: ");
        Label receiverPickupDateTimeLabel = new Label("Receiver Pickup Date: ");
        Label receiverApproxWeightLabel = new Label("Receiver Approximate Weight: ");
        Label receiverPickupNumberLabel = new Label("Receiver Pickup Number: ");
        Label receiverExtraInfoLabel = new Label("Extra Receiver Information: ");
        TextField receiverCompanyNameTF = new TextField(receiver.getCompanyName());
        TextField receiverAddressTF = new TextField(receiver.getAddress());
        TextField receiverPhoneNumberTF = new TextField(receiver.getPhoneNumber());
        TextField receiverReeferTemperatureTF = new TextField(receiver.getReeferTemperature());
        TextField receiverDeliveryAddressTF = new TextField(receiver.getDeliveryAddress());
        TextField receiverPickupDateTimeTF = new TextField(receiver.getPickupDateTime());
        TextField receiverApproxWeightTF = new TextField(receiver.getApproximateWeight());
        TextField receiverPickupNumberTF = new TextField(receiver.getPickupNumber());
        TextArea receiverExtraInfoTA = new TextArea(receiver.getExtraInfo());

        // handle save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            receiver.setCompanyName(receiverCompanyNameTF.getText());
            receiver.setAddress(receiverAddressTF.getText());
            receiver.setPhoneNumber(receiverPhoneNumberTF.getText());
            receiver.setReeferTemperature(receiverReeferTemperatureTF.getText());
            receiver.setDeliveryAddress(receiverDeliveryAddressTF.getText());
            receiver.setPickupDateTime(receiverPickupDateTimeTF.getText());
            receiver.setApproximateWeight(receiverApproxWeightTF.getText());
            receiver.setPickupNumber(receiverPickupNumberTF.getText());
            receiver.setExtraInfo(receiverExtraInfoTA.getText());

            // save receiver
            InvoiceStorage.saveLogistic(receiver);
            updateLogisticsList(logisticsTypeComboBox.getValue()); // update list
            borderPane.setCenter(selectionScreen);
        });

        // populate grid
        gridPane.add(receiverCompanyNameLabel, 0, 0);
        gridPane.add(receiverCompanyNameTF, 1, 0);
        gridPane.add(receiverAddressLabel, 0, 1);
        gridPane.add(receiverAddressTF, 1, 1);
        gridPane.add(receiverPhoneNumberLabel, 0, 2);
        gridPane.add(receiverPhoneNumberTF, 1, 2);
        gridPane.add(receiverReeferTemperatureLabel, 0, 3);
        gridPane.add(receiverReeferTemperatureTF, 1, 3);
        gridPane.add(receiverDeliveryAddressLabel, 0, 4);
        gridPane.add(receiverDeliveryAddressTF, 1, 4);
        gridPane.add(receiverPickupDateTimeLabel, 0, 5);
        gridPane.add(receiverPickupDateTimeTF, 1, 5);
        gridPane.add(receiverApproxWeightLabel, 0, 6);
        gridPane.add(receiverApproxWeightTF, 1, 6);
        gridPane.add(receiverPickupNumberLabel, 0, 7);
        gridPane.add(receiverPickupNumberTF, 1, 7);
        gridPane.add(receiverExtraInfoLabel, 0, 8);
        gridPane.add(receiverExtraInfoTA, 1, 8);
        gridPane.add(saveButton, 0, 9, 2, 1);

        return gridPane;
    }

    // update listview based on selected logistic
    private void updateLogisticsList(String selection) {
        logisticsListView.getItems().clear();

        if (selection.equals("Brokers")) {
            logisticsListView.getItems().addAll(InvoiceStorage.getSavedBrokerNames());
        } else if (selection.equals("Shippers")) {
            logisticsListView.getItems().addAll(InvoiceStorage.getSavedShipperNames());
        } else if (selection.equals("Receivers")) {
            logisticsListView.getItems().addAll(InvoiceStorage.getSavedReceiverNames());
        }
    }

    // edit button handle method
    private void handleEdit(ActionEvent event) {
        String selection = logisticsListView.getSelectionModel().getSelectedItem();

        if (selection != null) {
            Object logistic = getSelectedLogistic(selection);
            if(logistic != null) {
                editorScreen(logistic);
            }
        }
    }

    private void handleDelete(ActionEvent event) {
        String selection = logisticsListView.getSelectionModel().getSelectedItem();

        if (selection != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Logistic");
            alert.setHeaderText("Are you sure you want to delete this " +
                    logisticsTypeComboBox.getValue().substring(0, logisticsTypeComboBox.getValue().length() - 1) + "?");
            alert.setContentText("This action cannot be undone.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // delete logistic
                    if(logisticsTypeComboBox.getValue().equals("Brokers")) {
                        InvoiceStorage.deleteBroker(selection);
                    } else if(logisticsTypeComboBox.getValue().equals("Shippers")) {
                        InvoiceStorage.deleteShipper(selection);
                    } else if(logisticsTypeComboBox.getValue().equals("Receivers")) {
                        InvoiceStorage.deleteReceiver(selection);
                    }
                    updateLogisticsList(logisticsTypeComboBox.getValue()); // update list
                }
            });
        }
    }
}
