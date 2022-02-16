import static java.lang.System.currentTimeMillis;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.*;


public class DataGenerator {
	int size;
	int[] winCounts;
	Playground game;
	Path[] files;
	String[] labelStrings;
	long start_time;
	
	public static void main(String[] args) {
		DataGenerator dataGenerator =new DataGenerator(3);
		dataGenerator.printSummary();
	}
	
	
	public DataGenerator(int size)
	{
		start_time = currentTimeMillis();
		this.size =size;
		winCounts = new int[2];
		files = new Path[2];
		files[0] = FileSystems.getDefault().getPath("3d-tic-tac-toe.x.data");
		files[1] = FileSystems.getDefault().getPath("3d-tic-tac-toe.o.data");
		//labelStrings = new String[]{"positive","negative" };
		game = new Playground(size);
		GenerateData(0,0,0,0);
		
	}
	
	private void printSummary() {
		// TODO Auto-generated method stub
		long end_time = currentTimeMillis();
		System.out.println("X wins " + winCounts[0]+" time, O wins"+ winCounts[1]+" times. Total takes "+ (end_time - start_time) + " ms." );
		
	}

	private void GenerateData(int x, int y, int z, int cur_player) {
		if(x==size)
		{
			 x=0;
			 y++;
			 if (y == size) {
				y=0;
				z++;
				if(z == size)
				{
					return;
				}
			 }
		}
		
		Position pos = new Position(x, y, z);
		//System.out.println("Current at cell ["+x+", "+y+", "+z+"]");
		game.putMark(pos, cur_player);
		if (game.checkWin(pos, cur_player))
		{
			playerWin(cur_player);
			game.finished =false;
		}else {
			GenerateData(x+1, y, z, (cur_player+1)%2); //move
		}
		game.deleteMark(pos);
		//System.out.println("DeleteMark at cell ["+x+", "+y+", "+z+"]");
		GenerateData(x+1, y, z, cur_player); // as blank
	}

	private void playerWin(int cur_player) {
		//System.out.println("Player "+cur_player+" won!");
		winCounts[cur_player]++;
		StringBuilder sb =new StringBuilder();
		gameToString(sb);
		String str_pos = sb.toString()+"positive\n";
		String str_neg = sb.toString()+"negative\n";
		if(cur_player == 0)
		{
			writeToFile(str_pos, files[0]);
			writeToFile(str_neg, files[1]);
		}else {
			writeToFile(str_pos, files[1]);
			writeToFile(str_neg, files[0]);
		}
		
	}

	/**
	 * @param str_pos
	 * @param file 
	 */
	private void writeToFile(String str, Path file) {
		try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
		    writer.write(str, 0, str.length());
		    writer.close();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}

	/**
	 * @param sb
	 */
	private void gameToString(StringBuilder sb) {
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    String ch = null;
                    switch (game.getMark(new Position(k, j, i))) {
                        case -1:
                            ch = "b,";
                            break;
                        case 0:
                            ch = "x,";
                            break;
                        case 1:
                            ch = "o,";
                            break;
                        default:
                            System.out.println("Error in playground value.");
                            break;
                    }
                    sb.append(ch);
                }
            }
        }
	}

	
	
	
}
