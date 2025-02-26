

import javax.swing.*;


public class Main {
    public static void main(String[] args) throws Exception
    {
        int frame_h,frame_w;
        frame_h = frame_w = 600;
        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(frame_h,frame_w);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game snake_game = new Game();
        snake_game.startGame(frame_h, frame_w);
        frame.add(snake_game);
        frame.pack();
        snake_game.requestFocus();
    }
}