import java.util.*;
/**
 * 
 * @author Michael Lemberger
 *
 */
public class DFBnB {
	private Rules _rules;
	private Node _start;
	private Result _result;
	private int _count=1;
	private double _time=0;
	private boolean _open=false;
	DFBnB(Node start,boolean open){
		_start=start;
		_open=open;
		_start.f();
		_result=new Result(start);
	}
	

	public Stack<Node> DFBnBsearch(){
		long begin = System.currentTimeMillis();
		Stack <Node> L=new Stack<>();
		L.add(_start);
		if(_open)
			_result.printOpen(L, _count);
		Hashtable<String, Node> H = 
                new Hashtable<String, Node>();
		H.put(_start._print, _start);
		Stack<Node> result=null;
		//Determining threshold is n! or maxValue as asked.
		int board=_start.get_board().get_tiles().size();
		int t=1;
		if(board<13) {
		for(int i=2;i<=board;i++) {
			t*=i;
		}
		}
		else
		t=Integer.MAX_VALUE;
		
			while(!L.isEmpty()) {
				Node n=L.pop();
				if(n.isOut())
					H.remove(n._print);
				else {
					n.set_out(true);
					L.add(n);
					
					_rules=new Rules(n);
					PriorityQueue<Node>N=new PriorityQueue<>(new NodeComparator());
					N=_rules.fillUp();

				    _count+=N.size();
				   
				    int size=N.size();
				    PriorityQueue<Node>temp=new PriorityQueue<>(new NodeComparator());
				    temp.addAll(N);
				    
					for (int i=0;i<size;i++) {
						Node g=temp.poll();
						if(g.get_totalCost()>=t)
						{
							N.clear();
							break;
						}
						else if(H.containsKey(g._print)) {
							Node gtag=H.get(g._print);
							if(gtag.isOut()) {
								N.remove(g);
							}
							else {
								if(gtag.get_totalCost()<=g.get_totalCost()) {
									N.remove(g);
								}
								else {
									L.remove(gtag);
									H.remove(gtag._print);
								}
							}
						}
						else if(_result.isGoal(g)) {
							t=g.get_totalCost();
							result=new Stack<>();
							result=_result.Path(g);
							N.clear();
							break;
						}
						}
					
					Stack<Node>stack=new Stack<>();
					stack.addAll(N);
					size=stack.size();
					for(int i=0;i<size;i++) {
						Node node=stack.pop();
						L.add(node);
						H.put(node._print,node);
					}
					if(_open)
						_result.printOpen(L, _count);
					N.clear();
					}	
	}
			_time=(System.currentTimeMillis() - begin) / 1000.0;
			return result;
	}


	public int get_count() {
		return _count;
	}

	public double get_time() {
		return _time;
	}
			
}
