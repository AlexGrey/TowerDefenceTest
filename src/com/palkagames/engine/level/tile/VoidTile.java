package com.palkagames.engine.level.tile;

import com.palkagames.engine.graphics.Screen;
import com.palkagames.engine.graphics.Sprite;

/**
 * Created by Zver on 09.07.2016.
 */
public class VoidTile extends Tile {
    public VoidTile(Sprite sprite) {
        super(sprite);
    }
    //рендерим спрайт
    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, this);
    }
}
