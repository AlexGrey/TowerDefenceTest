package com.palkagames.engine.graphics;

/**
 * Created by Zver on 08.07.2016.
 */
public class Sprite {
    //размер спрайта
    public final int SIZE;
    //координаты по x и y
    private int x, y;
    //массив пикселей
    public int[] pixels;
    //экземпляр класса
    private SpriteSheet sheet;

    //иниализация спрайта травы
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
    //конструктор спрайта
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        // инициализируем х и у
        this.x = x * size;
        this.y = y * size;
        // размер и карту спрайтов
        SIZE = size;
        this.sheet = sheet;
        //размер буфера пикселей
        pixels = new int[SIZE * SIZE];
        //загружаем спрайт
        load();
    }

    public Sprite(int size, int colour){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = colour;
        }
    }

    //загрузка спрайта
    private void load(){
        ///проходим по размеру по х и у
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                //заполняем буфер пикселей спрайта, пикселями карты спрайтов
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}
