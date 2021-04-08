import java.io.*;
import java.util.*;
/**
 * 
 * @author Michael Lemberger
 *
 */
public class Parser {
	private Game game;

	private enum Names{
		BFS,DFID,A,IDA,DFBnB;
	}
	
	Parser(String s){
	game=new Game();
	try {
		readFromText(s);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

	private void readFromText(String name) throws IOException {
	 List<String> lblack = new ArrayList<String>();
	 List<String> lred = new ArrayList<String>();
	 File file = new File(name); 
	 boolean flag=false;
	 boolean enter=false;
		    Scanner sc = new Scanner(file); 
		    while (sc.hasNextLine()) { 
            String s=sc.nextLine();
            String check=s.replaceAll("[0-9]", "");
            check=check.replaceAll("_", "");
            check=check.replaceAll(",","");
            check=check.replaceAll(" ","");
            
           if(!flag) {
            for (Object obj : Names.values())
            {
                    if (s.equals(obj.toString())||s.equals(obj.toString()+"*"))
                {
                    flag=true;
                    break;
                }
            }
           }
           
           if(flag&&!enter) {
           	game.set_type(s);
           	enter=true;
           }
           
           else if(check.equals("x")) {
           String[] arrOfNum = s.split("x"); 
           int[] size = new int [2];
           size[0]=Integer.parseInt(arrOfNum[0]);
           size[1]=Integer.parseInt(arrOfNum[1]);
   			game.get_start().get_board().set_size(size);
   			
           }
           
           else if(s.equals("with time")) 
           	game.set_time(true);
           
           else if(s.equals("with open")) 
           	game.set_open(true);
           
           else if(check.equals("Black:")) {
				s = s.replaceAll("[^0-9]+", " ");
				lblack.addAll(Arrays.asList(s.trim().split(" ")));
			}
			
			else if(check.equals("Red:")) {
				s = s.replaceAll("[^0-9]+", " ");
				lred.addAll(Arrays.asList(s.trim().split(" ")));
			}
			
			else if(check.equals("")){
				game.get_start().get_board().get_tiles().addAll(Arrays.asList(s.trim().split(",")));
			}
			else {
				if(!check.equals("noopen")&&!check.equals("notime"))
				throw new IOException();
			}
		}
		    int size=game.get_start().get_board().get_tiles().size();
           for(int i=0;i<size;i++) {
        		   game.get_start().get_board().get_colors().add("g");
        	   }
           for (int i=0;i<game.get_start().get_board().get_tiles().size();i++) {
               if(lblack.contains(game.get_start().get_board().get_tiles().get(i))){
            	   game.get_start().get_board().get_colors().add(i,"b");
               }
               if(lred.contains(game.get_start().get_board().get_tiles().get(i))){
            	   game.get_start().get_board().get_colors().add(i,"r");
               }
           }
		game.get_start()._print=game.get_start().get_board().get_tiles().toString();   
	}
	
	public Game getGame() {
		return game;
	}

}

