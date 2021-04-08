import java.util.List;
/**
 * 
 * @author Michael Lemberger
 * Node class- represents a node in the tree.
 * saves the states board, father, g(),h(),f() and a reprisintation of the node
 * in string. and the operation in string.
 */
public class Node {
	
	private Board _board;
	public String _print;
	private String _operation;
	private Node _father;
	private Boolean _out=false;
	private int _cost;
	private int _hcost=0;
	private int _totalCost;
	private static int _count;

	Node(Node n, Board board){
		_board=board;
		_father=n;
		_cost=n._cost;
		_print=_board.get_tiles().toString();
		_count++;
	}

	Node(){
		_board=new Board();
		_father=null;
	}
	public int get_count() {
		return _count;
	}
	public Node get_father() {
		return _father;
	}

	public Board get_board() {
		return _board;
	}
	public Boolean isOut() {
		return _out;
	}

	public void set_out(Boolean _out) {
		this._out = _out;
	}
	public int get_totalCost() {
		return _totalCost;
	}
	
	public int get_cost() {
		return _cost;
	}

	public void set_cost(int _cost) {
		this._cost = _cost;
	}
	public String get_operation() {
		return _operation;
	}

	public void set_operation(String _operation) {
		this._operation = _operation;
	}
	/**
	 * This is the heuristic function.
	 * it implements the Manhattan distance.
	 * checks the tile distance to it's goal location
	 * by it's y and x.
	 * @return h-cost.
	 */
	public int h() {
		List<String>tiles=get_board().get_tiles();
		int result=0;
		int m=get_board().get_size()[1];
		//count-the place of this tile.
		int count=0;
		boolean flag=false;
		//We do the Manhattan distance for each tile.
		for(String s:tiles) {
			//No point checking the empty tile.
			if(!s.equals("_")){
			//count2 is the goal location
			int count2=Integer.parseInt(s)-1;
			String color=get_board().get_colors().get(count);
			//if color is red set flag true, later we add 30 to cost.
			if(color.equals("r"))
				flag=true;
			//if the tile is black we don't move it.
			if(!color.equals("b")) {
				//temp stores the Manhattan distance for this tile, than adds it 
			int temp=(Math.abs(count%m-count2%m)+Math.abs(count/m-count2/m));
			//if tile is red
			if(flag)
				temp*=30;
			//add to overall result
			result+=temp;
			}
			}
			count++;
			flag=false;
		}
		//update node h()-cost than return it.
		_hcost=result;
		return result;
	}
	
	/**
	 * f()=g()+h()
	 * @return f-cost
	 */
	public int f() {
		//update the f()-cost than return it.
		return (_totalCost=_cost+h());
	}
	
	//toString for printing the open list
	public String toString() {
		String result="";
		//get the board sizes.
		int n=get_board().get_size()[0];
		int m=get_board().get_size()[1];
		for(int i=0; i<n+m;i++) {result+="---";}
		
		result+="\n";
		//print each value and color.
		for(int i=0; i<(n*m);i++) {				
			if(i%m==0 && i!=0)
				result+="\n";
				result+="| ";
				String value=get_board().get_tiles().get(i);
				result+=value;
				//We print the value to a non empty tile
				if(!value.equals("_"))
				result+=get_board().get_colors().get(i);
				result+=" |";
			 }
		
			 result+="\n";
			 
			 for(int i=0; i<n+m;i++) {result+="---";}
			 
			 return result;
	}

}
