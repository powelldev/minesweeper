package fireminder.minesweeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {

  private static final String PREF_DIFFICULTY = "pref_difficulty";

  private static final String EASY = "Easy";
  private static final String MEDIUM = "Medium";
  private static final String HARD = "Hard";

  public static Difficulty getDifficultyPref(Context context) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    String difficulty = preferences.getString(PREF_DIFFICULTY, HARD);
    switch (difficulty) {
      case EASY:
        return Difficulty.Easy;
      case MEDIUM:
        return Difficulty.Medium;
      case HARD:
        return Difficulty.Hard;
      default:
        return Difficulty.Hard;

    }
  }
}
