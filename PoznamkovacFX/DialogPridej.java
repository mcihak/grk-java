package poznamkovacfx;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class DialogPridej extends Stage {

    private Zaznam zaznam = null;

    public DialogPridej(Window okno) {
        setTitle("Nový záznam");
        setWidth(350);
        setHeight(250);

        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        initOwner(okno);
        setScene(vytvorScenu());
    }

    public Zaznam getZaznam() {
        return zaznam;
    }

    private Scene vytvorScenu() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);

        // Mřížka s TextFieldy a Labely
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Komponenty
        TextField tfText = new TextField();
        DatePicker datePicker = new DatePicker();
        Label lbText = new Label("Text");
        Label lbDatum = new Label("Datum");

        grid.add(lbDatum, 0, 0);
        grid.add(datePicker, 1, 0);
        grid.add(lbText, 0, 1);
        grid.add(tfText, 1, 1);

        // Tlačítko
        Button tlacitko = new Button("OK");
        tlacitko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    LocalDate datum = datePicker.getValue();
                    String text = tfText.getText();
                    zaznam = new Zaznam(datum, text);
                    hide();
                } catch (DateTimeParseException | IllegalArgumentException ex) {
                    System.out.println("Chyba: " + ex.getMessage());
                }

            }
        });

        box.getChildren().addAll(grid, tlacitko);
        return new Scene(box);
    }
}
