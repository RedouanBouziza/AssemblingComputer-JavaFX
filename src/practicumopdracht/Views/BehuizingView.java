/**
 * @Author: Redouan Bouziza IS205
 * Master View
 */
package practicumopdracht.Views;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import practicumopdracht.Models.Behuizing;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BehuizingView extends View {

    private MenuItem oplopendMenuItem;
    private MenuItem aflopendMenuItem;
    private MenuItem opslaanMenuItem;
    private MenuItem ladenMenuItem;
    private MenuItem afsluitenMenuItem;

    private TextField hoogteBehuizingTextField;
    private TextField merkenTextField;
    private TextField serienummerBehuizingText;
    private CheckBox temperedGlassBehuizingCheckbox;
    private ListView<Behuizing> listViewBehuizing;
    private Button opslaanKnop;
    private Button nieuwKnop;
    private Button verwijderenKnop;
    private Button componentToevoegenKnop;
    private Parent root;

    public BehuizingView() {
        screenView();
    }

    private void screenView() {

//      MENUBAR --------------------------------------------------------------------------------------------------------
        MenuBar menuBarBovenAan = new MenuBar();
        Menu bestandMenu = new Menu("Bestand");
        opslaanMenuItem = new MenuItem("Opslaan");
        ladenMenuItem = new MenuItem("Laden");
        afsluitenMenuItem = new MenuItem("Afsluiten");

        bestandMenu.getItems().addAll(
                opslaanMenuItem,
                ladenMenuItem,
                new SeparatorMenuItem(),
                afsluitenMenuItem);

        Menu sorterenMenu = new Menu("Sorteren");
        oplopendMenuItem = new MenuItem("Oplopend");
        aflopendMenuItem = new MenuItem("Aflopend");

        sorterenMenu.getItems().addAll(oplopendMenuItem, aflopendMenuItem);

        menuBarBovenAan.getMenus().addAll(bestandMenu, sorterenMenu);
//      MENUBAR --------------------------------------------------------------------------------------------------------


//      MERK-BEHUIZING -------------------------------------------------------------------------------------------------
        Label merkLabel = new Label("Behuizing merk: ");
        VBox merkBehuizingLinks = new VBox();
        merkBehuizingLinks.getChildren().add(merkLabel);
        merkBehuizingLinks.setPadding(new Insets(4,0,0,0));
        merkBehuizingLinks.setMinWidth(160);

        merkenTextField = new TextField();
        VBox merkenTextFieldRechts = new VBox();
        merkenTextFieldRechts.getChildren().add(merkenTextField);
        merkenTextFieldRechts.setMinWidth(445);

        VBox merkBehuizingRechts = new VBox();
        merkBehuizingRechts.getChildren().add(merkenTextFieldRechts);
        merkBehuizingRechts.setAlignment(Pos.TOP_RIGHT);

        HBox merkBehuizing = new HBox();
        merkBehuizing.getChildren().addAll(merkBehuizingLinks, merkBehuizingRechts);
        merkBehuizing.setPadding(new Insets(0,0,12,0));
//      MERK-BEHUIZING -------------------------------------------------------------------------------------------------


//      HOOGTE-BEHUIZING -----------------------------------------------------------------------------------------------
        Label hoogteBehuizingLabel = new Label("Hoogte van behuizing (cm): ");
        VBox hoogteBehuizingLinks = new VBox();
        hoogteBehuizingLinks.getChildren().add(hoogteBehuizingLabel);
        hoogteBehuizingLinks.setMinWidth(160);
        hoogteBehuizingLinks.setPadding(new Insets(4,0,0,0));

        hoogteBehuizingTextField = new TextField();
        VBox hoogteBehuizingRechts = new VBox();
        hoogteBehuizingRechts.getChildren().add(hoogteBehuizingTextField);
        hoogteBehuizingRechts.setMinWidth(445);

        HBox hoogteBehuizing = new HBox();
        hoogteBehuizing.getChildren().addAll(hoogteBehuizingLinks, hoogteBehuizingRechts);
        hoogteBehuizing.setPadding(new Insets(0,0,12,0));
//      HOOGTE-BEHUIZING -----------------------------------------------------------------------------------------------


//      SERIENUMMER-BEHUIZING ------------------------------------------------------------------------------------------
        Label serienummerBehuizingLabel = new Label("Serienummer van behuizing: ");
        VBox serienummerBehuizingLinks = new VBox();
        serienummerBehuizingLinks.getChildren().add(serienummerBehuizingLabel);
        serienummerBehuizingLinks.setPadding(new Insets(4,0,0,0));
        serienummerBehuizingLinks.setMinWidth(160);

        serienummerBehuizingText = new TextField();
        serienummerBehuizingText.setPrefHeight(20);
        serienummerBehuizingText.setPrefWidth(20);

        VBox serienummerBehuizingRechts = new VBox();
        serienummerBehuizingRechts.getChildren().add(serienummerBehuizingText);
        serienummerBehuizingRechts.setMinWidth(445);

        HBox serienummerBehuizing = new HBox();
        serienummerBehuizing.getChildren().addAll(serienummerBehuizingLinks, serienummerBehuizingRechts);
        serienummerBehuizing.setPadding(new Insets(0,0,12,0));
//      SERIENUMMER-BEHUIZING ------------------------------------------------------------------------------------------


//      TEMPERED-GLASS -------------------------------------------------------------------------------------------------
        Label temperedGlassBehuizingLabel = new Label("Tempered glass aanwezig?: ");
        VBox temperedGlassBehuizingLinks = new VBox();
        temperedGlassBehuizingLinks.getChildren().add(temperedGlassBehuizingLabel);
        temperedGlassBehuizingLinks.setPadding(new Insets(0,0,0,0));
        temperedGlassBehuizingLinks.setMinWidth(160);

        temperedGlassBehuizingCheckbox = new CheckBox();
        VBox temperedGlassBehuizingRechts = new VBox();
        temperedGlassBehuizingRechts.getChildren().add(temperedGlassBehuizingCheckbox);

        HBox temperedGlassBehuizing = new HBox();
        temperedGlassBehuizing.getChildren().addAll(temperedGlassBehuizingLinks, temperedGlassBehuizingRechts);
        temperedGlassBehuizing.setPadding(new Insets(0,0,12,0));
//      TEMPERED-GLASS -------------------------------------------------------------------------------------------------


//      OPSLAAN-KNOP ---------------------------------------------------------------------------------------------------
        opslaanKnop = new Button("Opslaan");
        opslaanKnop.setMinWidth(605);
        VBox opslaanKnopButton = new VBox();
        opslaanKnopButton.getChildren().add(opslaanKnop);

        HBox opslaanKnopBehuizing = new HBox();
        opslaanKnopBehuizing.getChildren().add(opslaanKnopButton);
        opslaanKnopBehuizing.setPadding(new Insets(0,0,10,0));
//      OPSLAAN-KNOP ---------------------------------------------------------------------------------------------------


//      LISTVIEW -------------------------------------------------------------------------------------------------------
        listViewBehuizing = new ListView<>();
        listViewBehuizing.setPrefWidth(605);
        listViewBehuizing.setPrefHeight(190);

        GridPane lijstBehuizing = new GridPane();
        lijstBehuizing.getChildren().add(listViewBehuizing);
        lijstBehuizing.setPadding(new Insets(0,0,10,0));
        lijstBehuizing.setPrefHeight(190);
//      LISTVIEW -------------------------------------------------------------------------------------------------------


//      DRIE-KNOPPEN ---------------------------------------------------------------------------------------------------
        nieuwKnop = new Button("Nieuw");
        nieuwKnop.setMinWidth(150);
        VBox nieuwKnopBox = new VBox();
        nieuwKnopBox.getChildren().add(nieuwKnop);

        verwijderenKnop = new Button("Verwijderen");
        verwijderenKnop.setMinWidth(200);
        VBox verwijderenKnopBox = new VBox();
        verwijderenKnopBox.getChildren().add(verwijderenKnop);
        verwijderenKnopBox.setPadding(new Insets(0,10,0,10));

        componentToevoegenKnop = new Button("Component Bekijken/Toevoegen");
        componentToevoegenKnop.setMinWidth(235);
        VBox componentToevoegenKnopBox = new VBox();
        componentToevoegenKnopBox.getChildren().add(componentToevoegenKnop);

        HBox drieKnoppen = new HBox();
        drieKnoppen.getChildren().addAll(
                nieuwKnopBox,
                verwijderenKnopBox,
                componentToevoegenKnopBox
        );
        drieKnoppen.setPadding(new Insets(0,0,10,0));
//      DRIE-KNOPPEN ---------------------------------------------------------------------------------------------------


//      ALLES ----------------------------------------------------------------------------------------------------------
        BorderPane menubarBovenAanBP = new BorderPane();
        menubarBovenAanBP.setTop(menuBarBovenAan);

        VBox behuizingAlles2 = new VBox();
        behuizingAlles2.setMinHeight(480);
        behuizingAlles2.setPadding(new Insets(10));
        behuizingAlles2.getChildren().addAll(
                merkBehuizing,
                hoogteBehuizing,
                serienummerBehuizing,
                temperedGlassBehuizing,
                opslaanKnopBehuizing,
                lijstBehuizing,
                drieKnoppen
        );

        VBox behuizingAlles3 = new VBox();
        behuizingAlles3.getChildren().addAll(menubarBovenAanBP, behuizingAlles2);

        root = behuizingAlles3;

//      ALLES ----------------------------------------------------------------------------------------------------------
    }


//      MENUBAR --------------------------------------------------------------------------------------------------------
    public MenuItem getOpslaanMenuItem() {
        return opslaanMenuItem;
    }

    public MenuItem getLadenMenuItem() {
        return ladenMenuItem;
    }

    public MenuItem getAfsluitenMenuItem() {
        return afsluitenMenuItem;
    }

    public MenuItem getOplopendMenuItem() {
        return oplopendMenuItem;
    }

    public MenuItem getAflopendMenuItem() {
        return aflopendMenuItem;
    }

//      VIEW -----------------------------------------------------------------------------------------------------------
    public TextField getMerkenTextField() {
    return merkenTextField;
}

    public TextField getHoogteBehuizingTextField() {
        return hoogteBehuizingTextField;
    }

    public TextField getSerienummerBehuizingText() {
        return serienummerBehuizingText;
    }

    public CheckBox getTemperedGlassBehuizingCheckbox() {
        return temperedGlassBehuizingCheckbox;
    }

    public ListView<Behuizing> getListViewBehuizing() {
        return listViewBehuizing;
    }

    public Button getOpslaanKnop() {
        return opslaanKnop;
    }

    public Button getNieuwKnop() {
        return nieuwKnop;
    }

    public Button getVerwijderenKnop() {
        return verwijderenKnop;
    }

    public Button getComponentToevoegenKnop() {
        return componentToevoegenKnop;
    }

    protected Parent initializeView() {
        return root;
    }

    public Parent getRoot() {
        return root;
    }

}

