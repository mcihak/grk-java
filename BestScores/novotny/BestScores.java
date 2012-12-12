import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class BestScores {
	List<Player> playList = new ArrayList<Player>();
	
	public void add(String n, int s){
		playList.add(new Player(n,s));
	}
	public String toString(){
		Collections.sort(playList);
		String r = "";
		for (Player p1 : playList) r += p1 + "\r\n";
		return r;
	}
}