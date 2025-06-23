package com.github.mewzok;

import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class DollarConverter {
    private static final NumberFormat dollarFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public static String formatToDollars(BigDecimal number) {
        try {
            return dollarFormatter.format(number);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(number + " could not be converted to dollar amount.");

            alert.showAndWait();
            return "";
        }
    }

    public static BigDecimal formatFromDollars(String number) {
        try {
            return new BigDecimal(number.replaceAll("[$,]", ""));
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(number + " could not be converted to a number.");

            alert.showAndWait();
            return new BigDecimal(0);
        }
    }
}
