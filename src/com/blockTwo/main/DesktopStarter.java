package com.blockTwo.main;

import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.blockTwo.game.Restitution;

/**
 * @author Ross-Desktop
 */
public class DesktopStarter {

    public static void main(String[] args) {
        new JoglApplication(new Restitution(),
                                                  "Restitution",
                                                  480, 320,
                                                  true);
    }
    
}
