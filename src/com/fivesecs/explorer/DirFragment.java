package com.fivesecs.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fivesecs.explorer.utils.ExpolererUtils;
import com.fivesecs.explorer.utils.FileItem;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DirFragment extends ListFragment implements OnCheckedChangeListener, OnClickListener{
	public  static String path;
	private FileListAdapter adapter = null;
	private List<FileItem> filesToAdapter = new ArrayList<FileItem>();
//	private List<File> filesToAdapter = new ArrayList<File>();
	private TextView txt_path;
	private List<File> selectedFiles = new ArrayList<File>();

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
		Log.d(Facade.tag, "in onAttach...");
	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		path = this.getArguments().getString(path);
		path = getArguments().getString("path");  // Before, this statement in the onAttach method, and there is something wrong with the back stack.
		initDirListItem(path);
		Log.d(Facade.tag, path);
		Log.d(Facade.tag, "in onActivityCreated...");
	}
	
	private void initDirListItem(String path) {
		filesToAdapter = ExpolererUtils.getFileItemList(new File(path));
		adapter = new FileListAdapter(this, filesToAdapter);
		setListAdapter(adapter);

		txt_path = (TextView) getActivity().findViewById(R.id.txt_path);
		txt_path.setText(path);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		File clickedFile = filesToAdapter.get(position).getFile();
		String clicedFilePath = clickedFile.toString();
		if(!clickedFile.canRead()) {
			Toast.makeText(getActivity(), "Sorry, permission denied.", Toast.LENGTH_SHORT).show();
			return;
		}
		if(clickedFile.isDirectory()) {

			ft.replace(R.id.container_for_content_listview, DirFragment.newInstance(clicedFilePath), "ReplaceParent");
			ft.addToBackStack("ReplaceParent");
			ft.commit();
		}
		
	}


	// 由item消失出发的onCheckedChanged buttonView不存在。
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
			Log.d(Facade.tag, buttonView.toString());
			int pos = getListView().getPositionForView(buttonView);
			if(pos != ListView.INVALID_POSITION) {
				Log.v(Facade.tag, pos + "");
				FileItem p = filesToAdapter.get(pos);
				p.setSelected(isChecked);
				Toast.makeText(getActivity(), "Item: " + p.getFile().getName() + " State: " + isChecked + "Position: " + pos + ".", Toast.LENGTH_SHORT).show();
				
			}
		
	}
	
	

	@Override
	public void onClick(View buttonView) {

		Log.d(Facade.tag, buttonView.toString());
		int pos = getListView().getPositionForView(buttonView);
		if(pos != ListView.INVALID_POSITION) {
			Log.v(Facade.tag, pos + "");
			FileItem p = filesToAdapter.get(pos);
			p.setSelected(((CheckBox)buttonView).isChecked());
			Toast.makeText(getActivity(), "Item: " + p.getFile().getName() + " State: " + buttonView.isSelected() + "Position: " + pos + ".", Toast.LENGTH_SHORT).show();	
			if(((CheckBox)buttonView).isChecked()) {
				selectedFiles.add(filesToAdapter.get(pos).getFile());
			} else {
				selectedFiles.remove(filesToAdapter.get(pos).getFile());
			}
			
			for(File file : selectedFiles) {
				Log.d(Facade.tag, "Selected file: " + file.toString());
			}
		}
		
		
	}
	
	@Override
	public void onStart() {
		Log.v(Facade.tag, "in onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.v(Facade.tag, "in onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.v(Facade.tag, "in onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.v(Facade.tag, "in onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.v(Facade.tag, "in onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.v(Facade.tag, "in onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.v(Facade.tag, "in onDetach");
		super.onDetach();
	}

}
