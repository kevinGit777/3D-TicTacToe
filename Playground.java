import java.util.ArrayList;
import java.util.List;

public class Playground {
    int[][][] ground;
    static int DEFAULE_SIZE = 3;
    boolean finished;
    int size;
    int emptySpace;
    List<int[]> wingin_rules;


    public Playground()
    {
        this(DEFAULE_SIZE);
    }

    public Playground(int size) {
        this.emptySpace = size*size*size;
        this.size = size;
        finished = false;
        ground = new int[size][size][size];
        for (int i = 0; i < size; i++) {
            ground[i] = new int[size][size];
            for (int j = 0; j < size; j++) {
                ground[i][j] = new int[size];
                for (int k = 0; k < size; k++) {
                    ground[i][j][k] = -1;
                }
            }
        }
        generateWiningRules();
    }

    private void generateWiningRules() {
        wingin_rules = new ArrayList<>(26);
        ArrayList<int[]> init = new ArrayList<>();
        
        init.add(new int[]{1,0});
        init.add(new int[]{0,1});
        for (int i = 0; i < 2; i++) {
            init.add( new int[]{init.get(i)[0] *-1, init.get(i)[1] * -1});
        }
        for (int i = 0; i < 4; i++) {
            for (int j = i+1; j < 4; j++) {
                int x = init.get(i)[0]+init.get(j)[0];
                int y = init.get(i)[1]+init.get(j)[1];
                if(x != 0 || y != 0)
                {
                    for (int z = -1; z < 2; z++) {
                        wingin_rules.add(new int[] {x ,y ,z});
                    }
                }
            }
        }
        wingin_rules.add(new int[]{0,0,1});
        wingin_rules.add(new int[]{0,0,-1});
    }

    

    public boolean checkValid(Position pos) {
        if(!checkInRange(pos))
            return false;
        if (getMark(pos)!= -1) {
            System.out.println("The position has been taken.");
            return false;
        }
        return true;
    }



    private boolean checkInRange(Position pos) {
        for (int num : pos.getPos()) {
            if(num <0 || num >= size)
                return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("Layer " +i+ "\n\tColumn0 \tColumn1 \t Column2");
            for (int j = 0; j < size; j++) {
                sb.append("Row "+ j+"\t");
                for (int k = 0; k < size; k++) {
                    String ch = null;
                    switch (getMark(new Position(k, j, i))) {
                        case -1:
                            ch = "E\t";
                            break;
                        case 0:
                            ch = "X\t";
                            break;
                        case 1:
                            ch = "O\t";
                            break;
                        default:
                            System.out.println("Error in playground value.");
                            break;
                    }
                    sb.append(ch+"\t");
                }
                sb.append("\n");
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }


    public boolean getFinish_status() {
        return finished;
    }

    public void putMark(Position pos, int cur_player) {
        ground[pos.getX()][pos.getY()][pos.getZ()] = cur_player;
        emptySpace--;
        if(emptySpace ==0)
            finished =true;
        finished = checkWin(pos, cur_player);
    }

    private boolean checkWin(Position pos, int cur_player) {
        for (int[] vector : wingin_rules) {
            if(getMark(movePosition(pos, vector, 1)) == cur_player &&
                (getMark(movePosition(pos, vector, 2) ) == cur_player // the end mark
                || getMark(movePosition(pos, vector, -1) ) == cur_player)) // the mid mark
            {
                return true;
            }
        }
        return false;
    }

    private Position movePosition(Position pos, int[] vec, int step)
    {
        return new Position(pos.getX()+vec[0]*step, 
            pos.getY()+vec[1]*step, pos.getZ()+vec[2]*step);
    }


    protected int getMark(Position pos) {
        if (!checkInRange(pos))
        {
            return -1;
        }
        return ground[pos.getX()][pos.getY()][pos.getZ()];
    }
}
