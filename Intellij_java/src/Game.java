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
    static int h;

    static Graphics graphics;

    static Image bg1 = null;
    static Image handle = null;

    static Image[] img = new Image[8];
    static boolean b;


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
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
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

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            bg1 = ImageIO.read(Game.class.getClassLoader().getResource("images/gamebg.png"));
            handle = ImageIO.read(Game.class.getClassLoader().getResource("images/handle.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        int h = 0;
        for (int g = 0; g < 3; g++) {
            obsx[g] = 1000 + h;
            h += 500;
            Random rd = new Random();

            obsy[g] = rd.nextInt(313) + 188;
        }


        graphics.drawImage(bg1, bx1, by, null);

        int k;
        while (true) {
            for (k = 0; k < 8; k++) {
                graphics.drawImage(img[k], cx, cy, null);
                try {
                    Thread.sleep(80);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                graphics.clearRect(cx, cy, 60, 60);
                graphics.drawImage(bg1, bx1, by, null);

            }
        }


    }//end main


    @Override
    public void keyTyped(KeyEvent e) {
        // not required
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("this is keyPressed");
        int code = e.getKeyCode();
        //  b=true;
        if (code == KeyEvent.VK_SPACE) {
            int k = 8;
            while (k > 0) {
                cy = cy - k;
                k = k - 2;
                if (i == 8)
                    i = 0;
                try {
                    Thread.sleep(45);
                } catch (Exception y) {
                    y.printStackTrace();
                }
                graphics.drawImage(img[i], cx, cy, null);
                graphics.drawImage(bg1, bx1, bx2, null);
            }

        }
    }




    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released");
        // b = false;
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {

            print();
        }
    }

    public void print() {
        // int h = 0;
        while (true) {
            // cy =+h;
            //   h= h+2;
            // gravity();
            if (bx1 == -1000)
                bx1 = 1000;
            if (bx2 == -1000)
                bx2 = 1000;
            bx1--;
            bx2--;
            graphics.drawImage(bg1, bx1, by, null);
            graphics.drawImage(bg1, bx2, by, null);

            graphics.drawImage(img[i], cx, cy, null);



            if(m==6)
                m=0;

            int g;
            Random rd = new Random();

            for(g=0;g<2;g++) {
                if (obsx[g] == 0) {
                    obsx[g] = 1000;
                    obsy[g] = rd.nextInt(313) + 188;
                }
                obsx[g]--;
            }

            for(m=0;m<2;m++){
                graphics.drawImage(handle, obsx[m], obsy[m], null);
                if(obsy[m]<425)
                    graphics.drawImage(handle, obsx[m], obsy[m] - 425, null);
                else
                    graphics.drawImage(handle, obsx[m],0 , null);

            }

        }
    }
    /*public void gravity(){
            if(b){
                h=0;
            }
            cx=cx+h;
            h += 2;
    }*/


}


