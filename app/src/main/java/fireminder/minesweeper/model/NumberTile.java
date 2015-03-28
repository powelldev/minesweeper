package fireminder.minesweeper.model;

public class NumberTile extends Tile {

    int minesAdjacentTo = 0;

    public NumberTile() {
        super(Type.NUMBER);
    }

    public void increment() {
        minesAdjacentTo++;
    }

    @Override
    public String toString() {
        return Integer.toString(minesAdjacentTo);
    }
}
