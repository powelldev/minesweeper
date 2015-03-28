package fireminder.minesweeper.model;

public class Mine extends Tile {

    Mine() {
        super(Type.MINE);
    }

    @Override
    public String toString() {
        return "Mine";
    }
}
