package fireminder.minesweeper;


  public enum Difficulty {
    Easy(3, 1), Medium(5, 3), Hard(8, 10);

    public int gridSize, numOfMines;

    Difficulty(int gridSize, int numOfMines) {
      this.gridSize = gridSize;
      this.numOfMines = numOfMines;
    }
  }
