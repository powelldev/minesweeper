package fireminder.minesweeper.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Grid {

  private Tile[][] grid;

  /**
   * Constructs a new square grid of width and length gridSize.
   * Assuming all grids are squares.
   */
  private Grid(int gridSize) {
    grid = new Tile[gridSize][gridSize];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        grid[i][j] = new EmptyTile();
      }

    }
  }

  /**
   * Returns width|length of grid. Assuming all grids are squares.
   */
  public int size() {
    return grid.length;
  }

  /**
   * Perform game logic for what occurs at point point on the grid
   *
   * @param point location of the tile
   * @return the point of the tile that was revealed.
   */
  public Tile revealTileAt(Point point) {
    if (!isPointOnGrid(point)) {
      throw new IllegalArgumentException("Point is not on grid");
    }
    Tile tile = this.getTileAt(point);
    tile.toggleReveal();
    decideActionFor(tile, point);
    return tile;
  }

  public void peekAtTile(Point point) {
    Tile tile = this.getTileAt(point);
    if (!tile.isRevealed()) {
      tile.toggleReveal();
    }
  }

  private void decideActionFor(Tile tile, Point point) {
    switch (tile.getType()) {
      case EMPTY:
        revealEmptyAdjacentAndNumbers(point);
        break;
      case NUMBER:
        // no need to do anything here
        break;
      case MINE:
        revealMines();
        break;
    }
  }

  /**
   * When an empty square is revealed, all other adjacent empty squares or numbers
   * should also be revealed.
   */
  private void revealEmptyAdjacentAndNumbers(Point tilePoint) {
    for (Point point : pointsAdjacentTo(tilePoint)) {
      Tile tile = getTileAt(point);
      switch (tile.getType()) {
        case EMPTY:
          if (!tile.isRevealed()) {
            tile.toggleReveal();
            revealEmptyAdjacentAndNumbers(point);
          }
          break;
        case NUMBER:
          if (!tile.isRevealed()) {
            tile.toggleReveal();
          }
          break;
        case MINE:
          // should never happen
          throw new IllegalStateException("Mine adjacent to empty square?!");
      }
    }
  }

  public Tile getTileAt(Point point) {
    return grid[point.x][point.y];
  }

  private boolean isPointOnGrid(Point point) {
    return point.x >= 0 && point.x < grid.length
        && point.y >= 0 && point.y < grid.length;
  }

  private void spawnMines(int numOfMines) {
    Set<Point> points = generateRandomPoints(numOfMines);
    for (Point point : points) {
      this.addMine(point);
    }
  }

  private void addMine(Point mine) {
    grid[mine.x][mine.y] = new Mine();
    for (Point point : pointsAdjacentTo(mine)) {
      incrementTileNumber(point);
    }
  }

  /**
   * (x-1, y-1) (x, y-1) (x+1, y-1)
   * (x-1, y)   (x,y)    (x+1, y)
   * (x-1, y+1) (x, y+1) (x+1, y+1)
   */
  private List<Point> pointsAdjacentTo(Point point) {
    int x = point.x;
    int y = point.y;
    List<Point> points = new ArrayList<>();
    points.add(new Point(x - 1, y - 1));
    points.add(new Point(x - 1, y));
    points.add(new Point(x - 1, y + 1));

    points.add(new Point(x, y - 1));
    points.add(new Point(x, y + 1));

    points.add(new Point(x + 1, y - 1));
    points.add(new Point(x + 1, y));
    points.add(new Point(x + 1, y + 1));

    points = sanitizePointList(points);

    return points;
  }

  /**
   * Removes points from list if they are not on the grid.
   */
  private List<Point> sanitizePointList(List<Point> points) {
    for (Iterator<Point> candidate = points.iterator(); candidate.hasNext(); ) {
      if (!isPointOnGrid(candidate.next())) {
        candidate.remove();
      }
    }
    return points;
  }

  public List<Tile> tilesAdjacentTo(Point point) {
    List<Tile> tiles = new ArrayList<>();
    List<Point> points = pointsAdjacentTo(point);
    for (Point item : points) {
      tiles.add(getTileAt(item));
    }
    return tiles;
  }

  /**
   * Squares around mines contain a number representing how many mines
   * they are adjacent to. Increments that number for tiles around a mine.
   * @param point
   */
  private void incrementTileNumber(Point point) {
    switch (grid[point.x][point.y].getType()) {
      case EMPTY:
        grid[point.x][point.y] = new NumberTile();
        // fall-through to be incremented
      case NUMBER:
        ((NumberTile) grid[point.x][point.y]).increment();
        break;
      case MINE:
        // ignore
      default:
        break;
    }
  }

  /**
   * Generates a list of random mines.
   * @param numOfMines
   * @return
   */
  private Set<Point> generateRandomPoints(int numOfMines) {
    if (numOfMines > (grid.length * grid.length)) {
      throw new IllegalStateException("Number of mines is greater than tiles on grid!");
    }
    // Using hashSet to prevent duplicates.
    Set<Point> set = new HashSet<>();
    for (int i = 0; i < numOfMines; i++) {
      boolean unique = set.add(randomPoint(grid.length));
      while (!unique) {
        unique = set.add(randomPoint(grid.length));
      }
    }
    return set;
  }

  /**
   * Generates a random point between 0 and an upper bound
   * @param upperBound
   * @return
   */
  private Point randomPoint(int upperBound) {
    Random rand = new Random();
    int x = rand.nextInt(upperBound);
    int y = rand.nextInt(upperBound);
    return new Point(x, y);
  }

  public Tile getTileAt(int x, int y) {
    return grid[x][y];
  }

  public boolean hasWon() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        if (grid[i][j].isRevealed() && grid[i][j].type.equals(Tile.Type.MINE)) {
          return false;
        }
        else if (!grid[i][j].isRevealed() && grid[i][j].type.equals(Tile.Type.NUMBER)) {
          return false;
        }
      }
    }
    return true;
  }

  private void revealMines() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        Tile tile = grid[i][j];
        if (tile.type.equals(Tile.Type.MINE)) {
          peekAtTile(new Point(i, j));
        }
      }
    }
  }
  public void cheat() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        peekAtTile(new Point(i, j));
      }
    }
  }

  public static class Builder {
    Grid grid;

    public Builder(int gridSize) {
      grid = new Grid(gridSize);
    }

    public Builder setMineCount(int mineCount) {
      grid.spawnMines(mineCount);
      return this;
    }

    public Grid build() {
      return grid;
    }
  }


}
