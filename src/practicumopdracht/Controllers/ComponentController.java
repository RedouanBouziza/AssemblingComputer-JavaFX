package practicumopdracht.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.Models.Behuizing;
import practicumopdracht.Models.Component;
import practicumopdracht.Views.BehuizingView;
import practicumopdracht.Views.ComponentView;
import practicumopdracht.Views.View;

import java.time.LocalDate;

public class ComponentController extends Controller {

    private ComponentView view;

    public ComponentController(Behuizing behuizing) {

        view = new ComponentView();

        view.getOpslaanKnop().setOnAction(actionEvent -> viewOpslaan(behuizing));
        view.getNieuwKnop().setOnAction(actionEvent -> viewNieuw());
        view.getVerwijderenKnop().setOnAction(e -> viewVerwijderen());
        view.getTerugKnop().setOnAction(actionEvent -> viewSchakelaar());

        view.getBehuizingen().setValue(behuizing);
    }

    private void viewOpslaan(Behuizing behuizing) {

        String componentNaam = view.getComponentNaamTextField().getText();
        LocalDate datumGarantie = view.getDatumGarantieComponent().getValue();

        StringBuilder stringBuilder = new StringBuilder();

        if (componentNaam.isBlank()) {
            stringBuilder.append("Je moet een component naam invullen\n");
            view.getComponentNaamTextField().setStyle("-fx-border-color: red");
        }

        if (datumGarantie == null) {
            stringBuilder.append("Je moet een geldige datum invullen\n");
            view.getDatumGarantieComponent().setStyle("-fx-border-color: red");
        }

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

        Component component = view.getListViewComponent().getSelectionModel().getSelectedItem();
        ButtonType oke = new ButtonType("Oke");

        if (component != null) {
            component.setNaam(componentNaam);
            component.setDatum(datumGarantie);
            view.getListViewComponent().refresh();
            view.getComponentNaamTextField().setStyle("-fx-border-color: none");
            view.getDatumGarantieComponent().setStyle("-fx-border-color: none");
        } else {
            view.getComponentNaamTextField().setStyle("-fx-border-color: none");
            view.getDatumGarantieComponent().setStyle("-fx-border-color: none");
            component = new Component(behuizing, componentNaam, datumGarantie);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(oke);
            alert.setContentText(behuizing.toString());
            alert.setTitle("Nieuwe component");
            alert.setHeaderText("Het is je gelukt om een nieuwe component toe te voegen.");
            alert.showAndWait();

            view.getListViewComponent().getItems().add(component);

            clearFields();
        }

    }

    private void viewNieuw() {
        ButtonType yes = new ButtonType("Ja");
        ButtonType no = new ButtonType("Nee");

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        alert.setTitle("Nieuwe velden");
        alert.setHeaderText("Weet je zeker dat je nieuwe velden wilt toevoegen?");
        alert.showAndWait();

        if (alert.getResult() == yes) {
            clearFields();
        }
    }

    private void viewVerwijderen() {
        Component component = view.getListViewComponent().getSelectionModel().getSelectedItem();
        ButtonType yes = new ButtonType("Ja");
        ButtonType no = new ButtonType("Nee");
        ButtonType oke = new ButtonType("Oke");

        if (component == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Component selecteren!");
            alert.setHeaderText("Je moet een component selecteren.");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(oke);
            alert.showAndWait();

            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        alert.setTitle("component verwijderen!");
        alert.setHeaderText("Weet je zeker dat je deze component wilt verwijderen?");
        alert.showAndWait();

        if (alert.getResult() == yes) {
            view.getListViewComponent().getItems().remove(component);

            clearFields();
        }
    }

    private void viewSchakelaar() {
        MainApplication.switchController(new BehuizingController());
    }

    private void clearFields() {
        view.getComponentNaamTextField().clear();
        view.getDatumGarantieComponent().setValue(null);
        view.getListViewComponent().getSelectionModel().clearSelection();
    }

    @Override
    public View getView() {
        return view;
    }
}

