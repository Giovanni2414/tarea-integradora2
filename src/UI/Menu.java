package UI;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.IOException;

import Model.MainGame;

public class Menu {
	
	MainGame gm;
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public Menu() {
		gm = new MainGame();
	}
	
	public void startMenu() throws IOException {
		int option = 0;
		do {
			String msg = "Choose an option\n(1) New game\n(2) Move around the tab\n(3) Find mirror\nOption: ";
			writeText(msg);
			option = Integer.parseInt(rd.readLine());
			switch(option) {
				case 1:
					newGame();
					break;
				case 2:
					makeMovement();
					break;
				case 3:
					findMirror();
					break;
			}
		} while(option != 0);
	}
	
	private void writeText(String msg) throws IOException {
		wr.write(msg);
		wr.flush();
	}
	
	private void newGame() throws IOException {
		String msg = "Enter your name, n x m panel and k mirrors\n";
		writeText(msg);
		String[] partition = rd.readLine().split(" ");
		writeText(gm.startNewGame(Integer.parseInt(partition[1]), Integer.parseInt(partition[2]), partition[0], Integer.parseInt(partition[3])));
		writeText(gm.printGame(null, 0, 0));
	}
	
	private void makeMovement() throws IOException {
		String msg = "Enter the codification to make a movement (Example: 1E): ";
		writeText(msg);
		String mov = rd.readLine();
		writeText(gm.makeMovement(mov));
		writeText(gm.printInfoGame());
	}
	
	private void findMirror() throws IOException {
		String msg = "Enter the codification to find a mirror (Example: L1ER): ";
		writeText(msg);
		String loc = rd.readLine();
		writeText(gm.makeMovement(loc));
	}
	
}
