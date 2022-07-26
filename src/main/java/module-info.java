module edu.sharif.twitter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;

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
    requires java.datatransfer;
    requires java.desktop;

    opens edu.sharif.twitter to javafx.fxml;
    exports edu.sharif.twitter;

    exports edu.sharif.twitter.view;
    opens edu.sharif.twitter.view to javafx.fxml;

    exports edu.sharif.twitter.view.show;
    opens edu.sharif.twitter.view.show to javafx.fxml;

    opens edu.sharif.twitter.base to org.hibernate.orm.core;
    exports  edu.sharif.twitter.base;

    opens edu.sharif.twitter.entity to org.hibernate.orm.core;
    exports edu.sharif.twitter.entity;
    exports edu.sharif.twitter.view.data;
    opens edu.sharif.twitter.view.data to javafx.fxml;
}