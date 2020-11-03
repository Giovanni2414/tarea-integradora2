package Model;

public class Player {

	private String name;
	private int score;
	private int n;
	private int k;
	private int m;
	private int rK;
	private int failK;
	public Player right;
	public Player left;
	
	public Player(String nm, int n, int m, int k) {
		name = nm;
		this.n = n;
		this.m = m;
		this.k = k;
		failK = 0;
		score = 0;
		right = null;
		left = null;
	}
	
	public Player getLeft() {
		return left;
	}
	public Player getRight() {
		return right;
	}
	public int getRK() {
		return rK;
	}
	public int getFailK() {
		return failK;
	}
	public int getN() {
		return n;
	}
	public int getM() {
		return m;
	}
	public int getK() {
		return k;
	}
	public void setName(String n) {
		name = n;
	}
	public void addScore(int s) {
		score += s;
	}
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	public void setRestMirrors(int rk) {
		this.rK = rk;
	}
	public void setScore() {
		score = 100 - (rK * 2);
	}
	public void addFailedAttemp() {
		this.failK++;
	}
		
}
