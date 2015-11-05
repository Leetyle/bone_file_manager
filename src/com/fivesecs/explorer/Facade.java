package com.fivesecs.explorer;


import java.io.File;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class Facade extends Activity {
	public static final String tag = "Facade";
	private String currentPath = Environment.getRootDirectory().toString();
//	private String currentPath = Environment.getDataDirectory().getPath();
	DirFragment content_fragment = DirFragment.newInstance(currentPath);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facade);
		
		setDefaultDirContent();
		Log.v(tag, "inOncreate");
	}
	
	private void setDefaultDirContent() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragTransaction = fm.beginTransaction();
		fragTransaction.replace(R.id.container_for_content_listview, content_fragment, "current_content_frag");
//		fragTransaction.addToBackStack(null);
		fragTransaction.commit();
	}
	
	
}
