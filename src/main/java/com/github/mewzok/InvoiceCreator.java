package com.github.mewzok;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.YearMonth;
import java.util.*;

public class InvoiceCreator extends Pane {
    // declare variables
    Invoice invoice = new Invoice();
    private WindowCloseCallback callback;
    private final Label rkNumberLabel = new Label("RK#: ");
    private final Label otbNumberLabel = new Label("OTB#: ");
    private final Label brokerCompanyNameLabel = new Label("Broker Company Name: ");
    private final Label brokerAddressLabel = new Label("Broker Address: ");
    private final Label brokerPhoneNumberLabel = new Label("Broker Phone Number: ");
    private final Label brokerReeferTemperatureLabel = new Label("Broker Reefer Temperature: ");
    private final Label brokerEmailLabel = new Label("Broker Email: ");
    private final Label brokerNameLabel = new Label("Broker Name: ");
    private final Label brokerPONumberLabel = new Label("Broker PO#: ");
    private final Label brokerExtraInfoLabel = new Label("Extra Broker Information: ");
    private final Label shipperCompanyNameLabel = new Label("Shipper Company Name: ");
    private final Label shipperAddressLabel = new Label("Shipper Address: ");
    private final Label shipperPhoneNumberLabel = new Label("Shipper Phone Number: ");
    private final Label shipperReeferTemperatureLabel = new Label("Shipper Reefer Temperature: ");
    private final Label shipperDeliveryAddressLabel = new Label("Shipper Delivery Address: ");
    private final Label shipperPickupDateTimeLabel = new Label("Shipper Pickup Date: ");
    private final Label shipperApproxWeightLabel = new Label("Shipper Approximate Weight: ");
    private final Label shipperConfirmationNumberLabel = new Label("Shipper Confirmation Number: ");
    private final Label shipperExtraInfoLabel = new Label("Extra Shipper Information: ");
    private final Label receiverCompanyNameLabel = new Label("Receiver Company Name: ");
    private final Label receiverAddressLabel = new Label("Receiver Address: ");
    private final Label receiverPhoneNumberLabel = new Label("Receiver Phone Number: ");
    private final Label receiverReeferTemperatureLabel = new Label("Receiver Reefer Temperature: ");
    private final Label receiverDeliveryAddressLabel = new Label("Receiver Delivery Address: ");
    private final Label receiverPickupDateTimeLabel = new Label("Receiver Pickup Date: ");
    private final Label receiverApproxWeightLabel = new Label("Receiver Approximate Weight: ");
    private final Label receiverPickupNumberLabel = new Label("Receiver Pickup Number: ");
    private final Label receiverExtraInfoLabel = new Label("Extra Receiver Information: ");
    private final Label grossLabel = new Label("Gross: ");
    private final Label lumperFeeLabel = new Label("Lumper Cost: ");
    private final Label pickupDateLabel = new Label("Pickup Date: ");
    private final Label deliveryDateLabel = new Label("Delivery Date: ");
    private final Label factoredFeeLabel = new Label("Factor Fee: ");
    private final Label factorDateLabel = new Label("Factor Date: ");
    private final Label factorDueDateLabel = new Label("Factor Due Date: ");
    private final Label dispatchedFeeLabel = new Label("Dispatch Fee: ");
    private TextField rkNumberTF = new TextField();
    private TextField otbNumberTF = new TextField();
    private TextField brokerCompanyNameTF = new TextField();
    private TextField brokerAddressTF = new TextField();
    private TextField brokerPhoneNumberTF = new TextField();
    private TextField brokerReeferTemperatureTF = new TextField();
    private TextField brokerEmailTF = new TextField();
    private TextField brokerNameTF = new TextField();
    private TextField brokerPONumberTF = new TextField();
    private TextArea brokerExtraInfoTA = new TextArea();
    private TextField shipperCompanyNameTF = new TextField();
    private TextField shipperAddressTF = new TextField();
    private TextField shipperPhoneNumberTF = new TextField();
    private TextField shipperReeferTemperatureTF = new TextField();
    private TextField shipperDeliveryAddressTF = new TextField();
    private TextField shipperPickupDateTimeTF = new TextField();
    private TextField shipperApproxWeightTF = new TextField();
    private TextField shipperConfirmationNumberTF = new TextField();
    private TextArea shipperExtraInfoTA = new TextArea();
    private TextField receiverCompanyNameTF = new TextField();
    private TextField receiverAddressTF = new TextField();
    private TextField receiverPhoneNumberTF = new TextField();
    private TextField receiverReeferTemperatureTF = new TextField();
    private TextField receiverDeliveryAddressTF = new TextField();
    private TextField receiverPickupDateTimeTF = new TextField();
    private TextField receiverApproxWeightTF = new TextField();
    private TextField receiverPickupNumberTF = new TextField();
    private TextArea receiverExtraInfoTA = new TextArea();
    private TextField grossTF = new TextField();
    private TextField lumperFeeTF = new TextField("0");
    private TextField pickupDateTF = new TextField();
    private TextField deliveryDateTF = new TextField();
    private TextField factoredFeeTF = new TextField("0");
    private TextField factorDateTF = new TextField();
    private TextField factorDueDateTF = new TextField();
    private TextField dispatchedFeeTF = new TextField("0");
    private TextField dispatchedCostDollarsTF = new TextField("0");
    private TextField otbCostTF = new TextField();
    private TextField netTF = new TextField();

