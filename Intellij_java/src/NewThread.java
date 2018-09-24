public class NewThread implements Runnable {
    String name;
    Thread t;

    NewThread(){
        t = new Thread(this,name);
        t.start();
    }

    public void run(){
        int k;
        for (k = 0; k < 8; k++) {
            Game.graphics.drawImage(Game.img[k], Game.cx, Game.cy, null);
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
