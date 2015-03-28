package fireminder.minesweeper.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

import fireminder.minesweeper.model.Grid;
import fireminder.minesweeper.model.Point;
import fireminder.minesweeper.model.Tile;

public class MinefieldView extends GridLayout {

  public MinefieldView(Context context) {
    super(context);
  }

  public MinefieldView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MinefieldView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  /**
   * Force each TileButton contained in this layout to update to whatever state
   * its corresponding Tile is in.
   *
   * @param grid The grid this view is representing
   */
  public void updateView(Grid grid) {
    for (int i = 0; i < getChildCount(); i++) {
      TileButton tileButton = (TileButton) getChildAt(i);
      Point point = tileButton.getPoint();
      Tile tile = grid.getTileAt(point);
      tileButton.updateView(tile);
    }
  }

}