    private String factorCostPay;

    private YearMonth ym;

    // comboboxes
    private ComboBox<String> brokerDropdown = new ComboBox<>();
    private ComboBox<String> shipperDropdown = new ComboBox<>();
    private ComboBox<String> receiverDropdown = new ComboBox<>();

    // modify and group elements into hbox
    private HBox otbNumberHBox = new HBox(5, new Label("   "), otbNumberTF, new Label(""));
    private HBox brokerCompanyNameHBox = new HBox(5, new Label("   "), brokerDropdown, new Label(""));
    private HBox brokerAddressHBox = new HBox(5, new Label("   "), brokerAddressTF, new Label(""));
    private HBox brokerPhoneNumberHBox = new HBox(5, new Label("   "), brokerPhoneNumberTF, new Label(""));
    private HBox brokerReeferTempHBox = new HBox(5, new Label("   "), brokerReeferTemperatureTF, new Label(""));
    private HBox brokerEmailHBox = new HBox(5, new Label("   "), brokerEmailTF, new Label(""));
    private HBox brokerNameHBox = new HBox(5, new Label("   "), brokerNameTF, new Label(""));
    private HBox brokerPONumber = new HBox(5, new Label("   "), brokerPONumberTF, new Label(""));
    private HBox brokerExtraInfoHBox = new HBox(5, new Label("   "), brokerExtraInfoTA, new Label(""));
    private HBox shipperCompanyNameHBox = new HBox(5, new Label("   "), shipperDropdown, new Label(""));
    private HBox shipperAddressHBox = new HBox(5, new Label("   "), shipperAddressTF, new Label(""));
    private HBox shipperPhoneNumberHBox = new HBox(5, new Label("   "), shipperPhoneNumberTF, new Label(""));
    private HBox shipperReeferTempHBox = new HBox(5, new Label("   "), shipperReeferTemperatureTF, new Label(""));
    private HBox shipperDeliveryAddressHBox = new HBox(5, new Label("   "), shipperDeliveryAddressTF, new Label(""));
    private HBox shipperPickupDateTimeHBox = new HBox(5, new Label("   "), shipperPickupDateTimeTF, new Label(""));
    private HBox shipperApproximateWeightHBox = new HBox(5, new Label("   "), shipperApproxWeightTF, new Label(""));
    private HBox shipperConfirmationNumberHBox = new HBox(5, new Label("   "), shipperConfirmationNumberTF, new Label(""));
    private HBox shipperExtraInfoHBox = new HBox(5, new Label("   "), shipperExtraInfoTA, new Label(""));
    private HBox receiverCompanyNameHBox = new HBox(5, new Label("   "), receiverDropdown, new Label(""));
    private HBox receiverAddressHBox = new HBox(5, new Label("   "), receiverAddressTF, new Label(""));
    private HBox receiverPhoneNumberHBox = new HBox(5, new Label("   "), receiverPhoneNumberTF, new Label(""));
    private HBox receiverReeferTempHBox = new HBox(5, new Label("   "), receiverReeferTemperatureTF, new Label(""));
    private HBox receiverDeliveryAddressHBox = new HBox(5, new Label("   "), receiverDeliveryAddressTF, new Label(""));
    private HBox receiverPickupDateTimeHBox = new HBox(5, new Label("   "), receiverPickupDateTimeTF, new Label(""));
    private HBox receiverApproximateWeightHBox = new HBox(5, new Label("   "), receiverApproxWeightTF, new Label(""));
    private HBox receiverPickupNumberHBox = new HBox(5, new Label("   "), receiverPickupNumberTF, new Label(""));
    private HBox receiverExtraInfoHBox = new HBox(5, new Label("   "), receiverExtraInfoTA, new Label(""));
    private HBox grossHBox = new HBox(5, new Label("$"), grossTF);
    private HBox lumperHBox = new HBox(5, new Label("$"), lumperFeeTF);
    private HBox pickupDateHBox = new HBox(5, new Label("   "), pickupDateTF);
    private HBox deliveryDateHBox = new HBox(5, new Label("   "), deliveryDateTF);
    private HBox factorHBox = new HBox(5, new Label("   "), factoredFeeTF, new Label("%"));
    private HBox factorDateHBox = new HBox(5, new Label("   "), factorDateTF);
    private HBox factorDueDateHBox = new HBox(5, new Label("   "), factorDueDateTF);
    private HBox dispatchHBox = new HBox(5, new Label("   "), dispatchedFeeTF, new Label("%"));

    private ComboBox<String> rkNumberSuffix = new ComboBox<>();

