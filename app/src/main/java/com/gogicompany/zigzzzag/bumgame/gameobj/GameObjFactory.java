package com.gogicompany.zigzzzag.bumgame.gameobj;

import java.util.Random;

/**
 * Created by sbt-nikiforov-mo on 12/19/16.
 */

public class GameObjFactory {

    public static final GameObjFactory INSTANCE = new GameObjFactory();

    private GameObjFactory() {
    }

    public GameObj randomObj() {
        GameObj go = null;

        Random r = new Random();
        int i = r.nextInt(2);
        int randW = r.nextInt(100) + 50;
        int randH = r.nextInt(100) + 50;
        switch (i) {
            case 0:
                go = new CircleObj(300, 300, randH, 1, 1);
                break;
            case 1:
                go = new RectObj(300, 300, randW, randH, 1, 1);
                break;
            default:
                System.out.println("ALARMA! random fail: i=" + i);
                break;
        }

        return go;
    }
}
