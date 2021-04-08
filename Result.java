import java.util.Stack;
import java.util.List;
import java.util.Queue;
/**
 * 
 * @author Michael Lemberger
 *	class responsible for path and goal finding, and printing the open list.
 *	Path(Node)-finds the path by the father node.
 *	isGoal(Node)-checks if the node is a goal node. returns a boolean.
 *	printOpen- prints the open list at an iteration as asked by user. 
 */
public class Result {
	Stack<Node> _path;
	Node _start;
	Result(Node n){
		_start=n;
		_path=new Stack<>();
	}
	
	public Stack<Node> Path(Node n){
		//stop condition
		if(n==_start) {
			return _path;
		}
		_path.add(n);
		//recursive
		Path(n.get_father());
		return _path;
	}
	
	public boolean isGoal(Node n) {
		int i=1;
		List <String> check=n.get_board().get_tiles();
		for(String s:check) {
			if(!s.equals("_")) {
			if(Integer.parseInt(s)!=i)
				return false;
			i++;
			}
			else if(i!=check.size()) {
				return false;
			}
		}
		return true;
	}
	
	public void printOpen(Object open,int count) {
		if(open!=null) {
		System.out.println("Count "+count+":");
		System.out.println("===================");
		System.out.println(open);
		System.out.println("===================");
		}
	}
}
