module com.github.mewzok {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.mewzok to javafx.fxml;
    exports com.github.mewzok;
}
