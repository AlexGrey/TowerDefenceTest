package com.palkagames.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Zver on 07.07.2016.
 */
public class Keyboard implements KeyListener{
    //массив клавиш
    private boolean[] keys = new boolean[120];
    //флаги нажатых клавиш
    public boolean up, down, left, right;
    //обновление состояния нажатых клавиш
    public void update(){
        //клавиша вверх
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        //клавиша вниз
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        //клавиша влево
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        //клавиша вправо
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

        /*for (int i = 0; i < keys.length; i++) {
            if (keys[i]){
                System.out.println("Key: " + keys[i]);
            }
        }*/
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //метод вызывается когда клавиша нажата
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    //метод вызывается когда клавиша отжата
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
