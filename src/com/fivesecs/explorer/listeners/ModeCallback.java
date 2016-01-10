package com.fivesecs.explorer.listeners;

import com.fivesecs.explorer.R;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ModeCallback implements ListView.MultiChoiceModeListener {

	Context mContext;
	Fragment fragment;
	public ModeCallback(Context context, Fragment fragment) {
		this.mContext = context;
		this.fragment = fragment;
	}
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.dir_list_action_menu, menu);
        mode.setTitle("Select Items");
        return true;
    }

    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_delete:
            Toast.makeText(mContext, "Shared " + ((ListFragment)fragment).getListView().getCheckedItemCount() +
                    " items", Toast.LENGTH_SHORT).show();
            mode.finish();
            break;
        default:
            Toast.makeText(mContext, "Clicked " + item.getTitle(),
                    Toast.LENGTH_SHORT).show();
            break;
        }
        return true;
    }

    public void onDestroyActionMode(ActionMode mode) {
    }

    public void onItemCheckedStateChanged(ActionMode mode,
            int position, long id, boolean checked) {
//    		View view = getListView().getChildAt(position);
//    		TextView view2 = (TextView) ((RelativeLayout)view).getChildAt(1);
//    		mode.setTitle(view2.getText().toString());
    		
//    		Log.d("TAG", view2.getClass().toString());
    		
        final int checkedCount = ((ListFragment)fragment).getListView().getCheckedItemCount();
        switch (checkedCount) {
        
            case 0:
                mode.setSubtitle(null);

                break;
            case 1:
                mode.setSubtitle("1 item selected");

                break;
            default:
                mode.setSubtitle("" + checkedCount + " items selected");

                break;
        }
    }
    
}
