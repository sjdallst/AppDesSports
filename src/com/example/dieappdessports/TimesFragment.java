package com.example.dieappdessports;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.ListFragment;
import java.util.List;
import java.util.ArrayList;


public class TimesFragment extends ListFragment{
	
	OnTimeSelectedListener mCallback;
	
	// The container Activity must implement this interface so the frag can deliver messages
    public interface OnTimeSelectedListener {
        /** Called by TimeFragment when a list item is selected */
        public void onTimeSelected(int position);
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
		List<String> timesStringArray = new ArrayList<String>();
		for(int i = 0; i < 50; i++){
			if(i < 10){
				timesStringArray.add("12:0" + i + "pm");
			}
			else{
			timesStringArray.add("12:" + i + "pm");
			}
		}
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, timesStringArray));
    }
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onTimeSelected(position);
        
        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnTimeSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTimeSelectedListener");
        }
    }
	
	@Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.time_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }
}
