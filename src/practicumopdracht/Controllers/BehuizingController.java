/**
 * @Author: Redouan Bouziza IS205
 * BehuizingController Class
 */
package practicumopdracht.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import practicumopdracht.Comparators.BehuizingComparatorAZ;
import practicumopdracht.MainApplication;
import practicumopdracht.Models.Behuizing;
import practicumopdracht.Views.BehuizingView;
import practicumopdracht.Views.View;

import java.util.List;

public class BehuizingController extends Controller {

    private BehuizingView view;

    public BehuizingController() {

        view = new BehuizingView();

        view.getOpslaanMenuItem().setOnAction(actionEvent -> menuOpslaan());
        view.getLadenMenuItem().setOnAction(actionEvent -> menuLaden());
        view.getAfsluitenMenuItem().setOnAction(actionEvent -> menuAfsluiten());

        view.getOplopendMenuItem().setOnAction(actionEvent -> menuOplopend());
        view.getAflopendMenuItem().setOnAction(actionEvent -> menuAflopend());

        view.getOpslaanKnop().setOnAction(actionEvent -> viewOpslaan());
        view.getNieuwKnop().setOnAction(actionEvent -> viewNieuw());
        view.getVerwijderenKnop().setOnAction(e -> viewVerwijderen());
        view.getComponentToevoegenKnop().setOnAction(actionEvent -> viewSchakelaar());

        List<Behuizing> behuizingen = MainApplication.getBehuizingDAO().getAll();
        ObservableList<Behuizing> behuizingenObservableList = FXCollections.observableList(behuizingen);
        view.getListViewBehuizing().setItems(behuizingenObservableList);
        FXCollections.sort(view.getListViewBehuizing().getItems(), new BehuizingComparatorAZ());
    }


    private void menuOpslaan() {
        ButtonType yes = new ButtonType("Ja");
        ButtonType no = new ButtonType("Nee");
        ButtonType oke = new ButtonType("Oke");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        alert.setTitle("Oplsaan");
        alert.setHeaderText("Weet u zeker dat u wilt opslaan?");
        alert.showAndWait();

        if (alert.getResult() == yes) {
            if (MainApplication.getBehuizingDAO().save() && MainApplication.getComponentDAO().save()) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION );
                alert2.getButtonTypes().clear();
                alert2.getButtonTypes().addAll(oke);
                alert2.setTitle("Opslaan");
                alert2.setHeaderText("Het opslaan is succesvol gelukt.");
                alert2.showAndWait();
            } else {
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.getButtonTypes().clear();
                alert3.getButtonTypes().addAll(oke);
                alert3.setTitle("Opslaan");
                alert3.setHeaderText("Het opslaan is helaas mislukt.");
                alert3.showAndWait();
            }
        }
    }

    private void menuLaden() {
        ButtonType yes = new ButtonType("Ja");
        ButtonType no = new ButtonType("Nee");
        ButtonType oke = new ButtonType("Oke");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        alert.setTitle("Laden");
        alert.setHeaderText("Weet u zeker dat u wilt laden?");
        alert.showAndWait();

        if (alert.getResult() == yes) {
            MainApplication.getBehuizingDAO().load();
            MainApplication.getComponentDAO().load();
            view.getListViewBehuizing().refresh();
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION );
            alert2.getButtonTypes().clear();
            alert2.getButtonTypes().addAll(oke);
            alert2.setTitle("Laden");
            alert2.setHeaderText("Het laden is gelukt.");
            alert2.showAndWait();

            List<Behuizing> behuizingen = MainApplication.getBehuizingDAO().getAll();
            ObservableList<Behuizing> behuizingenObservableList = FXCollections.observableList(behuizingen);
            view.getListViewBehuizing().setItems(behuizingenObservableList);
        }
    }

    private void menuAfsluiten(){

        ButtonType yes = new ButtonType("Ja");
        ButtonType no = new ButtonType("Nee");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        alert.setTitle("Afsluiten");
        alert.setHeaderText("Weet u zeker dat u wilt afsluiten?");
        alert.showAndWait();
        if (alert.getResult() == yes) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.getButtonTypes().clear();
            alert1.getButtonTypes().addAll(yes, no);
            alert1.setTitle("Afsluiten");
            alert1.setHeaderText("Wilt u nog opslaan?");
            alert1.showAndWait();

            if (alert1.getResult() == no) {
                Stage stage = (Stage) view.getRoot().getScene().getWindow();
                stage.close();
            }

            if (alert1.getResult() == yes) {
                MainApplication.getBehuizingDAO().save();
                MainApplication.getComponentDAO().save();
            }

        }

        if (alert.getResult() == no) {
            return;
        }
        Stage stage = (Stage) view.getRoot().getScene().getWindow();
        stage.close();
    }

    private void menuOplopend() {
        FXCollections.sort(view.getListViewBehuizing().getItems(), new BehuizingComparatorAZ());
    }

    private void menuAflopend() {
        FXCollections.sort(view.getListViewBehuizing().getItems(), new BehuizingComparatorAZ().reversed());
    }

