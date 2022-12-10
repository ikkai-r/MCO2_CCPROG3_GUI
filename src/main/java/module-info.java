module com.example.mco2_ccprog3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens controller to javafx.fxml;
    exports controller;
    exports mainapp;
    opens mainapp to javafx.fxml;
}