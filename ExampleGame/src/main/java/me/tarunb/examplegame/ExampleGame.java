package me.tarunb.examplegame;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.tarunb.gamefx.entity.Entity;
import me.tarunb.gamefx.graphics.Sprite;
import me.tarunb.gamefx.input.KeyHandler;
import me.tarunb.gamefx.physics.PolarVector;
import me.tarunb.gamefx.view.View;
import me.tarunb.gamefx.view.ViewManager;

import java.util.Random;

public class ExampleGame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        View1 v1 = new View1();

        ViewManager.registerView(v1);
        ViewManager.setView(v1.getId(), stage);

        stage.show();
    }

}
