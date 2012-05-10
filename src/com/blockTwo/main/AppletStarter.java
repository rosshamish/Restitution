package com.blockTwo.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.blockTwo.game.Restitution;

/**
 *
 * @author Ross-Desktop
 */
public class AppletStarter {
    
    public static void main(String[] args) {
        new LwjglApplication(new Restitution(),
                                                  "Demo: Elastic & Inelastic Collisions!",
                                                  480, 320,
                                                  true);
    }

}
