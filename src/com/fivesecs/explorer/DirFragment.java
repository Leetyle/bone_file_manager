package com.fivesecs.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fivesecs.explorer.utils.ExpolererUtils;

public class DirFragment extends ListFragment {
	public  static String path;
	private FileListAdapter adapter = null;
	private List<File> filesToAdapter = new ArrayList<File>();
	private TextView txt_path;

	public static DirFragment newInstance(String filePath) {
		DirFragment f = new DirFragment();
		Bundle args = new Bundle();
		args.putString("path", filePath);
		f.setArguments(args);
		path = filePath;
		return f;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		path = getArguments().getString("path");
		Log.d(Facade.tag, path);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initDirListItem(path);
	}
	
	private void initDirListItem(String path) {
		filesToAdapter = ExpolererUtils.getFileList(new File(path));
		adapter = new FileListAdapter(getActivity(), filesToAdapter);
		setListAdapter(adapter);

		txt_path = (TextView) getActivity().findViewById(R.id.txt_path);
		txt_path.setText(path);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		File clickedFile = filesToAdapter.get(position);
		String clicedFilePath = clickedFile.toString();
		if(!clickedFile.canRead()) {
			Toast.makeText(getActivity(), "Sorry, permission denied.", Toast.LENGTH_SHORT).show();
			return;
		}
		if(clickedFile.isDirectory()) {
			ft.replace(R.id.container_for_content_listview, DirFragment.newInstance(clicedFilePath));
		}
		
		ft.commit();

//		if (.get(position) == "..") {
//			path = new File(path).getParent();
//		} else {
//			if (path.equals("/")) { 
//				path = path + adapter.getItem(position);
//			} else {
//				path = path + File.separator + adapter.getItem(position);
//			}
//		}
//		File dir = new File(path);
//		if (dir.isDirectory()) {
//			Log.v(tag, path);
//			ft.replace(R.id.container_for_content_listview, DirFragment.newInstance(path));
//			// ft.addToBackStack(null);
//			ft.commit();
//		} else {
//			path = path.substring(0, path.lastIndexOf("/") - 1);
//			return;
//		}

	}

}
