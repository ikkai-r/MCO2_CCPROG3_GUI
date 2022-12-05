package mco2_ccprog3;

import gui.scenes.OpeningScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
          launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        OpeningScene openingScene = new OpeningScene();
        primaryStage = openingScene.getMainStage();
        primaryStage.show();
    }
}