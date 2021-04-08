import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Michael Lemberger
 *	Board class - holds the tiles values and numbers in a list.
 */
public class Board {
	private List<String> _tiles;
	private List<String> _colors;
	private int[] _size;
	
	Board() {
		this._tiles = new ArrayList<>();
		this._colors = new ArrayList<>();
		_size=new int[2];
	}
	Board(Board other) {
		this();
		this._tiles.addAll(other.get_tiles());
		this._colors.addAll(other.get_colors());
		_size=other._size;
	}
	public List<String> get_tiles() {
		return _tiles;
	}
	public List<String> get_colors() {
		return _colors;
	}
	public void set_size(int[] _size) {
		this._size = _size;
	}
	public int[] get_size() {
		return _size;
	}
	public void set_tiles(List<String> _tiles) {
		this._tiles = _tiles;
	}
}
