package com.gogicompany.zigzzzag.bumgame.gameobj;

import java.util.Random;

/**
 * Created by sbt-nikiforov-mo on 12/19/16.
 */

public class GameObjFactory {

    public static final GameObjFactory INSTANCE = new GameObjFactory();

    private GameObjFactory() {
    }

    public GameObj randomObj(int maxX, int maxY) {
        GameObj go = null;

        Random rand = new Random();
        int i = rand.nextInt(2);
        switch (i) {
            case 0: {
                go = new CircleObj(maxX, maxY);
                go.randomProps();
                break;
            }
            case 1: {
                go = new RectObj(maxX, maxY);
                go.randomProps();
                break;
            }
            default:
                System.out.println("ALARMA! random fail: i=" + i);
                break;
        }

        return go;
    }
}
