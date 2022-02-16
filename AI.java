import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/**
 * simple AI output random position on the playground
 * 
 * TODO:add difiicuty
 */
public class AI implements Player {
	int range;
	Random ran;
	int player_num;
	Playground game;
	String data_path;
	String file_keyword;
	char mark;

	public AI(Playground game, int player_num) {
		ran = new Random();
		range = game.size;
		this.player_num = player_num;
		this.game = game;
		data_path = "3d-tic-tac-toe.data.size" + range;
		if (player_num == 0) {
			file_keyword = "positive";
			mark = 'x';
		} else {
			file_keyword = "negative";
			mark = 'o';
		}
	}

	public Position RandomMove() {

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

	@Override
	public Position move() {
		// TODO add difiicuty by contral the ratio of random and minimax
		return miniMax();
	}

	private Position miniMax() {
		int[] points = new int[range * range * range];
		int maxPoint = Integer.MIN_VALUE;
		int maxindex = -1;
		try (BufferedReader br = new BufferedReader(new FileReader(data_path))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] dataGame = line.split(",");
				boolean match = (dataGame[range * range * range].compareTo(file_keyword) == 0);
				if (fitCurrentGame(line)) {
					for (int i = 0; i < range * range * range; i++) {
						int x = i % 3;
						int y = i % 9 / 3;
						int z = i / 9;
						char game_mark = game.getCharMark(new Position(x, y, z));
						if (game_mark == 'b' && dataGame[i].charAt(0) == mark && match) {
							points[i]++;
						}

					}
				}

			}
			
			

			br.close();
		} catch (Exception exception) {
			System.err.println("File reading error");
			exception.printStackTrace();

		}
		for (int i = 0; i < range * range * range; i++) {
			if (points[i] > maxPoint) {
				maxindex = i;
				maxPoint = points[i];
			}
			System.out.println(String.format("Point at index%d is%d.", i, points[i]));

		}
		return new Position(maxindex % 3, maxindex % 9 / 3, maxindex / 9);
	}

	private boolean fitCurrentGame(String line) {
		// System.out.println("Fitting Game");

		String[] dataGame = line.split(",");
		for (int i = 0; i < range * range * range; i++) {
			int x = i % 3;
			int y = i % 9 / 3;
			int z = i / 9;
			char game_mark = game.getCharMark(new Position(x, y, z));
			if (game_mark == 'b')
				continue;
			if (dataGame[i].charAt(0) != game_mark) {
				return false;
			}
		}

		return true;
	}

	private int countPoint() {
		int point = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(data_path))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] dataGame = line.split(",");

			}
		} catch (Exception exception) {
			System.err.println("File reading error");
			exception.printStackTrace();
		}

		return point;
	}

}
