package com.blockTwo.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;
import com.blockTwo.game.Restitution;

/**
 *
 * @author Ross-Desktop
 */
public class AppletRestitution extends LwjglApplet {
    
    private static final long serialVersionUID = 1L;
    public AppletRestitution() {
        super(new Restitution(), false);
    }
}
