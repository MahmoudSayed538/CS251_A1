import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;


class Snake{
    int size;
    int x;
    int y;

   //set starting position and size

    Snake (int x, int y , int size)
    {
        this.x = x ;
        this.y = y ;
        this.size = size;
    }
}


public class Game extends JPanel implements ActionListener, KeyListener{
   int board_w;
   int board_h;
   //Snake
   Snake snake;
   ArrayList<Snake> snake_body;

   //Food

   Snake food;
   Random foodPosition;

   //logic

   Timer game_loop;
   int velocityX;
   int velocityY;
   boolean game_over = false;

    public void startGame(int board_h,int board_w)
    {
        this.board_h = board_h;
        this.board_w = board_w;
        setPreferredSize(new Dimension(this.board_w,this.board_h));
        setBackground(Color.black);
        setBorder(new LineBorder(Color.green, 3));
        addKeyListener(this);
        setFocusable(true);
        snake = new Snake(5,5,25);
        snake_body = new ArrayList<Snake>();
        food = new Snake(10,10,25);
        foodPosition = new Random();
        putFood();
        velocityX = 0 ;
        velocityY = 0 ;
        game_loop = new Timer(100,this);
        game_loop.start();
  
    }
  

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Snake Head

        g.setColor(Color.green);
        g.fill3DRect(snake.x * snake.size,snake.y *snake.size,snake.size,snake.size,true);

        //Food

        g.setColor(Color.red);
        g.fill3DRect(food.x * food.size, food.y * food.size, food.size, food.size,true);

        //Snake Body
        g.setColor(Color.blue);
        for(Snake unit : snake_body)
        {
            g.fill3DRect(unit.x * unit.size, unit.y * unit.size, unit.size, unit.size,true);

        }

        //Score

        g.setFont(new Font("Arial",Font.BOLD, 16));
        if(game_over){
            g.setColor(Color.red);
            g.drawString("Game Over\n Score : " + String.valueOf(snake_body.size()),snake.size - 16 ,snake.size);
        }else {
            g.drawString("Score: "+ String.valueOf(snake_body.size()), snake.size-16, snake.size);
        }

    }   
        

    public void putFood()
    {
        food.x = foodPosition.nextInt(board_w/food.size);
        food.y = foodPosition.nextInt(board_h/food.size - 2); // making sure the food always lands within the frame height
    }
    public void move () {
        if(yummy(snake,food))
        {
            snake_body.add(new Snake(food.x, food.y, food.size));
            putFood();
        }

        //The Body

        for(int i = snake_body.size()-1 ; i >= 0 ; i--)
        {
            Snake snake_part = snake_body.get(i);
            if(i == 0)
            {
                snake_part.x = snake.x;
                snake_part.y = snake.y;
            }else
            {
                Snake prev_part = snake_body.get(i-1);
                snake_part.x = prev_part.x;
                snake_part.y = prev_part.y;
            }
        }

        //The Head

        snake.x += velocityX;
        snake.y += velocityY;

        //game over
        
        for(Snake i : snake_body)
        {
            if(yummy(snake, i)){
                game_over = true;
            }
        }
        if(snake.x*snake.size < 0 || snake.x *snake.size >= board_w ||
           snake.y*snake.size < 0 || snake.y *snake.size >= board_h - snake.size - snake.size)
           {
                game_over=true;
           }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(game_over)
        {
            game_loop.stop();
            int option = JOptionPane.showConfirmDialog(this, "Game Over! Play Again?", "Snake Game",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) 
            {    game_over = false;
                 startGame(this.board_h,this.board_w); // Restart game
            } else 
            {
                System.exit(0); // Close the game
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_UP && velocityY != 1)
        {
            velocityX = 0;
            velocityY = -1;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1)
        {
            velocityX = 0;
            velocityY = 1;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1)
        {
            velocityX = -1;
            velocityY = 0;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1)
        {
            velocityX = 1;
            velocityY = 0;
        }
    }

    public boolean yummy(Snake snake, Snake food)
    {
        return snake.x == food.x && snake.y == food.y ;
    }
    // I don't need those but for some reason they need to be defined
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
 
}
