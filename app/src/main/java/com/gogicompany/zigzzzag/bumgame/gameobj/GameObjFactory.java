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

        Random r = new Random();
        int i = r.nextInt(2);
        int x = r.nextInt(maxX);
        int randW = r.nextInt(50) + 50;
        int randH = r.nextInt(50) + 50;
        int randDy = r.nextInt(10) + 10;
        switch (i) {
            case 0:
                go = new CircleObj(x, maxY, randH, 0, -randDy, maxX, maxY);
                break;
            case 1:
                go = new RectObj(x, maxY, randW, randH, 0, -randDy, maxX, maxY);
                break;
            default:
                System.out.println("ALARMA! random fail: i=" + i);
                break;
        }

        return go;
    }
}
