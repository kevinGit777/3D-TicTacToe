import java.util.Scanner;

public class User implements Player{

    @Override
    public Position move() {
        // TODO Auto-generated method stub
        System.out.println("Please enter a valid position to place the mark.");
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
        return null;
    }

    private String getUserInput() throws NumberFormatException{
        Scanner scanner_in = new Scanner(System.in);
        String str= scanner_in.nextLine();
        scanner_in.close();
        return str;
    }
    
}
