import java.util.*;
/**
 * 
 * @author Michael Lemberger
 *	Astar class- class responsible for running the A* algorithm as shown in class.
 *	Main function is ASTARsearch(), using open and closed list.
 *	@param _rules -for making new sons by game rules.
 *	@param _result -for path and goal finding, and printing the open list. 
 */

public class ASTAR {
	private Rules _rules;
	private Node _start;
	private Result _result;
	private int _count=1;
	private double _time=0;
	private boolean _open=false;
	ASTAR(Node start,boolean open){
		_start=start;
		_open=open;
		_result=new Result(start);
	}
	
	public Stack<Node> ASTARsearch(){
		long begin = System.currentTimeMillis();
		
		_start.f();
		
		PriorityQueue<Node>L=new PriorityQueue<Node>(new NodeComparator());
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
			Node n=L.remove();
			O.remove(n._print);
			
			if(_result.isGoal(n)) {
				_time=(System.currentTimeMillis() - begin) / 1000.0;
				return _result.Path(n);
			}
			
			C.put(n._print, n);
			//I used the class object _rules in order to crate
			//the nodes.
			_rules=new Rules(n);
			for(int i=0;i<4;i++) {
				//4 rules overall. Each iteration
				//a new son is created by order. If null, it means that
				//an operation can't be done. 
				Node x=_rules.buildByRules(i);
				if(x==null)
					continue;
				//count the if it is no null
				_count++;
				//get totalDistance with heuristic
				x.f();
				
				if(!C.containsKey(x._print)&&!O.containsKey(x._print)) {
					L.add(x);
					O.put(x._print,x);
				}
				else if(O.containsKey(x._print)) {
					Node node=O.get(x._print);
					if(node.get_totalCost()>x.get_totalCost()) {
						L.remove(node);
						L.add(x);
						O.replace(node._print,x);
					}
				}
				//print the open list
				if(_open)
					_result.printOpen(L, _count);
			}
		}
		//update time
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
