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
		if(String.valueOf(mov.charAt(0)).equalsIgnoreCase("L")) {
			
		} else if(String.valueOf(mov.charAt(mov.length()-1)).equalsIgnoreCase("H") || String.valueOf(mov.charAt(mov.length()-1)).equalsIgnoreCase("V")) {
			
		} else {
			int m = mov.charAt(mov.length()-1) - 64;
			String prevN = getNumbersFromMov(mov, 0, "");
			int n = Integer.parseInt(prevN.substring(0, mov.length()-1));
			System.out.println(n + " " + m);
			Panel enterPanel = searchPanel(firstPanel, n, m);
			enterPanel.entrance = true;
			Panel outPanel = managementMovements(enterPanel, n, m);
			outPanel.out = true;
			printGame(firstPanel, n, m);
		}
		return msg;
	}
	
	private String getNumbersFromMov(String mov, int n, String num) {
		if(n < mov.length()-1) {
			num = String.valueOf(mov.charAt(n));
			num += getNumbersFromMov(mov, n+1, num);
		}
		return num;
	}
	
	private Panel searchPanel(Panel miracle, int n, int m) {
		if(n > 0) {
			miracle = searchPanel(miracle.getRightPanel(), n-1, m);
		} else {
			miracle = searchPanel2(miracle, m);
		}
		return miracle;
	}
	
	private Panel searchPanel2(Panel miracle, int m) {
		if(m > 0) {
			miracle = searchPanel2(miracle.getLowerPanel(), m-1);
		}
		return miracle;
	}
	
	private Panel managementMovements(Panel miracle, int n, int m) {
		if(n == 1) {
			if(!miracle.getMirror().equals("N")) {
				if((miracle.getMirror()).equals("L")) {
					miracle = moveRight(miracle);
				} else if((miracle.getMirror()).equals("R")) {
					miracle = moveLeft(miracle);
				}
			} else {
				miracle = moveDown(miracle);
			}
		} else if(n == this.n) {
			if(!miracle.getMirror().equals("N")) {
				if((miracle.getMirror()).equals("L")) {
					miracle = moveLeft(miracle);
				} else if((miracle.getMirror()).equals("R")) {
					miracle = moveRight(miracle);
				}
			} else {
				miracle = moveUp(miracle);
			}
		} else if(m == 1) {
			if(!miracle.getMirror().equals("N")) {
				if((miracle.getMirror()).equals("L")) {
					miracle = moveDown(miracle);
				} else if((miracle.getMirror()).equals("R")) {
					miracle = moveUp(miracle);
				}
			} else {
				miracle = moveRight(miracle);
			}
		} else if(m == this.m) {
			if(!miracle.getMirror().equals("N")) {
				if((miracle.getMirror()).equals("L")) {
					miracle = moveUp(miracle);
				} else if((miracle.getMirror()).equals("R")) {
					miracle = moveDown(miracle);
				}
			} else {
				miracle = moveLeft(miracle);
			}
		}
		return miracle;
	}
	
	private Panel managementMovementsCorners(Panel miracle, int n, int m, String dir) {
		
		return miracle;
	}
	
	private Panel moveDown(Panel miracle) {
		if(miracle.getLowerPanel() != null) {
			if((miracle.getLowerPanel().getMirror()).equals("N")) {
				miracle = moveDown(miracle.getLowerPanel());
			} else if((miracle.getLowerPanel().getMirror()).equals("L")) {
				miracle = moveRight(miracle.getLowerPanel());
			} else {
				miracle = moveLeft(miracle.getLowerPanel());
			}
		}
		return miracle;
	}
	
	private Panel moveRight(Panel miracle) {
		if(miracle.getRightPanel() != null) {
			if((miracle.getRightPanel().getMirror()).equals("N")) {
				miracle = moveRight(miracle.getRightPanel());
			} else if((miracle.getRightPanel().getMirror()).equals("L")) {
				miracle = moveDown(miracle.getRightPanel());
			} else {
				miracle = moveUp(miracle.getRightPanel());
			}
		}
		return miracle;
	}
	
	private Panel moveUp(Panel miracle) {
		if(miracle.getHigherPanel() != null) {
			if((miracle.getHigherPanel().getMirror()).equals("N")) {
				miracle = moveUp(miracle.getHigherPanel());
			} else if((miracle.getHigherPanel().getMirror()).equals("L")) {
				miracle = moveLeft(miracle.getHigherPanel());
			} else {
				miracle = moveRight(miracle.getHigherPanel());
			}
		}
		return miracle;
	}
	
	private Panel moveLeft(Panel miracle) {
		if(miracle.getLeftPanel() != null) {
			if((miracle.getLeftPanel().getMirror()).equals("N")) {
				System.out.println("Me moví");
				miracle = moveLeft(miracle.getLeftPanel());
			} else if((miracle.getLeftPanel().getMirror()).equals("L")) {
				miracle = moveUp(miracle.getLeftPanel());
			} else {
				miracle = moveDown(miracle.getLeftPanel());
			}
		}
		return miracle;
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
			System.out.print(miracle.getMirrorView());
			printHelper(miracle.getRightPanel(), m-1);
		}
	}
	
	private void setRandomMirrors(Panel miracle, int k) {
		if(k > 0) {
			int n = (int) Math.floor(Math.random() * this.n);
			int m = (int) Math.floor(Math.random() * this.m);
			Panel tempForSetMirror = setRandomMirrorsHelper1(miracle, n, m);
			if(tempForSetMirror.getMirror().equalsIgnoreCase("N")) {
				int c = (int) Math.floor(Math.random()*3);
				if(c % 2 == 0) {
					tempForSetMirror.setMirror("L");
				} else {
					tempForSetMirror.setMirror("R");
				}
				setRandomMirrors(miracle, k-1);
			} else {
				setRandomMirrors(miracle, k);
			}
		}
	}
	
	private Panel setRandomMirrorsHelper1(Panel miracle, int n, int m) {
		Panel box = null;
		if(n > 0) {
			box = setRandomMirrorsHelper1(miracle.getLowerPanel(), n-1, m);
		} else {
			return setRandomMirrorsHelper2(miracle, m);
		}
		return box;
	}
	
	private Panel setRandomMirrorsHelper2(Panel miracle, int m) {
		Panel box = null;
		if(m > 0) {
			box = setRandomMirrorsHelper2(miracle.getRightPanel(), m-1);
		} else {
			return miracle;
		}
		return box;
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
		if(m > 1) {
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
	
	private int getRemainingMirrors() {
		return this.k;
	}
	
	private void findedMirror() {
		this.k--;
	}
	
}
