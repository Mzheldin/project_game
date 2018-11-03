package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.math.Rnd;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.utils.Regions;


public class Ship extends Sprite {

    private Vector2 v0 = new Vector2(0, -0.5f);
    private BulletPool bulletPool;
    private TextureAtlas atlas;
    private Rect worldBounds;



    public Ship() {
        regions = new TextureRegion[1];
    }

    @Override
    public boolean isOutside(Rect other) {
        return getLeft() > other.getRight() || getRight() < other.getLeft() ||  getTop() < other.getBottom();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v0, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    public void set(TextureRegion region, float proportion, float vY, BulletPool bulletPool, Rect worldBounds){
        this.regions = Regions.split(region, 1, 2, 2);
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
        v0.set(0, -vY);
        setHeightProportion(proportion);
        float posX = Rnd.nextFloat(worldBounds.getLeft() + getHalfWidth(), worldBounds.getRight() - getHalfWidth());
        float posY = worldBounds.getTop() + getHeight();
        pos.set(posX, posY);
        System.out.println("posX " + pos.x + " posY " + pos.y);
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, atlas.findRegion("bulletMainShip"), pos, new Vector2(0, 0.5f), 0.01f, worldBounds, 1);
    }

}