    private Button confirmButton = new Button("Confirm");

    private CheckBox lumperFeeCheckbox = new CheckBox();
    private CheckBox dispatchedFeeCheckbox = new CheckBox();
    private CheckBox factoredFeeCheckbox = new CheckBox();

    // extra information checkboxes
    CheckBox brokerExtraInfoCheckbox = new CheckBox();
    CheckBox shipperExtraInfoCheckbox = new CheckBox();
    CheckBox receiverExtraInfoCheckbox = new CheckBox();

    private Label rkNetPayLabel = new Label("");
    private Label dispatcherNetPayLabel = new Label("");

    List<Pair<Label, HBox>> formFields = new ArrayList<>();

    private GridPane gridPane = new GridPane();
    private VBox vBox = new VBox(gridPane);

    private String selectedSuffix = "";

    // constructor for creating a new invoice
    public InvoiceCreator(WindowCloseCallback callback) {
        this.callback = callback;
        createPane();
    }

    // constructor for editing an existing invoice
    public InvoiceCreator(WindowCloseCallback callback, Invoice invoice) {
        this.callback = callback;
        this.invoice = invoice;
        createPane();
        fillFieldsWithInvoiceData();
    }

    private void createPane() {
        // add fields to list pair for easy iteration
        formFields.add(new Pair<>(otbNumberLabel, otbNumberHBox));
        formFields.add(new Pair<>(brokerCompanyNameLabel, brokerCompanyNameHBox));
        formFields.add(new Pair<>(brokerAddressLabel, brokerAddressHBox));
        formFields.add(new Pair<>(brokerPhoneNumberLabel, brokerPhoneNumberHBox));
        formFields.add(new Pair<>(brokerReeferTemperatureLabel, brokerReeferTempHBox));
        formFields.add(new Pair<>(brokerEmailLabel, brokerEmailHBox));
        formFields.add(new Pair<>(brokerNameLabel, brokerNameHBox));
        formFields.add(new Pair<>(brokerPONumberLabel, brokerPONumber));
        formFields.add(new Pair<>(brokerExtraInfoLabel, brokerExtraInfoHBox));
        formFields.add(new Pair<>(shipperCompanyNameLabel, shipperCompanyNameHBox));
        formFields.add(new Pair<>(shipperAddressLabel, shipperAddressHBox));
        formFields.add(new Pair<>(shipperPhoneNumberLabel, shipperPhoneNumberHBox));
        formFields.add(new Pair<>(shipperReeferTemperatureLabel, shipperReeferTempHBox));
        formFields.add(new Pair<>(shipperDeliveryAddressLabel, shipperDeliveryAddressHBox));
        formFields.add(new Pair<>(shipperPickupDateTimeLabel, shipperPickupDateTimeHBox));
        formFields.add(new Pair<>(shipperApproxWeightLabel, shipperApproximateWeightHBox));
        formFields.add(new Pair<>(shipperConfirmationNumberLabel, shipperConfirmationNumberHBox));
        formFields.add(new Pair<>(shipperExtraInfoLabel, shipperExtraInfoHBox));
        formFields.add(new Pair<>(receiverCompanyNameLabel, receiverCompanyNameHBox));
        formFields.add(new Pair<>(receiverAddressLabel, receiverAddressHBox));
        formFields.add(new Pair<>(receiverPhoneNumberLabel, receiverPhoneNumberHBox));
        formFields.add(new Pair<>(receiverReeferTemperatureLabel, receiverReeferTempHBox));
        formFields.add(new Pair<>(receiverDeliveryAddressLabel, receiverDeliveryAddressHBox));
        formFields.add(new Pair<>(receiverPickupDateTimeLabel, receiverPickupDateTimeHBox));
        formFields.add(new Pair<>(receiverApproxWeightLabel, receiverApproximateWeightHBox));
        formFields.add(new Pair<>(receiverPickupNumberLabel, receiverPickupNumberHBox));
        formFields.add(new Pair<>(receiverExtraInfoLabel, receiverExtraInfoHBox));
        formFields.add(new Pair<>(grossLabel, grossHBox));
        formFields.add(new Pair<>(lumperFeeLabel, lumperHBox));
        formFields.add(new Pair<>(pickupDateLabel, pickupDateHBox));
        formFields.add(new Pair<>(deliveryDateLabel, deliveryDateHBox));
        formFields.add(new Pair<>(factoredFeeLabel, factorHBox));
        formFields.add(new Pair<>(factorDateLabel, factorDateHBox));
        formFields.add(new Pair<>(factorDueDateLabel, factorDueDateHBox));
        formFields.add(new Pair<>(dispatchedFeeLabel, dispatchHBox));


        // load suffix dropdown
        rkNumberSuffix.getItems().addAll("M", "D");

        // load saved fields into dropdowns
        brokerDropdown.getItems().addAll(InvoiceStorage.getSavedBrokerNames());
        shipperDropdown.getItems().addAll(InvoiceStorage.getSavedShipperNames());
        receiverDropdown.getItems().addAll(InvoiceStorage.getSavedReceiverNames());

        // allow typing in ComboBoxes
        rkNumberSuffix.setEditable(true);
        brokerDropdown.setEditable(true);
        shipperDropdown.setEditable(true);
        receiverDropdown.setEditable(true);

        // listen to changes in RK# dropdown
        rkNumberSuffix.setOnAction(e -> updateFormBasedOnSuffix());

        // modify fees
        // lumper
        lumperFeeCheckbox.setSelected(false);
        lumperFeeTF.setEditable(false);
        lumperFeeTF.setStyle("-fx-background-color: #e0e0e0;"); // gray

        // dispatched
        dispatchedFeeCheckbox.setSelected(false);
        dispatchedFeeTF.setEditable(false);
        dispatchedFeeTF.setStyle("-fx-background-color: #e0e0e0;"); // gray

        // factored
        factoredFeeCheckbox.setSelected(false);
        factoredFeeTF.setEditable(false);
        factoredFeeTF.setStyle("-fx-background-color: #e0e0e0;"); // gray

        // allow lumper fee to be entered if checkbox selected
        lumperFeeCheckbox.setOnAction(e -> {
            if(lumperFeeCheckbox.isSelected()) {
                lumperFeeTF.setEditable(true);
                lumperFeeTF.setStyle("-fx-background-color: white;");
                lumperFeeTF.setText("");
            } else {
                lumperFeeTF.setEditable(false);
                lumperFeeTF.setText("0");
                lumperFeeTF.setStyle("-fx-background-color: #e0e0e0;"); // gray
            }
        });

        // allow dispatch fee to be entered if checkbox selected
        dispatchedFeeCheckbox.setOnAction(e -> {
            if(dispatchedFeeCheckbox.isSelected()) {
                dispatchedFeeTF.setEditable(true);
                dispatchedFeeTF.setStyle("-fx-background-color: white;");
                dispatchedFeeTF.setText("5");
            } else {
                dispatchedFeeTF.setEditable(false);
                dispatchedFeeTF.setText("0");
                dispatchedFeeTF.setStyle("-fx-background-color: #e0e0e0;"); // gray
            }
        });

        // allow factor fee to be entered if checkbox selected
        factoredFeeCheckbox.setOnAction(e -> {
            if(factoredFeeCheckbox.isSelected()) {
                factoredFeeTF.setEditable(true);
                factoredFeeTF.setStyle("-fx-background-color: white;");
                if(selectedSuffix == "M") {
                    factoredFeeTF.setText("2.5");
                } else {
                    factoredFeeTF.setText("1");
                }
            } else {
                factoredFeeTF.setEditable(false);
                factoredFeeTF.setText("0");
                factoredFeeTF.setStyle("-fx-background-color: #e0e0e0;"); // gray
            }
        });

        // show/hide extra info text area
        brokerExtraInfoTA.setEditable(false);
        brokerExtraInfoTA.setStyle("-fx-background-color: #e0e0e0;"); // gray

        shipperExtraInfoTA.setEditable(false);
        shipperExtraInfoTA.setStyle("-fx-background-color: #e0e0e0;"); // gray

        receiverExtraInfoTA.setEditable(false);
        receiverExtraInfoTA.setStyle("-fx-background-color: #e0e0e0;"); // gray

        // extra info checkbox handlers
        brokerExtraInfoCheckbox.setOnAction(e -> {
            if(brokerExtraInfoCheckbox.isSelected()) {
                brokerExtraInfoTA.setEditable(true);
                brokerExtraInfoTA.setStyle("-fx-background-color: white;");
                brokerExtraInfoTA.setPromptText("Enter additional information...");
            } else {
                brokerExtraInfoTA.setEditable(false);
                brokerExtraInfoTA.setStyle("-fx-background-color: #e0e0e0;"); // gray
            }
        });

        shipperExtraInfoCheckbox.setOnAction(e -> {
            if(shipperExtraInfoCheckbox.isSelected()) {
                shipperExtraInfoTA.setEditable(true);
                shipperExtraInfoTA.setStyle("-fx-background-color: white;");
                shipperExtraInfoTA.setPromptText("Enter additional information...");
            } else {
                shipperExtraInfoTA.setEditable(false);
                shipperExtraInfoTA.setStyle("-fx-background-color: #e0e0e0;"); // gray
            }
        });

        receiverExtraInfoCheckbox.setOnAction(e -> {
            if(receiverExtraInfoCheckbox.isSelected()) {
                receiverExtraInfoTA.setEditable(true);
                receiverExtraInfoTA.setStyle("-fx-background-color: white;");
                receiverExtraInfoTA.setPromptText("Enter additional information...");
            } else {
                receiverExtraInfoTA.setEditable(false);
                receiverExtraInfoTA.setStyle("-fx-background-color: #e0e0e0;"); // gray
            }
        });

        // autofill details when saved field is selected
        // broker autofill
        brokerDropdown.setOnAction(e -> {
            String selectedBroker = brokerDropdown.getValue();
            Broker broker = InvoiceStorage.getBroker(selectedBroker);
            if(broker != null) {
                brokerCompanyNameTF.setText(broker.getCompanyName());
                brokerAddressTF.setText(broker.getAddress());
                brokerPhoneNumberTF.setText(broker.getPhoneNumber());
                brokerReeferTemperatureTF.setText(broker.getReeferTemperature());
                brokerEmailTF.setText(broker.getEmail());
                brokerNameTF.setText(broker.getBrokerName());
                brokerPONumberTF.setText(broker.getPoNumber());
                brokerExtraInfoTA.setText(broker.getExtraInfo());

                // activate extra info checkbox if on
                if(broker.getExtraInfo() != null && !broker.getExtraInfo().equals("")) {
                    brokerExtraInfoCheckbox.setSelected(true);
                    brokerExtraInfoCheckbox.fireEvent(new ActionEvent());
                }
            }
        });

        // shipper autofill
        shipperDropdown.setOnAction(e -> {
            String selectedShipper = shipperDropdown.getValue();
            Shipper shipper = InvoiceStorage.getShipper(selectedShipper);
            if(shipper != null) {
                shipperCompanyNameTF.setText(shipper.getCompanyName());
                shipperAddressTF.setText(shipper.getAddress());
                shipperPhoneNumberTF.setText(shipper.getPhoneNumber());
                shipperReeferTemperatureTF.setText(shipper.getReeferTemperature());
                shipperDeliveryAddressTF.setText(shipper.getDeliveryAddress());
                shipperPickupDateTimeTF.setText(shipper.getPickupDateTime());
                shipperApproxWeightTF.setText(shipper.getApproximateWeight());
                shipperConfirmationNumberTF.setText(shipper.getConfirmationNumber());
                shipperExtraInfoTA.setText(shipper.getExtraInfo());

                // activate extra info checkbox if on
                if(shipper.getExtraInfo() != null && !shipper.getExtraInfo().equals("")) {
                    shipperExtraInfoCheckbox.setSelected(true);
                    shipperExtraInfoCheckbox.fireEvent(new ActionEvent());
                }
            }
        });

        // receiver autofill
        receiverDropdown.setOnAction(e -> {
            String selectedReceiver = receiverDropdown.getValue();
            Receiver receiver = InvoiceStorage.getReceiver(selectedReceiver);
            if(receiver != null) {
                receiverCompanyNameTF.setText(receiver.getCompanyName());
                receiverAddressTF.setText(receiver.getAddress());
                receiverPhoneNumberTF.setText(receiver.getPhoneNumber());
                receiverReeferTemperatureTF.setText(receiver.getReeferTemperature());
                receiverDeliveryAddressTF.setText(receiver.getDeliveryAddress());
                receiverPickupDateTimeTF.setText(receiver.getPickupDateTime());
                receiverApproxWeightTF.setText(receiver.getApproximateWeight());
                receiverPickupNumberTF.setText(receiver.getPickupNumber());
                receiverExtraInfoTA.setText(receiver.getExtraInfo());

                // activate extra info checkbox if on
                if(receiver.getExtraInfo() != null && !receiver.getExtraInfo().equals("")) {
                    receiverExtraInfoCheckbox.setSelected(true);
                    receiverExtraInfoCheckbox.fireEvent(new ActionEvent());
                }
            }
        });

        // listeners for text changes to update net
        setupListeners();

        // create main border pane
        BorderPane borderPane = new BorderPane();

        // create grid with required fields
        gridPane.setHgap(10);
        gridPane.setPrefWidth(400);

        // set column constraints
        ColumnConstraints labelConstraints = new ColumnConstraints(200);
        ColumnConstraints textFieldConstraints = new ColumnConstraints(200);
        ColumnConstraints extraConstraints = new ColumnConstraints(70);
        labelConstraints.setHalignment(HPos.LEFT);
        labelConstraints.setHgrow(Priority.NEVER);
        textFieldConstraints.setHalignment(HPos.LEFT);
        textFieldConstraints.setHgrow(Priority.NEVER);
        extraConstraints.setHalignment(HPos.RIGHT);
        extraConstraints.setHgrow(Priority.NEVER);
        gridPane.getColumnConstraints().addAll(labelConstraints, textFieldConstraints, extraConstraints);

        // group elements into HBox
        HBox rkNumberLabelSpacing = new HBox(5, new Label("   "), rkNumberTF);
        HBox rkNumberHBox = new HBox(5, new Label("-") {{setStyle("-fx-font-size: 20px");}}, rkNumberSuffix);

        // add RK Number label, text field and suffix dropdown
        gridPane.add(rkNumberLabel, 0, 0);
        gridPane.add(rkNumberLabelSpacing, 1, 0);
        gridPane.add(rkNumberHBox, 2, 0);

        // wrap in vbox so scrolling actually works
        vBox.setMinHeight((gridPane.getChildren().size() / 2) * 27); // number of rows * approx size of each row
        vBox.setPrefHeight((gridPane.getChildren().size() / 2) * 27);

        // create scroll bar
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setPrefViewportHeight(500);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // vbox for button and label
        rkNetPayLabel.setStyle("-fx-font-size: 16px;");
        VBox bottomElements = new VBox(5, dispatcherNetPayLabel, rkNetPayLabel, confirmButton);
        bottomElements.setAlignment(Pos.CENTER);

        // place confirm button
        borderPane.setBottom(bottomElements);

        borderPane.setCenter(scrollPane);
        this.getChildren().add(borderPane);

        handleEntry();
    }

