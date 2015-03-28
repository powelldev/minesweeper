package fireminder.minesweeper.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import fireminder.minesweeper.ControlFragment;
import fireminder.minesweeper.R;


public class MainActivity extends ActionBarActivity implements ControlFragment.ControlPanelListener {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getFragmentManager()
        .beginTransaction()
        .add(R.id.container, new MineFragment(), "mine")
        .add(R.id.lower_container, new ControlFragment(), "control")
        .addToBackStack("mine")
        .commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      getFragmentManager()
          .beginTransaction()
          .replace(R.id.container, new SettingsFragment())
          .detach(getFragmentManager().findFragmentById(R.id.lower_container))
          .addToBackStack("mine")
          .commit();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    if (getFragmentManager().getBackStackEntryCount() > 1)
      getFragmentManager().popBackStack();
    else
      super.onBackPressed();
  }

  private MineFragment getMineFragment() {
    MineFragment fragment = (MineFragment) getFragmentManager().findFragmentById(R.id.container);
    return fragment;
  }
  @Override
  public void onNewGame() {
    getMineFragment().newGame();
  }

  @Override
  public void onValidate() {
    getMineFragment().validate();
  }

  @Override
  public void onCheat() {
    getMineFragment().cheat();
  }
}
