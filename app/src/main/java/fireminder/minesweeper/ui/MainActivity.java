package fireminder.minesweeper.ui;

import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;

import fireminder.minesweeper.Difficulty;
import fireminder.minesweeper.PrefUtils;
import fireminder.minesweeper.R;
import fireminder.minesweeper.model.Grid;
import fireminder.minesweeper.model.Point;
import fireminder.minesweeper.model.Tile;


public class MainActivity extends ActionBarActivity {

    MinefieldView gridView;
    Grid grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      getFragmentManager().beginTransaction().add(R.id.container, new MineFragment(), "mine").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
          getFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
