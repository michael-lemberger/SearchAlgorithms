import java.util.*;
/**
 * 
 * @author Michael Lemberger
 * Rules class responsible for making nodes with every allowed operation.
 * buildByRules(int)- main function, checks which operation is allowed and
 * prepering the nodes by it.
 */
public class Rules {
	
private Node _state;
private boolean _cost=false;
private List<String> tiles;
private List<String> colors;

Rules(Node state){
	_state=state;
	tiles=_state.get_board().get_tiles();
	colors= _state.get_board().get_colors();
}

public Node buildByRules(int num){
	
	int father_blank=0;
	//In this part we seek the fathers empty tile index.
	//I do this in order to prevent making the opposed operation.
	if(_state.get_father()!=null) {
	List<String> father_tiles=_state.get_father().get_board().get_tiles();
	
	for(String s:father_tiles) {
		if(s.equals("_")) {
			break;
		}
		father_blank++;
	}
	
	}
	//if father is null, state is the start node
	else {father_blank=-1;}
	
	int blank=0;
	for(String s:tiles) {
		if(s.equals("_")) {
			break;
		}
		blank++;
	}
	//We use this function in order to check if an operation can be done.
	//The user passes num that represent the operation, by order. 
	num=operation(num, blank, father_blank);
	//if 0, the operation can't be done. return null
	if(num!=0) {
		Board board=new Board(_state.get_board());
		//set the board by the changes that needs to be made.
		board.get_tiles().set(blank+num, tiles.get(blank));
		board.get_colors().set(blank+num, "");
		board.get_tiles().set(blank, tiles.get(blank+num));
		board.get_colors().set(blank, colors.get(blank+num));
		//the son.
		Node node=new Node(_state,board);
		
		String operation=""+tiles.get(blank+num);
		//chose the correct operation as string
		if(num<0) {
			if(num<-1)
				operation+="D";
			else
				operation+="R";
		}
		else{
			if(num>1)
				operation+="U";
			else
				operation+="L";
		}
		//than save it
		node.set_operation(operation);
		//if we need to add 30 for red tile.
		if(_cost)
			node.set_cost(node.get_father().get_cost()+30);
		else
			node.set_cost(node.get_father().get_cost()+1);
		
		return node;
	}

	return null;
}
//function for creating all sons in one go.
public PriorityQueue<Node> fillUp(){
	PriorityQueue<Node>temp=new PriorityQueue<>(new NodeComparator());
	for(int i=0;i<4;i++) {
		Node g=buildByRules(i);
		if(g==null)
			continue;
		g.f();
		temp.add(g);
	}
	return temp;
}

//Returns 0 if the operation can't be done
//Otherwise, returns the number of steps the tile should
//move (inside the list containing the tiles).
private int operation(int i,int blank,int last) {
	int num=0;
	_cost=false;
	int n=_state.get_board().get_size()[1];
	int m=_state.get_board().get_size()[0];
	String color="";
	
	switch(i) {
	//Left
	case 0:
		//check if exceeding the sides.
		if((blank+1)%n == 0)
			break;
		//check if doing the opposed operation.
		if((blank+1)==last&&last!=-1)
			break;
		color= _state.get_board().get_colors().get(blank+1);
		//check if the tile is black
		if(color.equals("b"))
			break;
		//check if the tile is red
		if(color.equals("r"))
			_cost=true;
		
		num=1;
		break;
		//Checking almost the same conditions from now.
		//Up
	case 1:
		if(blank+n>=(n*m))
			break;
		if(blank+n==last&&last!=-1)
			break;
		color= _state.get_board().get_colors().get(blank+n);
		if(color.equals("b"))
			break;
		if(color.equals("r"))
			_cost=true;
		
		num=n;
		break;
		//Right
	case 2:
		if((blank+1)%n == 1)
			break;
		if(blank-1==last&&last!=-1)
			break;
		color= _state.get_board().get_colors().get(blank-1);
		if(color.equals("b"))
			break;
		if(color.equals("r"))
			_cost=true;
		
		num=-1;
		break;
		//Down
	case 3:
		if(blank-n<0)
			break;
		if((blank-n)==last&&last!=-1)
			break;
		color= _state.get_board().get_colors().get(blank-n);
		if(color.equals("b"))
			break;
		if(color.equals("r"))
			_cost=true;
		
		num=-n;
		break;
	}
	
	return num;
}
}
