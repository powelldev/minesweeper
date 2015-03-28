package fireminder.minesweeper.ui;

import android.content.Context;
import android.widget.Button;

import fireminder.minesweeper.model.Point;
import fireminder.minesweeper.model.Tile;

public class TileButton extends Button {

  private Point point;

  public TileButton(Context context, Point point) {
    super(context);
    this.point = point;
  }

  /**
   * Forces the button to display the state of this tile
   */
  public void updateView(Tile tile) {
    if (tile.isRevealed()) {
      this.setText(tile.toString());
    } else {
      this.setText("");
    }
  }

  public Point getPoint() {
    return point;
  }

}
