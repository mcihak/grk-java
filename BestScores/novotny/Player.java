public class Player implements Comparable<Player>{
	private String name;
	private int score;
	
	public Player(String _name, int _score){
		name = _name;
		score = _score;
	}
	
	public int GetScore(){
		return score;
	}
	
	public String toString(){return name + ": " + score;}

	@Override
	public int compareTo(Player p0){
        if (score < p0.GetScore())
            return 1;
        else if (score == p0.GetScore())
            return 0;
        else
            return -1;
	}
}
