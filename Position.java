public class Position {
    int [] pos;
    
    public Position(int x, int y, int z) {
        this.pos[0] = x;
        this.pos[1] = y;
        this.pos[2] = z;
    }

    public int[] getPos() {
        return this.pos;
    }

    public int getX() {
       return pos[0]; 
    }

    public int getY() {
        return pos[1]; 
     }

     public int getZ() {
        return pos[2]; 
     }
}
