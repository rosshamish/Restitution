package com.blockTwo.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import java.util.ArrayList;
import java.util.Iterator;
import javax.media.opengl.GL;

/**
 *
 * @author r.anderson8
 */
public class Restitution implements ApplicationListener {

    private final int pixels_per_m = 30;
    
    private World world;
    private float timeStep = 1.0f / 60.0f;
    private int positionIters = 8;
    private int velocityIters = 3;
    
    private OrthographicCamera camera;
    
    private SpriteBatch batch;
    
    private Texture ballImage;
    
    @Override
    public void create() {
        /**
         * Sprite Batch.
         */
        batch = new SpriteBatch();
        
        /**
         * Textures.
         */
        ballImage = new Texture(Gdx.files.internal("assets/ring.png"));
        
        /**
         * World.
         */
        world = new World(new Vector2(0.0f, -9.81f), true);
        
        /**
         * Body Definitions.
         */
        // Ground
        BodyDef groundDef = new BodyDef();
        groundDef.allowSleep = false;
        groundDef.position.set(-50.0f, 0.0f);
        groundDef.type = BodyType.StaticBody;
        
        // Ball
        BodyDef ballDef = new BodyDef();
        ballDef.allowSleep = true;
        ballDef.position.set(new Vector2(0, 20));
        ballDef.type = BodyType.DynamicBody;
        
        /**
         * Fixture Definitions.
         */
        // Ground
        FixtureDef groundFixDef = new FixtureDef();
        groundFixDef.density = 5.0f;
        EdgeShape groundShape = new EdgeShape();
        groundShape.set(new Vector2(-50.0f, 0.0f), new Vector2(50.0f, 0.0f));
        groundFixDef.shape = groundShape;
        
        // Ball
        FixtureDef ballFixDef = new FixtureDef();
        ballFixDef.density = 5.0f;
        ballFixDef.restitution = 1.0f;
        ballFixDef.shape = new CircleShape();
        ballFixDef.shape.setRadius(1.0f);
        
        /**
         * Body Creation.
         */
        Body ground = world.createBody(groundDef);
        Texture groundImage = new Texture(ballImage.getTextureData());
        ground.setUserData(groundImage);
        Body ball = world.createBody(ballDef);
        ball.setUserData(ballImage);
        
        /**
         * Fixture Creation.
         */
        ground.createFixture(groundFixDef);
        ball.createFixture(ballFixDef);
        
        /**
         * Camera.
        */
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        
        
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        /**
         * World Update.
         */
        world.step(timeStep, velocityIters, positionIters);
        
        /**
         * Camera Update.
         */
        camera.update();
        
        /**
         * Clear Screen.
         */
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        /**
         * Drawing.
         */
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        
        Iterator<Body> iter = world.getBodies();
        while (iter.hasNext()) {
            Body body = iter.next();
            ArrayList<Fixture> fixtures = body.getFixtureList();
            for (int i=0; i < fixtures.size(); i++) {
                Body curBody = fixtures.get(i).getBody();
                Fixture curFix = fixtures.get(i);
                Vector2 worldPoint = curBody.getWorldPoint(Vector2.Zero);
                float diameter = curFix.getShape().getRadius() * 2;
                batch.draw((Texture)curBody.getUserData(), worldPoint.x * pixels_per_m, worldPoint.y * pixels_per_m, 
                        diameter * pixels_per_m, diameter * pixels_per_m);
            }
        }
        
        
        batch.end();
    }
    

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
