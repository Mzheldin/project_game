package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;


public class MainShip extends Sprite {

    private Vector2 v0 = new Vector2(0.5f, 0);
    private Vector2 v = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;
    private  int pointer;
    private boolean pressedTouch;
    private float touchX;

    private BulletPool bulletPool;

    private TextureAtlas atlas;

    private Rect worldBounds;

    Sound shtSound = Gdx.audio.newSound((Gdx.files.internal("shoot.mp3")));

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.atlas = atlas;
        setHeightProportion(0.15f);
        this.bulletPool = bulletPool;
    }

    @Override
    public void update(float delta) {
        stopMove();
        pos.mulAdd(v, delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        this.pointer = pointer;
        touchX = touch.x;
        pressedTouch = true;
        if (touch.x < pos.x){
            moveLeft();
        } else if (touch.x > pos.x){
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
//        if (pointer == this.pointer) {
//            stop();
//            pressedTouch = false;
//        }
        stop();
        pressedTouch = false;
        return false;
    }


    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, atlas.findRegion("bulletMainShip"), pos, new Vector2(0, 0.5f), 0.01f, worldBounds, 1);
        shtSound.play(1.0f);
    }

    private void stopMove(){
        if (pressedTouch && Math.abs(pos.x - touchX) < 0.005f )stop();
        if (worldBounds.getRight() < getRight()) setRight(worldBounds.getRight());
        if (worldBounds.getLeft() > getLeft()) setLeft(worldBounds.getLeft());
    }

    public void dispose(){
        shtSound.dispose();
    }
}
