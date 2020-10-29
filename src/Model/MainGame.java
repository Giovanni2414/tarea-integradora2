package Model;

public class MainGame {
	
	private Panel firstPanel;
	private int n;
	private int m;
	private int k;
	Player player;
	Player rootBinaryTreePlayers;
	
	public String startNewGame(int n, int m, String name, int k) {
		this.n = n;
		this.m = m;
		this.k = k;
		player = new Player(name);
		this.firstPanel = new Panel();
		createVerticalPanels(n, m, firstPanel);
		createVerticalRelations(firstPanel, n, m);
		setRandomMirrors(firstPanel, k);
		return "New game created for " + player.getName() + " " + k + " mirrors remaining\n";
	}
	
	public String makeMovement(String mov) {
		String msg = "";
		if(mov.length() == 2) {
			msg += 69 - mov.charAt(1);
		} else if (mov.length() == 3) {
			
		} else {
			msg = "You has entered a invalid codification of Movement\n";
		}
		return msg;
	}
	
	public String printGame(Panel miracle, int n, int m) {
		if(miracle == null) {
			miracle = this.firstPanel;
			n = this.n;
			m = this.m;
		}
		if(n > 0) {
			printHelper(miracle, m);
			System.out.println("");
			printGame(miracle.getLowerPanel(), n-1, m);
		}
		return "";
	}

	//Possible method
	public void printHelper(Panel miracle, int m) {
		if(m > 0) {
			System.out.print(miracle.getWithMirror());
			printHelper(miracle.getRightPanel(), m-1);
		}
	}
	
	private void setRandomMirrors(Panel miracle, int k) {
		if(k > 0) {
			int n = (int) Math.floor(Math.random() * this.n);
			int m = (int) Math.floor(Math.random() * this.m);
			setRandomMirrorsHelper1(miracle, n, m);
			setRandomMirrors(miracle, k-1);
		}
	}
	
	private void setRandomMirrorsHelper1(Panel miracle, int n, int m) {
		if(n > 0) {
			setRandomMirrorsHelper1(miracle.getLowerPanel(), n-1, m);
		} else {
			setRandomMirrorsHelper2(miracle, m);
		}
	}
	
	private void setRandomMirrorsHelper2(Panel miracle, int m) {
		if(m > 0) {
			setRandomMirrorsHelper2(miracle.getRightPanel(), m-1);
		} else {
			int n = (int) Math.floor(Math.random()*3);
			if(n % 2 == 0) {
				miracle.setMirror("L");
			} else {
				miracle.setMirror("R");
			}
		}
	}
	
	private void createVerticalPanels(int n, int m, Panel miracle) {
		if(n > 0) {
			createHorizontalPanels(m, miracle);
			miracle.setLowerPanel(new Panel());
			(miracle.getLowerPanel()).setHigherPanel(miracle);
			createVerticalPanels(n-1, m, miracle.getLowerPanel());
		}
	}
	
	private void createHorizontalPanels(int m, Panel miracle) {
		if(m > 0) {
			miracle.setRightPanel(new Panel());
			(miracle.getRightPanel()).setLeftPanel(miracle);
			createHorizontalPanels(m-1, miracle.getRightPanel());
		}
	}
	
	private void createVerticalRelations(Panel miracle, int n, int m) {
		if (m > 1) {
			Panel secondMiracle = miracle.getLowerPanel();
			createVerticalRelationsExtension(miracle, secondMiracle, n);
			createVerticalRelations(secondMiracle, n, m-1);
		}
	}
	
	private void createVerticalRelationsExtension(Panel miracle, Panel secondMiracle, int n) {
		if(n > 0) {
			miracle.setLowerPanel(secondMiracle);
			secondMiracle.setHigherPanel(miracle);
			createVerticalRelationsExtension(miracle.getRightPanel(), secondMiracle.getRightPanel(), n-1);
		}
	}
	
}
