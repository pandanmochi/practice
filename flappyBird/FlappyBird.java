package flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird implements ActionListener, MouseListener {

    public static FlappyBird flappyBird;
    public Renderer renderer;
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private int ticks;
    private int yMotion;
    private int score;
    private boolean gameOver;
    private boolean started;
    public Rectangle bird;
    public ArrayList<Rectangle> columns;
    public Random random;

    public FlappyBird()
    {
        JFrame gameFrame = new JFrame("Flappy Bird");
        Timer timer = new Timer(20, this);

        renderer = new Renderer();
        gameFrame.add(renderer);

        random = new Random();

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program will be terminated when closing window
        gameFrame.setVisible(true);
        gameFrame.setResizable(true); //window frame not resizable by user
        gameFrame.setSize(WIDTH, HEIGHT);

        gameFrame.addMouseListener(this);

        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT/ 2 - 10, 20 ,20);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }


    public static void main(String[] args)
    {
        flappyBird = new FlappyBird();
    }

    public void addColumn(boolean start)
    {
        int space = 300;
        int width = 100;
        int height = 50 + random.nextInt(300);

        if (start)
        {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 150, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        else
        {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 150, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }

    public void paintColumn(Graphics g, Rectangle column)
    {
        g.setColor(Color.BLACK);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void repaint(Graphics g)
    {
        g.setColor(Color.WHITE); //set game background white
        g.fillRect(0, 0, WIDTH, HEIGHT); //fill background

        g.setColor( Color.BLACK);
        g.fillRect(0, HEIGHT-150, WIDTH, 150);

        g.setColor(Color.BLACK);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for(Rectangle column : columns)
        {
            paintColumn(g, column);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", 1, 50));
        if(gameOver)
        {
            g.drawString("Game Over", 250, HEIGHT / 2 - 50);
            g.drawString("Score: " + score, 250, HEIGHT / 2);
        }
        else if(!started)
        {
            g.drawString("click to start", 250, HEIGHT / 2 - 50);

        }

        if (!gameOver && started)
        {
            g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
        }
    }

    private void jump()
    {
        if(gameOver)
        {
            score = 0;
            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT/ 2 - 10, 20 ,20);
            columns.clear();
            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }
        if(!started)
        {
            started = true;
        }
        if(yMotion > 0)
        {
            yMotion = 0;
        }
        yMotion -=10;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int speed = 10; // gamespeed
        ticks++;

        if(started)
        {
            for(int i = 0;i<columns.size();i++)
            {
                Rectangle column = columns.get(i);
                column.x -= speed;
            }

            if(ticks%2==0 && yMotion < 15)
            {
                yMotion += 2;
            }

            for(int i = 0;i<columns.size();i++)
            {
                Rectangle column = columns.get(i);
                if(column.x + column.width < 0) //if column left frame
                {
                    columns.remove(column); //remove column from the list
                    if(column.y == 0) //true for every top column
                    {
                        addColumn(false); //game proceeds to add columns
                    }
                }
            }

            bird.y += yMotion; // making the bird fall down

            for(Rectangle column : columns)
            {
                if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 5 && bird.x + bird.width / 2 < column.x + column.width / 2 + 5)
                {
                    score++;
                }

                if(column.intersects(bird))
                {
                    gameOver = true;
                    bird.x = column.x - bird.width;
                }
            }

            if(bird.y > HEIGHT - 150 - bird.height || bird.y == 0)
            {
                gameOver = true;
                bird.y = HEIGHT - 150 - bird.height;
            }

            if(bird.y + yMotion >= HEIGHT - 150)
            {
                bird.y = HEIGHT - 150 - bird.height;
            }
        }

        renderer.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jump();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
