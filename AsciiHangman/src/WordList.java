import java.util.*; 

public class WordList {
	
	private List<String> list; 

	
	public WordList(int size) {
		while (size >= 0) {
			list = Arrays.asList(new String[size]);
		}
	}
	
	public boolean isEmpty() {
		return (list.isEmpty());
	}
	
	public void add(String s) {
		while (!list.contains(s)) {
			list.add(s);
		}
		
	}

	public String selectAny() {
		while (!list.isEmpty()) {
			return list.get(((int) Math.random() * list.size()));
		}
		return "List is empty!";
	}
	
	public void remove(String word) {
		int windex = 0;
		while (word != null && list.contains(word)) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == word) {
					windex = i;
				}
			}
			list.remove(windex);
		}
	}
}
