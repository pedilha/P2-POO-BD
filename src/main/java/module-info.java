module br.com.aula.prova2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    exports br.com.aula.prova2;
    exports br.com.aula.prova2.controller;

    opens br.com.aula.prova2.controller to javafx.fxml;
    opens br.com.aula.prova2.view       to javafx.fxml;
  opens br.com.aula.prova2.model      to javafx.base, javafx.fxml;
}
