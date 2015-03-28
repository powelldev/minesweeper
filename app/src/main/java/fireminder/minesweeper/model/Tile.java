package fireminder.minesweeper.model;

/**
 * Class representing a tile on a minefield grid.
 */
public abstract class Tile {

    public Tile(Type type) {
        this.type = type;
    }

    public enum Type { EMPTY, NUMBER, MINE }

    private boolean isRevealed = false;

    public void toggleReveal() {
        isRevealed = !isRevealed;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    Type type = Type.EMPTY;

    public Type getType() {
        return type;
    }
}
