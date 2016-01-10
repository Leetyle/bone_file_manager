package com.fivesecs.explorer;


import com.fivesecs.explorer.utils.ExpolererUtils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

public class MainActivity extends Activity {
	public static final String tag = "Facade";
	public static ActionMode mActionMode;
	// private String currentPath = Environment.getDataDirectory().getPath();
	private Toolbar toolbar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facade);
		initToolBarAndActionMode();
		setDirContent();
		
	}

	private void initToolBarAndActionMode() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setActionBar(toolbar);
		toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
		toolbar.setElevation(10);
		Log.v(tag, "inOncreate");
	}
	
	public void setToolbarSubtitle(String path) {
		toolbar.setSubtitle(path);
	}


	private void setDirContent() {
			
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragTransaction = fm.beginTransaction();
		
		DirFragment fragment = (DirFragment) fm.findFragmentById(R.id.container_for_content_listview);	
		if(fragment == null) {
			String path = Environment.getRootDirectory().getAbsolutePath();
			fragment = DirFragment.newInstance(path);
			setToolbarSubtitle(path);
			fragTransaction.replace(R.id.container_for_content_listview, fragment, "current_content_frag");
			fragTransaction.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.facade, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_delete:
			
			break;

		default:
			break;
		}
		
		return true;
	}

}
