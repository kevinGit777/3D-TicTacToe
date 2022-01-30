import java.util.Random;
/**
 * simple AI output random position on the playground
 * 
 * 
 */
public class AI implements Player {
    int range;
    Random ran;
    public AI(Playground game)
    {
        ran = new Random();
        range = game.size;
    }

    @Override
    public Position move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Position(ran.nextInt(range), ran.nextInt(range), ran.nextInt(range));        
    }

    @Override
    public void close() {
        return;
    }
    
}
