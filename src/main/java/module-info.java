module com.kumar.firstjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.kumar.firstjfx to javafx.fxml;
    exports com.kumar.firstjfx;
}
