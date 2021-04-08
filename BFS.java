import java.util.*;
/**
 * 
 * @author Michael Lemberger
 *	BFS class- class responsible for running the A* algorithm as shown in class.
 *	Main function is BFSsearch(), using open and closed list. bfs revisited.
 *	@param _rules -for making new sons by game rules.
 *	@param _result -for path and goal finding, and printing the open list. 
 */
public class BFS {
	private Rules _rules;
	private Result _result;
	private Node _start;
	private int _count=1;
	private double _time=0;
	private boolean _open=false;
	BFS(Node start,boolean open){
		_start=start;
		_open=open;
		_result=new Result(start);
	}

	public Stack<Node> BfsSearch() {
		long begin = System.currentTimeMillis();
		Queue<Node> L = new LinkedList<>();
		L.add(_start);
		//print the open list
		if(_open)
		_result.printOpen(L, _count);
		//Open-list
		Hashtable<String, Node> O = 
                new Hashtable<String, Node>();
		O.put(_start._print, _start);
		//Closed-list
		Hashtable<String, Node> C = 
                new Hashtable<String, Node>();
		while(!L.isEmpty()) {
			Node n = L.remove();
			O.remove(n._print);
			//Inserting to Closed List used node
			C.put(n._print, n);
			//I used the class object _rules in order to crate
			//the nodes.
			_rules=new Rules(n);
			for(int i=0;i<4;i++) {
				//new son.
				Node g=_rules.buildByRules(i);
				//if operation can't be done.
				if(g==null)
					continue;
				//else count it.
				_count++;
				//if the node is not in open and closed list
				if(!C.containsKey(g._print)&&!O.containsKey(g._print)) {
					//check if we reached goal node.
				if(_result.isGoal(g)) {
					_time=(System.currentTimeMillis() - begin) / 1000.0;
					//use Result class to find path
					return _result.Path(g);
				}
				//if not, add to open list.
				L.add(g);
				if(_open)
				_result.printOpen(L, _count);
				O.put(g._print,g);
				}
			}
		}
		_time=(System.currentTimeMillis() - begin) / 1000.0;
		return null;
	}
	
	public double get_time() {
		return _time;
	}
	

	public int get_count() {
		return _count;
	}
	
}
