package Model;

public class Panel {

	Panel higherPanel;
	Panel lowerPanel;
	Panel rightPanel;
	Panel leftPanel;
	String mirror;
	
	public Panel() {
		higherPanel = null;
		lowerPanel = null;
		rightPanel = null;
		leftPanel = null;
		mirror = "N";
	}
	
	public String getWithMirror() {
		String msg = "";
			if(mirror.equals("L")) {
				msg = "[\\]";
			} else if(mirror.equals("R")) {
				msg = "[/]";
			} else {
				msg = "[ ]";
			}
		return msg;
	}
	public String getWithoutMirror() {
		return "[ ]";
	}
	public void setHigherPanel(Panel h) {
		higherPanel = h;
	}
	public void setLowerPanel(Panel h) {
		lowerPanel = h;
	}
	public void setRightPanel(Panel h) {
		rightPanel = h;
	}
	public void setLeftPanel(Panel h) {
		leftPanel = h;
	}
	public Panel getHigherPanel() {
		return higherPanel;
	}
	public Panel getLowerPanel() {
		return lowerPanel;
	}
	public Panel getRightPanel() {
		return rightPanel;
	}
	public Panel getLeftPanel() {
		return leftPanel;
	}
	public void setMirror(String m) {
		mirror = m;
	}
	public String getMirror() {
		return mirror;
	}
	
}
