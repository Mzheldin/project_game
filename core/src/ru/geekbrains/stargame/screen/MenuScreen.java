package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private Texture img;

    private Vector2 pos;
    private Vector2 v;
    private int startPosX;
    private int startPosY;
    private Vector2 touchPos; //вектор направления движения
    private Vector2 buff;
    private int direction;


    @Override
    public boolean touchDown(Vector2 touchPos, int pointer) {
        this.touchPos = touchPos;
        v.set(touchPos.cpy().sub(pos).scl(0.01f));
        direction = 0; //прерываем движение по стрелке
        return false;
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        startPosX = 0;
        startPosY = 0;
        pos = new Vector2(startPosX,startPosY);
        touchPos = new Vector2(startPosX,startPosY);
        v = new Vector2(0.5f, 0.5f);
        buff = new Vector2();
        direction = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.128f,0.53f, 0.9f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        move();
        batch.begin();
        batch.draw(img,pos.x,pos.y, 5.5f, 5.5f);
        batch.end();
        //moveDirection(direction);
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

//    @Override
//    public boolean keyDown(int keycode) {
//        touchPos.set(pos.x, pos.y);
//        v.set(0.5f, 0.5f);
//        switch (keycode){
//            case Input.Keys.DOWN:
//                direction = 1;
//                break;
//            case Input.Keys.UP:
//                direction = 2;
//                break;
//            case Input.Keys.LEFT:
//                direction = 3;
//                break;
//            case Input.Keys.RIGHT:
//                direction = 4;
//                break;
//        }
//        return super.keyDown(keycode);
//    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 19 || keycode == 20 || keycode == 21 || keycode == 22) direction = 0;
        return super.keyUp(keycode);
    }

    private void move(){
        buff.set(touchPos);
        if (buff.sub(pos).len() > v.len()) pos.add(v);
        else pos.set(touchPos);
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
