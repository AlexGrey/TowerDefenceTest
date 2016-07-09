package com.palkagames.engine.level.tile;

import com.palkagames.engine.graphics.Screen;
import com.palkagames.engine.graphics.Sprite;

/**
 * Created by Zver on 09.07.2016.
 */
public class Tile {
    //координаты по х и у
    public int x,y;
    //спрайт используемый тайлом
    public Sprite sprite;

    //тайл травы
    public static Tile grass = new GrassTile(Sprite.grass);
    //пустой тайл
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }
    //неизвестно что
    public boolean solid(){
        return false;
    }
}
