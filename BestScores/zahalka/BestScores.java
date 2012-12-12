import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BestScores {
	private List<Player> playList;
	
	public BestScores() {
		this.playList = new ArrayList<Player>();
	}
	
	public void add(String _name, int _score) {
		boolean found = false;
		for (Player p : playList) {
			if (p.getName().contentEquals(_name)) {
				found = true;
				if (p.getScore() < _score) {
					p.setScore(_score);
				}
				break;
			}
		}
		if (!found) {
			playList.add(new Player(_name, _score));
		}
		
		PlayerComparator comp = new PlayerComparator();
		Collections.sort(playList, comp);
	}
	
	public String toString() {
		String complete = "[Players]\n";
		int index = 1;
		for (Iterator<Player> i = playList.iterator(); i.hasNext(); ++index) {
			complete += index + ". " + i.next().toString();
		}
		return complete;
	}
}