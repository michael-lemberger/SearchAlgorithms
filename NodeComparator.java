import java.util.Comparator;
/**
 * 
 * @author Michael Lemberger
 *	Compares with the f() value of each nodes. 
 *	In increasing order.
 */
public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		
		return (o1.get_totalCost())-(o2.get_totalCost());
	}
}
