package com.palkagames.engine.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Zver on 07.07.2016.
 */
public class SpriteSheet {
    //путь к карте спрайтов
    private String path;
    // размер карты
    public final int SIZE;
    // буфер пикселей
    public int[] pixels;
    //тайлы
    public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
    //конструктор карты спрайтов
    public SpriteSheet(String path, int size) {
        //инициализируем путь, размер, и буфер пикселей
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        //загружаем изображение
        load();
    }

    private void load(){
        try{
            //пытаемся загрузить изображение по указанному пути
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            //получаем длину и высоту изображения
            int w = image.getWidth();
            int h = image.getHeight();
            //получаем цвет каждого пикселя изображения
            image.getRGB(0, 0, w, h, pixels, 0, w);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
