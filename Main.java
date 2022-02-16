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
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Player "+cur_player+"'s turn:");
                System.out.println(game.toString());
                do {
                    pos = players[cur_player].move();
                } while (!game.checkValid(pos));
                game.putMark(pos, cur_player);
                System.out.println("Player "+cur_player+" put mark on "+"Layer "+pos.getZ()+", Row "+pos.getY() +", Column"+pos.getX());
            }
            ending = finishGame(cur_player, game.checkDraw());
            if(ending)
            	closeGame(players);
        }
       
    }
    
    
    
    private static void closeGame(Player[] players) {
		// TODO Auto-generated method stub
    	for (Player player : players) {
    		player.close();
		}
    	scanner_in.close();
	}

	private static boolean finishGame(int cur_player, boolean draw) {
		if (draw) {
			System.out.println("GG! Draw!");
		}else {
			System.out.println("Player "+cur_player+" won!");
		}
        System.out.println("Would you like to play again?(y/n)");
        String ans = getUserInput();
        return !(ans.charAt(0) == 'y');
    }

    private static Player[] initialPlayer(Playground game) {
        Player[] players = new Player[2];
        //players[1] = new AI(game);
        System.out.println("Would you want to play as Player 0?(y/n)");
        String ans = getUserInput();
        if(ans.charAt(0) == 'y') //X marks
        {
            players[0] = new User();
        }else{
            players[0] = new AI(game, 0);
        }
        System.out.println("Would you want to play as Player 1?(y/n)");
        ans = getUserInput();
        if(ans.charAt(0) == 'y') // O marks
        {
            players[1] = new User();
        }else{
            players[1] = new AI(game, 1);
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