import java.util.Scanner;
/**
 * Main
 */
public class Main {
    static Scanner scanner_in = new Scanner(System.in);
    public static void main(String[] args) {
        //create playground
        boolean ending = false;
        
        while (!ending) {
            Playground game = createPlayground();
            Player[] players = initialPlayer(game);
            int cur_player =-1;
            Position pos;

            while (!game.getFinish_status()) {
                cur_player = (cur_player+1) % 2;
                System.out.println(game.toString());
                do {
                    pos = players[cur_player].move();
                } while (game.checkValid(pos));
                game.putMark(pos, cur_player);
                
            }
            ending = finishGame(cur_player);
            players[0].close();
        }
        
        scanner_in.close();
       
    }
    
    private static boolean finishGame(int cur_player) {
        System.out.println("Player "+cur_player+" won!");
        System.out.println("Would you like to play again?(y/n)");
        String ans = getUserInput();
        return ans.charAt(0) == 'y';
    }

    private static Player[] initialPlayer(Playground game) {
        Player[] players = new Player[2];
        players[1] = new AI(game);
        System.out.println("Would you want to play?(y/n)");
        String ans = getUserInput();
        if(ans.charAt(0) == 'y')
        {
            players[0] = new User();
        }else{
            players[0] = new AI(game);
        }
        return players;
    }

    private static Playground createPlayground() {
        System.out.println("How big do you want to playground to be? (Default and minimum is 3)");
        int playground_size = 0;
            try {
                playground_size = Integer.parseInt(getUserInput()); 
            } catch (NumberFormatException e) {
                System.out.println("Invalid size! Using default size");
                return new Playground();
            }
            return new Playground(playground_size);
    }

    private static String getUserInput() throws NumberFormatException{
        String str= scanner_in.nextLine();
        return str;
    }
}