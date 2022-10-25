package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.Controllers.BehuizingController;
import practicumopdracht.Controllers.Controller;
import practicumopdracht.Data.*;

public class MainApplication extends Application {

    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private static BehuizingDAO behuizingDAO;
    private static ComponentDAO componentDAO;
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

//        behuizingDAO = new BehuizingDummyDAO();
//        behuizingDAO = new TextBehuizingDAO();
        behuizingDAO = new BinaryBehuizingDAO();
//        componentDAO = new ComponentDummyDAO();
//        componentDAO = new TextComponentDAO();
        componentDAO = new ObjectComponentDAO();

//        behuizingDAO.load();
//        componentDAO.load();

        switchController(new BehuizingController());
    }

    public static void switchController(Controller controller) {
        stage.setScene(new Scene(controller.getView().getRoot()));
        stage.show();
    }

    public static BehuizingDAO getBehuizingDAO() {
        return behuizingDAO;
    }

    public static ComponentDAO getComponentDAO() {
        return componentDAO;
    }
}
