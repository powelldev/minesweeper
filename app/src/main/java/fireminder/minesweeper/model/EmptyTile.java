package fireminder.minesweeper.model;

/**
 * Class representing an empty tile on the minefield. Using a
 * specific class rather than an 0 integer provides better
 * separation of concerns.
 */
public class EmptyTile extends Tile {

    public EmptyTile() {
        super(Type.EMPTY);
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
