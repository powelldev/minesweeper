package fireminder.minesweeper;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fireminder.minesweeper.ControlFragment.ControlPanelListener} interface
 * to handle interaction events.
 */

public class ControlFragment extends Fragment implements View.OnClickListener {

  private ControlPanelListener mListener;

  public ControlFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_control, container, false);

    rootView.findViewById(R.id.new_game).setOnClickListener(this);
    rootView.findViewById(R.id.validate).setOnClickListener(this);
    rootView.findViewById(R.id.cheat).setOnClickListener(this);

    return rootView;
  }



  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      mListener = (ControlPanelListener) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(activity.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.validate:
        mListener.onValidate();
        break;
      case R.id.cheat:
        mListener.onCheat();
        break;
      case R.id.new_game:
        mListener.onNewGame();
        break;
    }
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface ControlPanelListener {
    public void onNewGame();
    public void onValidate();
    public void onCheat();
  }

}
