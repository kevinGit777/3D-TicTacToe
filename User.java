import java.util.Scanner;

public class User implements Player{
    Scanner scanner_in = new Scanner(System.in);
    @Override
    public Position move() {
        System.out.println("Please enter a valid position to place the mark.");
        while (true) {
            try {
                System.out.println("Which layer you want to put a mark?");
                int z = Integer.parseInt(getUserInput());
                System.out.println("Which row you want to put a mark?");
                int y = Integer.parseInt(getUserInput());
                System.out.println("Which column you want to put a mark?");
                int x = Integer.parseInt(getUserInput());
                return new Position(x,y,z);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    private String getUserInput() throws NumberFormatException{
        
        String str= scanner_in.nextLine();
        
        return str;
    }

    @Override
    public void close() {
        scanner_in.close();
    }
    
}
