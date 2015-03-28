package fireminder.minesweeper.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import fireminder.minesweeper.Difficulty;
import fireminder.minesweeper.PrefUtils;
import fireminder.minesweeper.R;
import fireminder.minesweeper.model.Grid;
import fireminder.minesweeper.model.Point;
import fireminder.minesweeper.model.Tile;

public class MineFragment extends Fragment {
  MinefieldView gridView;
  Grid grid;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
    gridView = (MinefieldView) rootView.findViewById(R.id.grid);
    Difficulty difficulty = PrefUtils.getDifficultyPref(getActivity());
    grid = new Grid.Builder(difficulty.gridSize).setMineCount(difficulty.numOfMines).build();
    setupGridView(grid);
    return rootView;
  }

  private void createNewGame() {
    Difficulty difficulty = PrefUtils.getDifficultyPref(getActivity());
    grid = new Grid.Builder(difficulty.gridSize).setMineCount(difficulty.numOfMines).build();
    setupGridView(grid);
  }

  /**
   * Clear old minefield and construct new one based on the grid object
   * passed in.
   */
  private void setupGridView(Grid grid) {
    int buttonSize = getDisplayWidth() / grid.size();
    gridView.removeAllViews();
    gridView.setRowCount(grid.size());
    gridView.setColumnCount(grid.size());
    for (int row = 0; row < grid.size(); row++) {
      for (int column = 0; column < grid.size(); column++) {

        TileButton tileButton = new TileButton(getActivity(), new Point(row, column));
        tileButton.setOnClickListener(tileButtonClickListener);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.height = buttonSize;
        params.width = buttonSize;
        params.setGravity(Gravity.CENTER);
        params.columnSpec = GridLayout.spec(column);
        params.rowSpec = GridLayout.spec(row);
        tileButton.setLayoutParams(params);
        gridView.addView(tileButton);
      }
    }
  }


  /**
   * The width of the current screen in pixels
   */
  private int getDisplayWidth() {
    DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
    return displayMetrics.widthPixels;
  }

  View.OnClickListener tileButtonClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      TileButton tileButton = (TileButton) v;
      Point point = tileButton.getPoint();
      Tile revealedTile = grid.revealTileAt(point);
      gridView.updateView(grid);
      tileButton.updateView(revealedTile);
    }
  };

  public void newGame() {
    createNewGame();
    Toast.makeText(getActivity(), getString(R.string.glhf), Toast.LENGTH_SHORT).show();
  }

  public void validate() {
    boolean won = grid.hasWon();
    if (won) {
      Toast.makeText(getActivity(), getString(R.string.victory), Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(getActivity(), getString(R.string.failure), Toast.LENGTH_SHORT).show();
      cheat();
    }
  }

  public void cheat() {
    grid.cheat();
    gridView.updateView(grid);
  }
}
