package me.tarunb.examplegame;

import me.tarunb.gamefx.Game;
import me.tarunb.gamefx.view.ViewManager;

public class ExampleGame {

    public static void main(String[] args) {
        Game.start(data -> {
            Game.setName("Example Game");

            View1 v1 = new View1();

            ViewManager.registerView(v1);
            ViewManager.setDefaultView(v1.getId());
        });
    }

}
