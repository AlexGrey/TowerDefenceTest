package com.palkagames.engine;

import com.palkagames.engine.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Zver on 04.07.2016.
 */
public class Game extends Canvas implements Runnable{
    //длина игрового экрана
    public static final int width = 300;
    //высота игрового экрана
    public static final int height = width / 16 * 9;
    //масштабирование?
    public static final int scale = 3;
    //заголовок игрового окна
    public static String title = "Tower Defense";

    //основной игровой поток
    private Thread thread;
    //контент панель
    private JFrame frame;
    //запущен игровой цикл или нет?
    private boolean running = false;
    //основной игровой экран
    private Screen screen;
    //буффер изображения
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    //растеризованный пиксельный буфер
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    private int xOffset = 0;
    private int yOffset = 0;

    //конструктор
    public Game(){
        //установка размера экрана
        Dimension size = new Dimension(width * scale, height * scale);
        //установка предпочитаемого разрешения
        setPreferredSize(size);
        //инициализация основного экрана длиной и высотой
        screen = new Screen(width, height);
        //инициализация контент панели
        frame = new JFrame();
    }

    //старт основного потока
    public synchronized void start(){
        //игровой цикл может быть запущен
        running = true;
        //инициализация, и старт потока
        thread = new Thread(this, "Display");
        thread.start();
    }

    //остановка основного потока
    public synchronized void stop(){
        //игровой цикл не может быть запущен
        running = false;
        //попытка завершить поток
        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //стартует при запуске потока
    @Override
    public void run() {

        long lastTime = System.nanoTime();
        //таймер для счета каждой секунды
        long timer = System.currentTimeMillis();
        //здесь задается, сколько раз игровой состояние обновляется в секунду(не рендер!)
        final double ns = 1000000000.0 / 60.0;
        //разница между прошлым и предыдущим обновлением игрового состояния
        double delta = 0;
        //количество кадров в секунду
        int frames = 0;
        //количество апдейтов в секунду
        int updates = 0;
        //запускаем главный цикл
        while(running){
            //текущее время
            long now = System.nanoTime();
            //разница между текущим, и последним обновлением
            delta += (now - lastTime) / ns;
            //последнее становится текщим
            lastTime = now;
            //пока разница больше единицы
            while (delta >= 1){
                //обновляем игровые данные 60 раз в секунду
                update();
                //увеличиваем счетчик апдейта
                updates++;
                //уменьшаем счетчик дельты
                delta--;
            }
            //отрисовываем игровые данные
            render();
            //увеличиваем счетчик кадров в секунду
            frames++;

            //если прошла секунда
            if (System.currentTimeMillis() - timer > 1000){
                //увеличиваем таймер на 1000мс
                timer += 1000;
                //выводим данные на экран
                frame.setTitle(Game.title + " | " + updates + " ups, " + frames + " fps");
                //обнуляем данные, будут пересчитаны в следующем апдейте в течении секунды
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    //метод обновляющий игровое состояние(60 раз в секунду)
    public void update(){
        xOffset++;
        yOffset++;
    }

    //метод отрисовывающий картинку на экран(каждый кадр)
    public void render(){
        //буфер отрисовки, фронт, бэк буфер и тд
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            //создаем три буфера отрисовки
            createBufferStrategy(3);
            return;
        }
        //очищаем экран
        screen.clear();
        //отрисовка игрового экрана
        screen.render(xOffset, yOffset);
        //заполняем буфер изображения, буфером экрана
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        //контекст графики для отрисовки буферов
        Graphics g = bs.getDrawGraphics();
        //отрисовывает буфер изображения
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        //освобождаем ресурсы
        g.dispose();
        //показываем следующий доступный буфер
        bs.show();

    }

    //стартовый метод
    public static void main(String[] args) {
        //инициализация игры
        Game game = new Game();
        //запрещаем изменять размер экрана
        game.frame.setResizable(false);
        //установка заголовка
        game.frame.setTitle(Game.title);
        //добавляем на контент панель нашу игру
        game.frame.add(game);
        //устанавливает такой минимальный размер, чтобы в окно влезли все компоненты
        game.frame.pack();
        //при нажатии на крестик, закрываем окно
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //центрируем игровой окно
        game.frame.setLocationRelativeTo(null);
        //делаем окно видимым
        game.frame.setVisible(true);
        //старт игры
        game.start();
    }
}
