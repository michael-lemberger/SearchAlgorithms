import java.util.*;
/**
 * 
 * @author Michael Lemberger
 *
 */
public class DFID {
	private Result _result;
	private Node _start;
	private static int num=0;
	private int _count=1;
	private double _time=0;
	private boolean _open=false;
	DFID(Node start,boolean open){
		_start=start;
		_open=open;
		_result=new Result(start);
	}
	
	public Stack<Node> DFIDsearch() {
		long begin = System.currentTimeMillis();
		int i=1;
		while(true) {
			num=0;
			Hashtable<String, Node> H = 
                    new Hashtable<String, Node>(); 
			Stack<Node> result=Limited_DFS(_start,i,H);
			if(result==null||!result.isEmpty()) {
				_time=(System.currentTimeMillis() - begin) / 1000.0;
				return result;
			}
			i++;
		}
	}
	
	private Stack<Node> Limited_DFS(Node n,int limit, Hashtable<String,Node> H){
		if(n!=_start)
		_count++;
		
		if(_result.isGoal(n))
			return _result.Path(n);
		else if(limit==0)
			return new Stack<>();
		else {
			H.put(n._print, n);
			if(_open)
				_result.printOpen(H, _count);
			boolean isCutoff=false;
			Rules rules=new Rules(n);
			for(int i=0;i<4;i++) {
				Node g=rules.buildByRules(i);
				if(g==null)
					continue;
				if(H.containsKey(g._print))
					continue;
				Stack<Node> result =Limited_DFS(g,limit-1,H);
				if(result!=null&&result.isEmpty()) {
					isCutoff=true;
				}
				else if(result!=null)
					return result;
			}
			H.remove(n._print);
			if(isCutoff) {
				return new Stack<>();
			}
			else
				return null;
			 
		}
	}
	
	public int get_count() {
		return _count;
	}
	public double get_time() {
		return _time;
	}
}

