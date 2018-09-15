import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.KeyListener;
import java.util.Random;


public class Game extends JFrame implements KeyListener {

    static int cx = 100;
    static int cy = 140;

    static int bx1 = 0;
    static int bx2 = 1000;
    static int by = 0;

    static int i;

    static int[] obsy = new int[6];
    static int[] obsx = new int[6];
    static int m;

    static Graphics graphics;

    static Image bg1 = null;
    static Image handle = null;
    static Image gameover = null;
    static int score;

    static Image[] img = new Image[8];
    static boolean b=false;
    static boolean bol=false;
    static boolean first=true;

    static int yVel=5;


    Game() {
        this.addKeyListener(this);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(1000, 500));
        f.add(p);
        f.setTitle("PAPPU PAKIA");

        Game game = new Game();
        f.addKeyListener(game);

        f.pack();
        f.setVisible(true);
        f.setResizable(false);
        f.setFocusable(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setLayout(null);

        graphics = p.getGraphics();


        try {
            img[0] = ImageIO.read(Game.class.getClassLoader().getResource("images/pappu1.png"));
            img[1] = ImageIO.read(Game.class.getClassLoader().getResource("images/pappu2.png"));
            img[2] = ImageIO.read(Game.class.getClassLoader().getResource("images/pappu3.png"));
            img[3] = ImageIO.read(Game.class.getClassLoader().getResource("images/pappu4.png"));
            img[4] = ImageIO.read(Game.class.getClassLoader().getResource("images/pappu5.png"));
            img[5] = ImageIO.read(Game.class.getClassLoader().getResource("images/pappu6.png"));
            img[6] = ImageIO.read(Game.class.getClassLoader().getResource("images/pappu7.png"));
            img[7] = ImageIO.read(Game.class.getClassLoader().getResource("images/pappu8.png"));
            bg1 = ImageIO.read(Game.class.getClassLoader().getResource("images/gamebg.png"));
            handle = ImageIO.read(Game.class.getClassLoader().getResource("images/handle.png"));
            gameover = ImageIO.read(Game.class.getClassLoader().getResource("images/gameover.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        int h=0;
        for (int g = 0; g < 2; g++) {
            obsx[g] = 1000 + h;
            h += 500;
            Random rd = new Random();

            obsy[g] = rd.nextInt(313) + 188;
        }


        graphics.drawImage(bg1, bx1, by, null);

        int k;
        while (first) {

            for (k = 0; k < 8; k++) {
                graphics.drawImage(img[k], cx, cy, null);
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
               // graphics.clearRect(cx, cy, 60, 60);
                graphics.drawImage(bg1, bx1, by, null);

                if(b){
                    //System.out.println("main");
                    cy+=yVel;
                    obstacles();

                    if(bol) {
                        first = false;
                        graphics.drawImage(gameover, 155, 0, null);
                        System.out.println("Score:"+score);

                    }
                }
            }
        }


    }//end main


    @Override
    public void keyTyped(KeyEvent e) {
        // not required
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            b=true;
            yVel=-5;
            int k = 4;
            while (k >= 0) {
                cy = cy + yVel -k;
                k = k - 1;
                try {
                    Thread.sleep(45);
                } catch (Exception y) {
                    y.printStackTrace();
                }
                i++;
                if (i == 8)
                    i = 0;
                graphics.drawImage(img[i], cx, cy, null);
                graphics.drawImage(bg1, bx1, bx2, null);
            }
            yVel = 5;
        }
    }




    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void obstacles() {

            if (bx1 == -1000)
                bx1 = 1000;
            if (bx2 == -1000)
                bx2 = 1000;
            bx1-=2;
            bx2-=2;
            graphics.drawImage(bg1, bx1, by, null);
            graphics.drawImage(bg1, bx2, by, null);


            Random rd = new Random();

            for(int g=0;g<2;g++) {

                if (obsx[g] <= 0) {
                    score++;
                    obsx[g] = 1000;
                    obsy[g] = rd.nextInt(313) + 188;
                }
                obsx[g]-=4;
            }

            for(m=0;m<2;m++){

               if(obsy[m]<450)
                graphics.drawImage(handle, obsx[m], obsy[m], null);
                //System.out.println("obstacle");
                if(obsy[m]<420) {
                    graphics.drawImage(handle, obsx[m], obsy[m] - 452, null);
                    check(obsy[m] - 140);
                }
                else {
                    graphics.drawImage(handle, obsx[m], 0, null);
                    check(312);
                }
            }

    }

    static void check(int ul){
        int r;
        if(cy < -50 || cy > 550)
            bol = true;
        for(r=0;r<4;r++) {
            if (cx + 30 + r == obsx[m] && cy <= ul)
                bol = true;
            if(bol)
                break;


            if (cx + 30 + r == obsx[m] && cy + 60 >= obsy[m])
                if (obsy[m] < 450)
                    bol = true;
                if(bol)
                    break;

        }
    }


}


