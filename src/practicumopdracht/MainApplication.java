package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.Controllers.BehuizingController;
import practicumopdracht.Controllers.Controller;

public class MainApplication extends Application {

    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        switchController(new BehuizingController());
    }

    public static void switchController(Controller controller) {
        stage.setScene(new Scene(controller.getView().getRoot()));
        stage.show();
    }

}
