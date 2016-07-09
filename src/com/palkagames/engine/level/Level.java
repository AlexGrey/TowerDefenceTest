package com.palkagames.engine.level;

import com.palkagames.engine.graphics.Screen;
import com.palkagames.engine.level.tile.Tile;

/**
 * Created by Zver on 09.07.2016.
 */
public class Level {
    // длина и высота уровня
    protected int width, height;
    //тайлы уровня
    protected int[] tiles;

    //конструктор уровня с длиной и высотой
    public Level(int width, int height){
        //устанавливаем заданную длину и высоту
        this.width = width;
        this.height = height;
        //размер тайлов
        tiles = new int[width * height];
        //генерируем уровень
        generateLevel();
    }

    //конструктор уровня через загрузку
    public Level(String path){
        loadLevel(path);
    }

    public Level(){

    }

    private void loadLevel(String path) {

    }

    protected void generateLevel() {

    }

    public void update(){

    }

    private void time(){

    }

    public void render(int xScroll, int yScroll, Screen screen){
        //устанавливаем сдвиг экрана на х и у
        screen.setOffset(xScroll, yScroll);
        //начальный и конечные х и у (>>4 означает )
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height) >> 4;
        //проходим по уровню и рендерим каждый тайл
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }
    //получение тайла
    public Tile getTile(int x, int y){
        //если полученный тайл == 0 то возврщаем тайл травы
        if (tiles[x + y * width] == 0) return Tile.grass;
        //иначе пустышку
        return Tile.voidTile;
    }
}
