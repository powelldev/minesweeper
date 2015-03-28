package fireminder.minesweeper.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import fireminder.minesweeper.R;

public class SettingsFragment extends PreferenceFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.preferences);
  }

}