//    ------------------------------------------------------------------------------------------------------------------

    private void viewOpslaan() {
        String merkenTextField = view.getMerkenTextField().getText();
        String hoogteBehuizingTextField = view.getHoogteBehuizingTextField().getText();
        String serienummerBehuizingTextField = view.getSerienummerBehuizingText().getText();
        boolean temperedGlass = view.getTemperedGlassBehuizingCheckbox().isSelected();

        StringBuilder stringBuilder = new StringBuilder();

        double hoogteBehuizingDouble = 0;
        int serienummerBehuizingInt = 0;


        if (merkenTextField.isBlank()) {
            stringBuilder.append("Je moet een merk invullen\n");
            view.getMerkenTextField().setStyle("-fx-border-color: red");
        }

        if (hoogteBehuizingTextField.isBlank()) {
            stringBuilder.append("Je moet de hoogte van de behuizing invullen (in CM)\n");
            view.getHoogteBehuizingTextField().setStyle("-fx-border-color: red");
        } else {
            try {
                hoogteBehuizingTextField = hoogteBehuizingTextField.replace(',', '.');
                hoogteBehuizingDouble = Double.parseDouble(hoogteBehuizingTextField);
            } catch (Exception e) {
                view.getHoogteBehuizingTextField().setStyle("-fx-border-color: red");
                stringBuilder.append("Hoogte moet wel met cijfers ingevuld worden!\n");
            }
        }

        if (serienummerBehuizingTextField.isBlank()) {
            stringBuilder.append("Je moet het serienummer van de behuizing invullen\n");
            view.getSerienummerBehuizingText().setStyle("-fx-border-color: red");
        } else {
            try {
                serienummerBehuizingInt = Integer.parseInt(serienummerBehuizingTextField);
            } catch (Exception e) {
                stringBuilder.append("Serienummer moet wel met cijfers ingevuld worden!\n");
                view.getSerienummerBehuizingText().setStyle("-fx-border-color: red");
            }
        }

        boolean temperedGlassBehuizing = view.getTemperedGlassBehuizingCheckbox().isSelected();

        String foutmelding = stringBuilder.toString();

        if (!foutmelding.isEmpty()) {
            ButtonType oke = new ButtonType("Oke");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Data onjuist!");
            alert.setHeaderText("Je hebt de verkeerde data ingevuld");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(oke);
            alert.setContentText(foutmelding);
            alert.showAndWait();

            return;
        }

        Behuizing behuizing = view.getListViewBehuizing().getSelectionModel().getSelectedItem();
        ButtonType oke = new ButtonType("Oke");

        if (behuizing != null) {
            behuizing.setSoort(merkenTextField);
            behuizing.setHoogte(hoogteBehuizingDouble);
            behuizing.setSerieNummer(serienummerBehuizingInt);
            behuizing.setBehuizingHeeftTemperedGlass(temperedGlass);
            view.getListViewBehuizing().refresh();
            view.getMerkenTextField().setStyle("-fx-border-color: none");
            view.getHoogteBehuizingTextField().setStyle("-fx-border-color: none");
            view.getSerienummerBehuizingText().setStyle("-fx-border-color: none");
        } else {
            view.getMerkenTextField().setStyle("-fx-border-color: none");
            view.getHoogteBehuizingTextField().setStyle("-fx-border-color: none");
            view.getSerienummerBehuizingText().setStyle("-fx-border-color: none");
            behuizing = new Behuizing(merkenTextField, hoogteBehuizingDouble,
                    serienummerBehuizingInt, temperedGlassBehuizing);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(oke);
            alert.setContentText(behuizing.toString());
            alert.setTitle("Nieuwe behuizing!");
            alert.setHeaderText("Het is je gelukt om een nieuwe behuizing toe te voegen.");
            alert.showAndWait();

            view.getListViewBehuizing().getItems().add(behuizing);

            clearFields();
        }

        MainApplication.getBehuizingDAO().addOrUpdate(behuizing);
    }

    private void viewNieuw() {
        ButtonType yes = new ButtonType("Ja");
        ButtonType no = new ButtonType("Nee");

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        alert.setTitle("Nieuwe velden!");
        alert.setHeaderText("Weet je zeker dat je nieuwe velden wilt toevoegen?");
        alert.showAndWait();

        if (alert.getResult() == yes) {
            clearFields();
        }
    }

    private void viewVerwijderen() {
        Behuizing behuizing = view.getListViewBehuizing().getSelectionModel().getSelectedItem();
        ButtonType yes = new ButtonType("Ja");
        ButtonType no = new ButtonType("Nee");
        ButtonType oke = new ButtonType("Oke");

        if (behuizing == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Behuizing selecteren!");
            alert.setHeaderText("Je moet een behuizing selecteren.");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(oke);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        alert.setTitle("Behuizing verwijderen!");
        alert.setHeaderText("Weet je zeker dat je deze behuizing wilt verwijderen?");
        alert.showAndWait();

        if (alert.getResult() == yes) {
            view.getListViewBehuizing().getItems().remove(behuizing);
            MainApplication.getBehuizingDAO().remove(behuizing);
            clearFields();
        }
    }

    private void viewSchakelaar() {
        Behuizing behuizing = view.getListViewBehuizing().getSelectionModel().getSelectedItem();
        ButtonType oke = new ButtonType("Oke");
        if (behuizing == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(oke);
            alert.setTitle("Geen behuizing geselecteerd!");
            alert.setHeaderText("Je moet een behuizing selecteren.");
            alert.showAndWait();
            return;
        }

        ComponentController controller = new ComponentController(behuizing);
        MainApplication.switchController(controller);
    }

    private void clearFields() {
        view.getMerkenTextField().clear();
        view.getHoogteBehuizingTextField().clear();
        view.getSerienummerBehuizingText().clear();
        view.getTemperedGlassBehuizingCheckbox().setSelected(false);
        view.getListViewBehuizing().getSelectionModel().clearSelection();
    }

    @Override
    public View getView() {
        return view;
    }
}
