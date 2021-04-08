import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
/**
 * 
 * @author Michael Lemberger
 *	
 */
public class Game {
	private String _type;
	private boolean _time;
	private boolean _open;
    private Node _start;
    private Stack<Node> _path; 
	 Game()
	 {
		 _start=new Node();
	 }
	 
	 Game(Game other){
		 this._type=other._type;
		 this._time=other._time;
		 this._open=other._open;
		 this._start=other._start;
	 }
	 
	 /**
	  * 
	  */
	 public void startAlgo() {
		 int num=0;
		 double time=0.0;
		 if(_type.equals("BFS")) {
			 BFS bfs=new BFS(_start,_open);
			 _path=bfs.BfsSearch();
			 num=bfs.get_count();
			 if(_time)
				 time=bfs.get_time();
		 }
		 else if(_type.equals("DFID")) {
			 DFID dfid=new DFID(_start,_open);
			 _path=dfid.DFIDsearch();
			 num=dfid.get_count();
			 if(_time)
				 time=dfid.get_time();
		 }
		 else if(_type.equals("A*")) {
			 ASTAR astar=new ASTAR(_start,_open);
			 _path=astar.ASTARsearch();
			 num=astar.get_count();
			 if(_time)
				 time=astar.get_time();
		 }
		 else if(_type.equals("IDA*")) {
			 IDASTAR idastar=new IDASTAR(_start,_open);
			 _path=idastar.IDASTARsearch();
			 num=idastar.get_count();
			 if(_time)
				 time=idastar.get_time();
		 }
		 else if(_type.equals("DFBnB")) {
			 DFBnB dfbnb=new DFBnB(_start,_open);
			 _path=dfbnb.DFBnBsearch();
			 num=dfbnb.get_count();
			 if(_time)
				 time=dfbnb.get_time();
		 }
		 outPut(num,time);
	 }
	 
	 private void outPut(int num,double time) {
		 int cost=0;
		 String data = "";
		 if(_path==null) {
			 data+="no path";
		 }
		 else {
			 int size=_path.size();
		 for(int i=1;i<=size;i++) {
			 Node n=_path.pop();
			 data+=""+n.get_operation();
			 if(i<size)
				 data+="-";
			 if(_path!=null&&i==size) {
				cost=n.get_cost();
			 }
		 }
		 }
		 data+="\n";
		 data+="Num: "+num+"\n";
		 
		 if(_path!=null) {
			 data+="Cost: "+cost+"\n";
			 
		 if(_time) {
			 String s=String.valueOf(time); 
			 data+=""+s+" seconds\n";
		 }
		 
		 }
		 
		 try {
		 FileOutputStream out = new FileOutputStream("output.txt");
			out.write(data.getBytes());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		
	 public Node get_start() {
		return _start;
	}

	public String get_type() {
		return _type;
	}

	public boolean is_time() {
		return _time;
	}

	public boolean is_open() {
		return _open;
	}
	public void set_type(String _type) {
		this._type = _type;
	}

	public void set_time(boolean _time) {
		this._time = _time;
	}

	public void set_open(boolean _open) {
		this._open = _open;
	}
}
