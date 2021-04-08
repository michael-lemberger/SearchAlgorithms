/**
 * 
 * @author Michael Lemberger
 *	Main for running the exercise.
 */
public class Ex1 {
	 public static void main(String[] args)
	  { 
		 //creating the game by input.
		Parser p;
		try {
		p=new Parser("input.txt");
		}
		catch(Exception e) {
			p=null;
			System.out.println("worng Input");
		}
		if(p!=null) {
		//running the game's algorithm.
	    Game game= new Game(p.getGame());
	    game.startAlgo();
		}
	  } 
}
