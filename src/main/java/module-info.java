module edu.sharif.twitter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;
    requires java.persistence;
    requires antlr;
    requires java.sql;
    requires org.hibernate.orm.core;

    opens edu.sharif.twitter to javafx.fxml;
    exports edu.sharif.twitter;
}