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
			String msg = "Choose an option\n(1) New game\n(2) Show players scores\n(3) Exit program\nOption: ";
			writeText(msg);
			option = Integer.parseInt(rd.readLine());
			switch(option) {
				case 1:
					newGame();
					break;
				case 2:
					showScores();
					break;
				case 3:
					writeText("Good bye!");
					break;
			}
		} while(option != 0);
	}
	
	private void showScores() throws IOException {
		writeText(gm.socreInOrden());
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
		String status = "InGame";
		do {
			status = makeMovement();
		} while(!status.equals("menu"));
	}
	
	private String makeMovement() throws IOException {
		String msg = "Enter the codification to make a movement (Example: 1E, L1ER or 'Menu'): ";
		writeText(msg);
		String mov = rd.readLine();
		writeText(gm.makeMovement(mov));
		return mov;
	}
	
}
