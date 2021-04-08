import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
/**
 * 
 * @author Michael Lemberger
 *
 */
public class IDASTAR {
	private Rules _rules;
	private Result _result;
	private Node _start;
	private int _count=1;
	private double _time=0;
	private boolean _open=false;
	IDASTAR(Node start,boolean open){
		_start=start;
		_open=open;
		_result=new Result(start);
	}
	

	public Stack<Node> IDASTARsearch(){
		//Measure time
		long begin = System.currentTimeMillis();
		Stack <Node> L=new Stack<>();
		Hashtable<String, Node> H = 
                new Hashtable<String, Node>(); 
		int t=_start.h();
		while(t!=Integer.MAX_VALUE) {
			L.add(_start);
			if(_open)
				_result.printOpen(L, _count);
			H.put(_start._print, _start);
			int minF=Integer.MAX_VALUE;
			_start.set_out(false);
			while(!L.isEmpty()) {
				Node n=L.pop();
				if(n.isOut())
					H.remove(n._print);
				else {
					n.set_out(true);
					L.add(n);
					_rules=new Rules(n);
					for(int i=0;i<4;i++) {
						Node g=_rules.buildByRules(i);
						if(g==null)
							continue;
						_count++;
						if(g.f()>t) {
							minF=Math.min(minF, g.get_totalCost());
							continue;
						}
						Node g1=H.get(g._print);
						if(H.containsKey(g._print)) {
							if(g1.isOut())
								continue;
							else {
								if(g1.f()>g.get_totalCost()) {
									L.remove(g1);
									H.remove(g1._print);
								}
								else
								continue;
								
							}
						}
						if(_result.isGoal(g)) {
							_time=(System.currentTimeMillis() - begin) / 1000.0;
							return _result.Path(g);
						}
						L.add(g);
						if(_open)
							_result.printOpen(L, _count);
						H.put(g._print, g);
					}
				}
			}
			t=minF;
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
