package com.palkagames.engine.level;

import java.util.Random;

/**
 * Created by Zver on 09.07.2016.
 */
public class RandomLevel extends Level{
    //рандом
    private static final Random random = new Random();
    //коснтруктор уровня с длиной и высотой
    public RandomLevel(int width, int height) {
        super(width, height);
    }

    //генерируем уровень
    protected void generateLevel(){
        //проходим по карте длины и высоты
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //в каждый тайл записываем рандомное число от 0 до 4
                tiles[x + y * width] = random.nextInt(4);
            }
        }
    }
}
