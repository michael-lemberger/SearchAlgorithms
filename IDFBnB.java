import java.util.*;

public class IDFBnB {
	private Rules _rules;
	List<Node>_goals;
	Stack<Node> _path;
	private Node _start;
	private static int _count=0;
	
	public IDFBnB(Node start, List<Node> goals){
		_rules=new Rules();
		_start=start;
		_goals=goals;
		_path=new Stack<>();
		IDFBnBsearch(start);
	}
	

	private Stack<Node> IDFBnBsearch(Node start){
		Stack <Node> L=new Stack<>();
		Hashtable<Long, Node> H = 
                new Hashtable<Long, Node>(); 
		Queue<Node> result=null;
		int t=Integer.MAX_VALUE;
			while(!L.isEmpty()) {
				Node n=L.pop();
				if(n.isOut())
					H.remove(n.get_id());
				else {
					n.set_out(true);
					L.add(n);
					Queue<Node> N=_rules.buildByRules(n);
					N=Sort(N);
	}
	}
			return null;
	}
	
	private boolean isGoal(Node n) {
		int i=0;
		List <String> check=n.get_board().get_tiles();
		for(Node nd:_goals) {	
		List <String> goal=nd.get_board().get_tiles();
		for(String s:check) {
			if(!s.equals(goal.get(i)))
				return false;
			i++;
		}
		}	
		return true;
	}
	
	private Stack<Node> Path(Node n){
		System.out.println(n);
		_path.add(n);
		if(n==_start) {
			System.out.println(_count+1);
			return _path;
		}
		
		Path(n.get_father());
		return null;
	}
	
	private int h(Node node) {
		List<String>tiles=node.get_board().get_tiles();
		List<String>goal=_goals.get(0).get_board().get_tiles();
		int result=0;
		int m=node.get_board().get_size()[1];
		int count=0;
		for(String s:tiles) {
			int count2=0;
			for(String str:goal) {
			if(s.equals(str))
			result+=(Math.abs(count%m-count2%m)+Math.abs(count/m-count2/m));
			count2++;
			}
			count++;
		}
		return result;
	}
	
	private int f(Node n) {
		
		return 0;
	}
	
	private Queue<Node> Sort(Queue<Node>queue) {
		PriorityQueue<Node>pq=new PriorityQueue<Node>();
		pq.addAll(queue);
		queue.addAll(pq);
			return queue;
		}
}
