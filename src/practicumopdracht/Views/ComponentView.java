/**
 * @Author: Redouan Bouziza IS205
 * Detail View
 */
package practicumopdracht.Views;

import javafx.geometry.Insets;
import practicumopdracht.Models.Component;
import practicumopdracht.Models.Behuizing;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class ComponentView extends View {

    private RadioButton componentAZknop;
    private RadioButton componentZAknop;
    private RadioButton componentDatumLaagNaarHoogknop;
    private RadioButton componentDatumHoogNaarLaagknop;
    private ToggleGroup sorterenGroep;

    private ComboBox<Behuizing> behuizingen;
    private TextField componentNaamTextField;
    private DatePicker datumGarantieComponent;
    private Button opslaanKnop;
    private ListView<Component> listViewComponent;
    private Button nieuwKnop;
    private Button verwijderenKnop;
    private Button terugKnop;
    private Parent root;

    public ComponentView() {
        screenView();
    }

    private void screenView() {

//      SELECTEER-BEHUIZING --------------------------------------------------------------------------------------------
        Label selecteerBehuizingLabel = new Label("Selecteer behuizing: ");
        VBox selecteerBehuizingLinks = new VBox();
        selecteerBehuizingLinks.getChildren().add(selecteerBehuizingLabel);
        selecteerBehuizingLinks.setPadding(new Insets(4,0,0,0));
        selecteerBehuizingLinks.setMinWidth(160);

        behuizingen = new ComboBox<Behuizing>();
        behuizingen.setMinWidth(445);
        TilePane behuizingenLijstKnop = new TilePane(behuizingen);
        behuizingenLijstKnop.setMinHeight(40);

        VBox selecteerBehuizingRechts = new VBox();
        selecteerBehuizingRechts.getChildren().add(behuizingenLijstKnop);
        selecteerBehuizingRechts.setAlignment(Pos.TOP_RIGHT);

        HBox selecteerBehuizing = new HBox();
        selecteerBehuizing.getChildren().addAll(selecteerBehuizingLinks, behuizingenLijstKnop);
        selecteerBehuizing.setPadding(new Insets(0,0,10,0));
//      SELECTEER-BEHUIZING --------------------------------------------------------------------------------------------


//      COMPONENT-NAAM -------------------------------------------------------------------------------------------------
        Label componentNaamLabel = new Label("Component naam: ");
        VBox componentNaamLinks = new VBox();
        componentNaamLinks.getChildren().add(componentNaamLabel);
        componentNaamLinks.setPadding(new Insets(4,0,0,0));
        componentNaamLinks.setMinWidth(160);

        componentNaamTextField = new TextField();
        VBox componentNaamTextFieldRechts = new VBox();
        componentNaamTextFieldRechts.getChildren().add(componentNaamTextField);
        componentNaamTextFieldRechts.setMinWidth(445);

        HBox componentNaam = new HBox();
        componentNaam.getChildren().addAll(componentNaamLinks, componentNaamTextFieldRechts);
        componentNaam.setPadding(new Insets(0,0,10,0));
//      COMPONENT-NAAM -------------------------------------------------------------------------------------------------


//      GARANTIE-DATUM -------------------------------------------------------------------------------------------------
        Label garantieDatumLabel = new Label("Datum van de garantie: ");
        VBox garantieDatumLabelLinks = new VBox();
        garantieDatumLabelLinks.getChildren().add(garantieDatumLabel);
        garantieDatumLabelLinks.setPadding(new Insets(4,0,0,0));
        garantieDatumLabelLinks.setMinWidth(160);

        datumGarantieComponent = new DatePicker();
        datumGarantieComponent.setMinWidth(445);

        VBox datumGarantieComponentRechts = new VBox();
        datumGarantieComponentRechts.getChildren().add(datumGarantieComponent);

        HBox garantieDatum = new HBox();
        garantieDatum.getChildren().addAll(garantieDatumLabelLinks, datumGarantieComponentRechts);
        garantieDatum.setPadding(new Insets(0,0,10,0));
//      GARANTIE-DATUM -------------------------------------------------------------------------------------------------


//      VIER-KNOPPEN ---------------------------------------------------------------------------------------------------
        Label sorteren = new Label("Sorteren: ");
        VBox sorterenlinks = new VBox();
        sorterenlinks.getChildren().add(sorteren);
        sorterenlinks.setPadding(new Insets(4,0,0,0));
        sorterenlinks.setMinWidth(150);

        componentAZknop = new RadioButton("A-Z");
        componentZAknop = new RadioButton("Z-A");
        componentDatumLaagNaarHoogknop = new RadioButton("Eerste datum 1-31");
        componentDatumHoogNaarLaagknop= new RadioButton("Laaste datum 31-1");

        sorterenGroep = new ToggleGroup();
        sorterenGroep.getToggles().addAll(
                componentAZknop,
                componentZAknop,
                componentDatumLaagNaarHoogknop,
                componentDatumHoogNaarLaagknop
        );

        HBox vierKnoppen = new HBox(
                sorterenlinks,
                componentAZknop,
                componentZAknop,
                componentDatumLaagNaarHoogknop,
                componentDatumHoogNaarLaagknop
        );
        vierKnoppen.setSpacing(10);
        vierKnoppen.setPadding(new Insets(0,0,10,0));
//      VIER-KNOPPEN ---------------------------------------------------------------------------------------------------


//      OPSLAAN-KNOP ---------------------------------------------------------------------------------------------------
        opslaanKnop = new Button("Opslaan");
        opslaanKnop.setMinWidth(605);
        VBox opslaanKnopButton = new VBox();
        opslaanKnopButton.getChildren().add(opslaanKnop);

        HBox opslaanComponent = new HBox();
        opslaanComponent.getChildren().add(opslaanKnopButton);
        opslaanComponent.setPadding(new Insets(0,0,10,0));
//      OPSLAAN-KNOP ---------------------------------------------------------------------------------------------------


//      LIJST-COMPONENT ------------------------------------------------------------------------------------------------
        listViewComponent = new ListView<>();
        listViewComponent.setPrefWidth(605);
        listViewComponent.setPrefHeight(168);

        GridPane lijstComponent = new GridPane();
        lijstComponent.getChildren().add(listViewComponent);
        lijstComponent.setPadding(new Insets(0,0,10,0));
        lijstComponent.setPrefHeight(168);
//      LIJST-COMPONENT ------------------------------------------------------------------------------------------------


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

        terugKnop = new Button("Terug naar behuizing overzicht");
        terugKnop.setMinWidth(235);
        VBox terugKnopBox = new VBox();
        terugKnopBox.getChildren().add(terugKnop);

        HBox drieKnoppen = new HBox();
        drieKnoppen.getChildren().addAll(
                nieuwKnopBox,
                verwijderenKnopBox,
                terugKnopBox
        );
        drieKnoppen.setPadding(new Insets(0,0,10,0));
//      DRIE-KNOPPEN ---------------------------------------------------------------------------------------------------


//      ALLES ----------------------------------------------------------------------------------------------------------
        VBox componentAlles = new VBox();

        componentAlles.getChildren().addAll(
                selecteerBehuizing,
                componentNaam,
                garantieDatum,
                vierKnoppen,
                opslaanComponent,
                lijstComponent,
                drieKnoppen
        );
        componentAlles.setMinHeight(480);
        componentAlles.setPadding(new Insets(10));

        root = componentAlles;

//      ALLES ----------------------------------------------------------------------------------------------------------

    }


//      RADIOBAR -------------------------------------------------------------------------------------------------------
    public RadioButton getComponentAZknop() {
        return componentAZknop;
    }

    public RadioButton getComponentZAknop() {
        return componentZAknop;
    }

    public RadioButton getComponentDatumLaagNaarHoogknop() {
        return componentDatumLaagNaarHoogknop;
    }

    public RadioButton getComponentDatumHoogNaarLaagknop() {
        return componentDatumHoogNaarLaagknop;
    }

    public ToggleGroup getSorterenGroep() {
        return sorterenGroep;
    }

//      VIEW -----------------------------------------------------------------------------------------------------------
    public ComboBox<Behuizing> getBehuizingen() {
        return behuizingen;
    }

    public TextField getComponentNaamTextField() {
        return componentNaamTextField;
    }

    public DatePicker getDatumGarantieComponent() {
        return datumGarantieComponent;
    }

    public ListView<Component> getListViewComponent() {
        return listViewComponent;
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

    public Button getTerugKnop() {
        return terugKnop;
    }

    @Override
    protected Parent initializeView() {
        return root;
    }

    public Parent getRoot() {
        return root;
    }
}

