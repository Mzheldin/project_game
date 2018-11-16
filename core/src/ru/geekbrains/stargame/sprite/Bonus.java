package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Bonus extends Sprite {

    private Rect worldBounds;
    private Vector2 v = new Vector2();
    private  int heal;
    private float bonusTime;
    private MainShip mainShip;
    private enum Effect {HEAL, DAMAGE}

    public Bonus(){
        regions = new TextureRegion[1];
    }

    public void set(TextureAtlas atlas,
                    Vector2 pos0,
                    float v,
                    float height,
                    Rect worldBounds,
                    int heal,
                    float bonusTime,
                    MainShip mainShip)
    {
        this.regions[0] = atlas.findRegion("bulletMainShip");
        this.pos.set(pos0);
        this.v.set(0, v);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.heal = heal;
        this.bonusTime = bonusTime;
        this.mainShip = mainShip;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public void havingBonus(){
        if (Math.random() > 0.5) mainShip.repair(heal);
        else mainShip.setBonusTime(bonusTime);
        destroy();
    }

    public void setBonusTime(float bonusTime) {
        this.bonusTime = bonusTime;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }

    public float getBonusTime() {
        return bonusTime;
    }
}