    // set up event listeners for required text fields
    private void setupListeners() {
        grossTF.textProperty().addListener((observable, oldValue, newValue) -> updateNetProfit());
        lumperFeeTF.textProperty().addListener((observable, oldValue, newValue) -> updateNetProfit());
        dispatchedFeeTF.textProperty().addListener((observable, oldValue, newValue) -> updateNetProfit());
        factoredFeeTF.textProperty().addListener((observable, oldValue, newValue) -> updateNetProfit());
    }

    // consistently update net profit
    private void updateNetProfit() {
        try {
            BigDecimal sum;
            BigDecimal gross = new BigDecimal(grossTF.getText().isEmpty() ? "0" : grossTF.getText());
            BigDecimal lumperFee = new BigDecimal(lumperFeeTF.getText().isEmpty() ? "0" : lumperFeeTF.getText());
            BigDecimal dispatchedFee = new BigDecimal(dispatchedFeeTF.getText().isEmpty() ? "0" : dispatchedFeeTF.getText());
            BigDecimal factoredFee = new BigDecimal(factoredFeeTF.getText().isEmpty() ? "0" : factoredFeeTF.getText());

            // calculate net profit
            sum = gross; // first get gross
            sum = sum.add(lumperFee); // second add lumper fee
            BigDecimal dispatcherNetProfit = sum.multiply(dispatchedFee.divide(new BigDecimal(100))); // get dispatcher profit
            sum = sum.subtract(dispatcherNetProfit); // third subtract dispatch fee
            BigDecimal factorNetProfit = sum.multiply(factoredFee.divide(new BigDecimal(100)));
            BigDecimal netProfit = sum.subtract(factorNetProfit); // fourth subtract factor fee

            // convert to US dollars
            String netFormatted = DollarConverter.formatToDollars(netProfit);
            String factorFormatted = DollarConverter.formatToDollars(factorNetProfit);
            String dispatchedFormatted = DollarConverter.formatToDollars(dispatcherNetProfit);

            if(selectedSuffix == "D") {
                rkNetPayLabel.setText("OTB Net Profit: " + netFormatted);
                netTF.setText(netFormatted);
            } else {
                rkNetPayLabel.setText("Net Profit: " + netFormatted);
            }
            netTF.setText(netFormatted);
            factorCostPay = factorFormatted;
            dispatcherNetPayLabel.setText("Dispatcher cost: " + dispatchedFormatted);
            dispatchedCostDollarsTF.setText(dispatchedFormatted);

        } catch(NumberFormatException e) {
            rkNetPayLabel.setText("Net Profit: Error");
        }
    }

