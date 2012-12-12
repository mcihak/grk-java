public class Player {
	private String name;
	public String getName() {
		return this.name;
	}
	private int score;
	public int getScore() {
		return this.score;
	}
	public void setScore(int _score) {
		this.score = _score;
	}
	
	public Player(String _name, int _score) {
		this.name = _name;
		this.score = _score;
	}
	
	public String toString() {
		return name + "   [Score] " + score + "\n";
	}
}