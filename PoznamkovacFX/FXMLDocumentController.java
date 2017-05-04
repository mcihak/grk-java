/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poznamkovacfx;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {

    private Databaze databaze;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView listView;

    @FXML
    private TextField tfText;

    @FXML
    private void handleBtnPridejAction(ActionEvent event) {
//        LocalDate datum = datePicker.getValue();
//        String text = tfText.getText();
//        databaze.pridejZaznam(datum, text);
//        listView.setItems(databaze.getVsechnyZaznamy());

        DialogPridej dialog = new DialogPridej(tfText.getScene().getWindow());
        dialog.showAndWait();
        Zaznam novy = dialog.getZaznam();
        if (novy != null) {
            databaze.pridejZaznam(novy);
        }
    }

    @FXML
    private void handleBtnVyhledejAction(ActionEvent event) {
        LocalDate datum = datePicker.getValue();
        listView.setItems(databaze.getZaznam(datum));
    }

    @FXML
    private void handleBtnVymazAction(ActionEvent event) {
        LocalDate datum = datePicker.getValue();
        databaze.vymazZaznamy(datum);
        listView.setItems(databaze.getVsechnyZaznamy());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaze = new Databaze();
        listView.setItems(databaze.getVsechnyZaznamy());
    }

}