    private void handleEntry() {
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                invoice.setValidInvoice(true);
                // create broker shipper and receiver objects from inputted fields
                Broker broker = new Broker(brokerDropdown.getValue(), brokerAddressTF.getText(), brokerPhoneNumberTF.getText(),
                        brokerReeferTemperatureTF.getText(), brokerEmailTF.getText(),
                        brokerNameTF.getText(), brokerPONumberTF.getText(), brokerExtraInfoTA.getText());
                Shipper shipper = new Shipper(shipperDropdown.getValue(), shipperAddressTF.getText(), shipperPhoneNumberTF.getText(),
                        shipperReeferTemperatureTF.getText(), shipperDeliveryAddressTF.getText(), shipperPickupDateTimeTF.getText(),
                        shipperApproxWeightTF.getText(), shipperConfirmationNumberTF.getText(), shipperExtraInfoTA.getText());
                Receiver receiver = new Receiver(receiverDropdown.getValue(), receiverAddressTF.getText(), receiverPhoneNumberTF.getText(),
                        receiverReeferTemperatureTF.getText(), receiverDeliveryAddressTF.getText(), receiverPickupDateTimeTF.getText(),
                        receiverApproxWeightTF.getText(), receiverPickupNumberTF.getText(), receiverExtraInfoTA.getText());

                // convert inputted number fields to BigDecimals
                BigDecimal grossDecimal = bigDecimalConversion(grossTF, "gross");
                BigDecimal factorCostDecimal = bigDecimalConversion(factoredFeeTF, "factor cost");
                BigDecimal dispatchCostDecimal = bigDecimalConversion(dispatchedFeeTF, "dispatch cost");

                // format numbers to US dollars
                NumberFormat dollarFormatter = NumberFormat.getCurrencyInstance(Locale.US);
                String grossDollars = dollarFormatter.format(grossDecimal);
                String factorCostDollars = dollarFormatter.format(factorCostDecimal);
                String dispatchCostDollars = dollarFormatter.format(dispatchCostDecimal);

                // check for valid values before creating invoice
                if(invoice.isValidInvoice()) {
                    invoice.setRkNumber(rkNumberTF.getText() + "-" + selectedSuffix);
                    invoice.setOtbNumber(otbNumberTF.getText());
                    invoice.setBroker(broker);
                    invoice.setShipper(shipper);
                    invoice.setReceiver(receiver);
                    invoice.setGross(grossDollars);
                    invoice.setPickupDate(pickupDateTF.getText());
                    invoice.setDeliveryDate(deliveryDateTF.getText());
                    invoice.setFactorCostPercent(factorCostDollars);
                    invoice.setFactorCostPay(factorCostPay);
                    invoice.setFactorDate(factorDateTF.getText());
                    invoice.setFactorDueDate(factorDueDateTF.getText());
                    invoice.setDispatchCostPercent(dispatchCostDollars);
                    invoice.setDispatchPay(dispatchedCostDollarsTF.getText());

                    if(selectedSuffix == "D") {
                        invoice.setOtbCost(netTF.getText());
                        invoice.setNet("N/A");
                    } else {
                        invoice.setOtbCost("N/A");
                        invoice.setNet(netTF.getText());
                    }
                }

                // make proper RK Number
                String newRKNumber = rkNumberTF.getText() + "-" + selectedSuffix;

                // check if RK Number already exists
                boolean rkNumberFound = false;
                for(Invoice inv : InvoiceStorage.loadInvoices(ym)) {
                    if(inv.getRkNumber().equals(newRKNumber)) {
                        rkNumberFound = true;
                        break;
                    }
                }

                if(rkNumberFound) {
                    // show duplicate alert
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Duplicate RK Number");
                    alert.setHeaderText(newRKNumber + " already exists.");
                    alert.setContentText("Do you want to replace the existing invoice?");

                    ButtonType replaceButton = new ButtonType("Replace");
                    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(replaceButton, cancelButton);

                    alert.showAndWait().ifPresent(response -> {
                        if (response == replaceButton) {
                            callback.onCloseWindow();
                        }
                    });
                } else {
                    callback.onCloseWindow();
                }



            }
        });
    }

    private void updateFormBasedOnSuffix() {
        // receive RK# suffix
        selectedSuffix = rkNumberSuffix.getValue();

        // clear grid before populating
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);

        // loop through arrays to populate creator grid
        int i = 0;
        for(Pair<Label, HBox> entry : formFields) {
            // change form based on suffix
            if(selectedSuffix.equals("M")) {
                otbNumberTF.setText("N/A");
                otbCostTF.setText("0");

                if(entry.getKey().getText().equals("OTB#: ") ||
                        entry.getKey().getText().equals("OTB Net: ")) {
                    continue;
                }

                // add main labels, textboxes and spacing
                gridPane.add(entry.getKey(), 0, i + 1);
                gridPane.add(entry.getValue(), 1, i + 1);

                // add checkboxes when necessary
                if(entry.getKey().getText().equals("Lumper Cost: ")) {
                    gridPane.add(lumperFeeCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Factor Fee: ")) {
                    gridPane.add(factoredFeeCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Dispatch Fee: ")) {
                    gridPane.add(dispatchedFeeCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Extra Broker Information: ")) {
                    gridPane.add(brokerExtraInfoCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Extra Shipper Information: ")) {
                    gridPane.add(shipperExtraInfoCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Extra Receiver Information: ")) {
                    gridPane.add(receiverExtraInfoCheckbox, 2, i + 1);
                } else {
                    gridPane.add(new Label(""), 2, i + 1);
                }
                i++;
            } else {
                // add main labels, textboxes and spacing
                gridPane.add(entry.getKey(), 0, i + 1);
                gridPane.add(entry.getValue(), 1, i + 1);

                // add checkboxes when necessary
                if(entry.getKey().getText().equals("Lumper Cost: ")) {
                    gridPane.add(lumperFeeCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Factor Fee: ")) {
                    gridPane.add(factoredFeeCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Dispatch Fee: ")) {
                    gridPane.add(dispatchedFeeCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Extra Broker Information: ")) {
                    gridPane.add(brokerExtraInfoCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Extra Shipper Information: ")) {
                    gridPane.add(shipperExtraInfoCheckbox, 2, i + 1);
                } else if(entry.getKey().getText().equals("Extra Receiver Information: ")) {
                    gridPane.add(receiverExtraInfoCheckbox, 2, i + 1);
                } else {
                    gridPane.add(new Label(""), 2, i + 1);
                }
                i++;
            }
        }

        // resize vbox after population to fix scrollbar
        gridPane.applyCss();
        gridPane.layout();
        vBox.setPrefHeight(gridPane.getBoundsInLocal().getHeight());
    }

    private BigDecimal bigDecimalConversion(TextField tf, String fieldType) {
        try{
            BigDecimal bigDecimal = new BigDecimal(tf.getText());
            return bigDecimal;
        } catch(NumberFormatException e) {
            invoice.setValidInvoice(false);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText(tf.getText() + " is not a valid value for " + fieldType + ".");

            alert.showAndWait();
            return null;
        }
    }

    public Invoice getInvoice() {
        return invoice;
    }

    // modify values and fill form if editing
    private void fillFieldsWithInvoiceData() {
        if(invoice != null) {
            // get and set suffix
            selectedSuffix = invoice.getRkNumber().substring(invoice.getRkNumber().length() - 1);
            rkNumberSuffix.setValue(selectedSuffix);
            Platform.runLater(() -> rkNumberSuffix.fireEvent(new ActionEvent()));
            // remove suffix from number
            rkNumberTF.setText(invoice.getRkNumber().substring(0, invoice.getRkNumber().length() - 2));

            otbNumberTF.setText(invoice.getOtbNumber());
            brokerDropdown.setValue(invoice.getBroker().getCompanyName());
            brokerAddressTF.setText(invoice.getBroker().getAddress());
            brokerPhoneNumberTF.setText(invoice.getBroker().getPhoneNumber());
            brokerReeferTemperatureTF.setText(invoice.getBroker().getReeferTemperature());
            brokerEmailTF.setText(invoice.getBroker().getEmail());
            brokerNameTF.setText(invoice.getBroker().getBrokerName());
            brokerPONumberTF.setText(invoice.getBroker().getPoNumber());
            // broker extra info
            if(invoice.getBroker().getExtraInfo() != null && !invoice.getBroker().getExtraInfo().equals("")) {
                brokerExtraInfoCheckbox.setSelected(true);
                brokerExtraInfoCheckbox.fireEvent(new ActionEvent());
                brokerExtraInfoTA.setText(invoice.getBroker().getExtraInfo());
            }

            shipperDropdown.setValue(invoice.getShipper().getCompanyName());
            shipperAddressTF.setText(invoice.getShipper().getAddress());
            shipperPhoneNumberTF.setText(invoice.getShipper().getPhoneNumber());
            shipperReeferTemperatureTF.setText(invoice.getShipper().getReeferTemperature());
            shipperDeliveryAddressTF.setText(invoice.getShipper().getDeliveryAddress());
            shipperPickupDateTimeTF.setText(invoice.getShipper().getPickupDateTime());
            shipperApproxWeightTF.setText(invoice.getShipper().getApproximateWeight());
            shipperConfirmationNumberTF.setText(invoice.getShipper().getConfirmationNumber());
            // shipper extra info
            if(invoice.getShipper().getExtraInfo() != null && !invoice.getShipper().getExtraInfo().equals("")) {
                shipperExtraInfoCheckbox.setSelected(true);
                shipperExtraInfoCheckbox.fireEvent(new ActionEvent());
                shipperExtraInfoTA.setText(invoice.getShipper().getExtraInfo());
            }

            receiverDropdown.setValue(invoice.getReceiver().getCompanyName());
            receiverAddressTF.setText(invoice.getReceiver().getAddress());
            receiverPhoneNumberTF.setText(invoice.getReceiver().getPhoneNumber());
            receiverReeferTemperatureTF.setText(invoice.getReceiver().getReeferTemperature());
            receiverDeliveryAddressTF.setText(invoice.getReceiver().getDeliveryAddress());
            receiverPickupDateTimeTF.setText(invoice.getReceiver().getPickupDateTime());
            receiverApproxWeightTF.setText(invoice.getReceiver().getApproximateWeight());
            receiverPickupNumberTF.setText(invoice.getReceiver().getPickupNumber());
            // receiver extra info
            if(invoice.getReceiver().getExtraInfo() != null && !invoice.getReceiver().getExtraInfo().equals("")) {
                receiverExtraInfoCheckbox.setSelected(true);
                receiverExtraInfoCheckbox.fireEvent(new ActionEvent());
                receiverExtraInfoTA.setText(invoice.getReceiver().getExtraInfo());
            }

            grossTF.setText(DollarConverter.formatFromDollars(invoice.getGross()).toString());
            pickupDateTF.setText(invoice.getPickupDate());
            deliveryDateTF.setText(invoice.getDeliveryDate());
            factoredFeeTF.setText(DollarConverter.formatFromDollars(invoice.getFactorCostPercent()).toString());
            factorDateTF.setText(invoice.getFactorDate());
            factorDueDateTF.setText(invoice.getFactorDueDate());
            dispatchedFeeTF.setText(invoice.getDispatchCostPercent());
            dispatchedCostDollarsTF.setText(invoice.getDispatchPay());
            otbCostTF.setText(invoice.getOtbCost());

            if(!selectedSuffix.equals("D")) {
                netTF.setText(DollarConverter.formatFromDollars(invoice.getNet()).toString());
            }
        }
    }
}