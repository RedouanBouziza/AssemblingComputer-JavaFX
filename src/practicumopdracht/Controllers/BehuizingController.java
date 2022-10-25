package practicumopdracht.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.Models.Behuizing;
import practicumopdracht.Views.BehuizingView;
import practicumopdracht.Views.View;

public class BehuizingController extends Controller {

    private BehuizingView view;

    public BehuizingController() {

        view = new BehuizingView();

        view.getOpslaanKnop().setOnAction(actionEvent -> viewOpslaan());
        view.getNieuwKnop().setOnAction(actionEvent -> viewNieuw());
        view.getVerwijderenKnop().setOnAction(e -> viewVerwijderen());
        view.getComponentToevoegenKnop().setOnAction(actionEvent -> viewSchakelaar());

    }

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
            alert.setTitle("Nieuwe behuizing");
            alert.setHeaderText("Het is je gelukt om een nieuwe behuizing toe te voegen.");
            alert.showAndWait();

            view.getListViewBehuizing().getItems().add(behuizing);

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
