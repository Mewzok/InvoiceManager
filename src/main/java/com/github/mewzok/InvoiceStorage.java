package com.github.mewzok;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.YearMonth;
import java.util.*;

public class InvoiceStorage {
    private static final String LOGISTIC_FILE_PATH = "savedlogistics.dat";
    private static final Map<String, Broker> savedBrokers = new HashMap<>();
    private static final Map<String, Shipper> savedShippers = new HashMap<>();
    private static final Map<String, Receiver> savedReceivers = new HashMap<>();

    // save invoices to file
    public static void saveInvoices(ArrayList<Invoice> invoices, YearMonth ym) {
        String filename = "invoices_" + ym.getYear() + "_" + String.format("%02d", ym.getMonthValue()) + ".dat";

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            output.writeObject(invoices);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Saving File");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred when saving to file.");

            alert.showAndWait();

            e.printStackTrace();
        }
    }

    // load invoices from file
    public static ArrayList<Invoice> loadInvoices(YearMonth ym) {
        String filename = "invoices_" + ym.getYear() + "_" + String.format("%02d", ym.getMonth()) + ".dat";
        File file = new File(filename);
        if (!file.exists())
            return new ArrayList<Invoice>();

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Invoice>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Loading File");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred when loading from file.");

            alert.showAndWait();

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // save a previously used broker
    public static void saveLogistic(Logistic log) {
        Map<String, ? extends Logistic> logisticsMap = null;

        if(log instanceof Broker) {
            logisticsMap = savedBrokers;
        } else if(log instanceof  Shipper) {
            logisticsMap = savedShippers;
        } else if(log instanceof Receiver) {
            logisticsMap = savedReceivers;
        }

        if (logisticsMap != null) {
            // find any existing entry
            String oldName = null;
            for (Map.Entry<String, ? extends Logistic> entry : logisticsMap.entrySet()) {
                if(entry.getValue().equals(log)) {
                    oldName = entry.getKey();
                    break;
                }
            }

            // if name changed remove old entry
            if(oldName != null && !oldName.equals(log.getCompanyName())) {
                logisticsMap.remove(oldName);
            }

            // save new name
            ((Map<String, Logistic>) logisticsMap).put(log.getCompanyName(), log);
        }

        saveLogisticsToFile();;
    }

    // retrieve broker if already exists
    public static Broker getBroker(String companyName) {
        return savedBrokers.get(companyName);
    }

    // get String array of all saved broker names
    public static String[] getSavedBrokerNames() {
        return savedBrokers.keySet().toArray(new String[0]); // automatically created array of correct size
    }

    // retrieve shipper if already exists
    public static Shipper getShipper(String companyName) {
        return savedShippers.get(companyName);
    }

    // get String array of all saved broker names
    public static String[] getSavedShipperNames() {
        return savedShippers.keySet().toArray(new String[0]); // automatically created array of correct size
    }

    // retrieve receiver if already exists
    public static Receiver getReceiver(String companyName) {
        return savedReceivers.get(companyName);
    }

    // get String array of all saved broker names
    public static String[] getSavedReceiverNames() {
        return savedReceivers.keySet().toArray(new String[0]); // automatically created array of correct size
    }

    // save brokers shippers receivers to file
    public static void saveLogisticsToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(LOGISTIC_FILE_PATH))) {
            output.writeObject(savedBrokers);
            output.writeObject(savedShippers);
            output.writeObject(savedReceivers);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Saving File");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred when saving to file.");

            alert.showAndWait();

            e.printStackTrace();
        }
    }

    // load brokers shippers receivers from file
    public static void loadLogisticsFromFile() {
        File file = new File(LOGISTIC_FILE_PATH);

        // check for saved logistics
        if (!file.exists()) {
            return;
        }

        // load if exists
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(LOGISTIC_FILE_PATH))) {
            Object obj = input.readObject();
            if(obj instanceof HashMap) {
                savedBrokers.putAll((HashMap<String, Broker>) obj); // load brokers
            }

            obj = input.readObject(); // read next object
            if(obj instanceof HashMap) {
                savedShippers.putAll((HashMap<String, Shipper>) obj); // load shippers
            }

            obj = input.readObject(); // read next object
            if(obj instanceof HashMap) {
                savedReceivers.putAll((HashMap<String, Receiver>) obj); // load receivers
            }
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Loading File");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred when loading from file.");

            alert.showAndWait();

            e.printStackTrace();
            return;
        }
    }

    public static void deleteBroker(String broker) {
        savedBrokers.remove(broker);
    }

    public static void deleteShipper(String shipper) {
        savedShippers.remove(shipper);
    }

    public static void deleteReceiver(String receiver) {
        savedReceivers.remove(receiver);
    }
}
