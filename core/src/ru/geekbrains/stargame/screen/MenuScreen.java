package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture img;

    private Vector2 pos;
    private Vector2 v;
    private int startPosX = 0;
    private int startPosY = 0;
    private Vector2 touchPos = new Vector2(startPosX,startPosY); //вектор направления движения
    private int direction;


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX,Gdx.graphics.getHeight() - screenY);
        v.set(0.5f,0.5f); //обновляем скорость, если она была снижена в методе move
        direction = 0; //прерываем движение по стрелке
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        pos = new Vector2(startPosX,startPosY);
        v = new Vector2(0.5f, 0.5f);
        direction = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.128f,0.53f, 0.9f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img,pos.x,pos.y);
        batch.end();
        move(pos, touchPos, v);
        moveDirection(direction);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.DOWN:
                direction = 1;
                touchPos.set(pos.x, pos.y - 1); //прерываем движение по тачдауну
                break;
            case Input.Keys.UP:
                direction = 2;
                touchPos.set(pos.x, pos.y + 1);
                break;
            case Input.Keys.LEFT:
                direction = 3;
                touchPos.set(pos.x - 1, pos.y);
                break;
            case Input.Keys.RIGHT:
                direction = 4;
                touchPos.set(pos.x + 1, pos.y);
                break;
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 19 || keycode == 20 || keycode == 21 || keycode == 22) direction = 0;
        return super.keyUp(keycode);
    }

    private void move(Vector2 start, Vector2 end, Vector2 v){
        if (start.x != end.x){
            while ((Math.abs(start.x - end.x) < v.x) && (v.x > 0.2f)) v.x /= 2; //уменьшаем скорость(если она > оставшегося пути) чтобы дойти до конца
            if (Math.abs(start.x - end.x) < Math.abs(start.x - end.x - v.x)) start.x += v.x;
            else start.x -= v.x;
        }
        if (start.y != end.y){
            while ((Math.abs(start.y - end.y) < v.y) && (v.y > 0.2f)) v.y /= 2;
            if (Math.abs(start.y - end.y) < Math.abs(start.y - end.y - v.y)) start.y += v.y;
            else start.y -= v.y;
        }
    }

    private void moveDirection(int direction){
        switch (direction){
            case 0:
                break;
            case 1:
                touchPos.sub(0, v.y);
                break;
            case 2:
                touchPos.add(0, v.y);
                break;
            case 3:
                touchPos.sub(v.x, 0);
                break;
            case 4:
                touchPos.add(v.x, 0);
                break;
        }
    }
}
