package com.fivesecs.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fivesecs.explorer.listeners.ModeCallback;
import com.fivesecs.explorer.utils.ExpolererUtils;
import com.fivesecs.explorer.utils.FileItem;
import com.fivesecs.explorer.utils.FileOperations;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class DirFragment extends ListFragment implements OnClickListener {
	private String path;
	private static final String PATH = "path";
	private FileListAdapter adapter = null;
	private List<FileItem> filesToAdapter = new ArrayList<FileItem>();
	private List<File> selectedFiles = new ArrayList<File>();
	

	// 分析一下为什么要用newInstance()????
	public static DirFragment newInstance(String filePath) {
		DirFragment f = new DirFragment();
		Bundle args = new Bundle();
		args.putString(PATH, filePath);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		path = getArguments().getString(PATH);
	}
	
	
	private void initDirListItem(String path) {
		filesToAdapter = ExpolererUtils.getFileItemList(new File(path));
		adapter = new FileListAdapter(this, filesToAdapter);
		setListAdapter(adapter);
		
		ListView lv = getListView();
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		lv.setMultiChoiceModeListener(new ModeCallback(getActivity(), this));
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// path = this.getArguments().getString(path);

		initDirListItem(path);
		((MainActivity)getActivity()).setToolbarSubtitle(path);
		
	}


	
	
//
//	public FileListAdapter getAdapter() {
//		return adapter;
//	}

	public String getPath() {
		return path;
	}



	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		File clickedFile = filesToAdapter.get(position).getFile();
		String clicedFilePath = clickedFile.toString();
		if (!clickedFile.canRead()) {
			Toast.makeText(getActivity(), "Sorry, permission denied.", Toast.LENGTH_SHORT).show();
			return;
		}
		if (clickedFile.isDirectory()) {

			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.replace(R.id.container_for_content_listview, DirFragment.newInstance(clicedFilePath), "ReplaceParent");
			ft.addToBackStack("ReplaceParent");
			ft.commit();
			((MainActivity)getActivity()).setToolbarSubtitle(clicedFilePath);
			// path = clicedFilePath;
		} else {
			FileOperations.openFile(getActivity(), clickedFile);
		}

	}

	// 由item消失出发的onCheckedChanged buttonView不存在。
//	@Override
//	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//		Log.d(MainActivity.tag, buttonView.toString());
//		int pos = getListView().getPositionForView(buttonView);
//		if (pos != ListView.INVALID_POSITION) {
//			Log.v(MainActivity.tag, pos + "");
//			FileItem p = filesToAdapter.get(pos);
//			p.setSelected(isChecked);
//			Toast.makeText(getActivity(),
//					"Selected: " + getListView().getCheckedItemCount(),
//					Toast.LENGTH_SHORT).show();
//			
//			
//
//		}
//
//	}

	@Override
	public void onClick(View buttonView) {

		Log.d(MainActivity.tag, buttonView.toString());
		int pos = getListView().getPositionForView(buttonView);
		if (pos != ListView.INVALID_POSITION) {
			Log.v(MainActivity.tag, pos + "");
			FileItem p = filesToAdapter.get(pos);
			
			// 把当前是否选中的状态保存到p中
			p.setSelected(((CheckBox) buttonView).isChecked());
			Toast.makeText(getActivity(),
					"Selected: " + getListView().getCheckedItemCount(),
					Toast.LENGTH_SHORT).show();
			if (((CheckBox) buttonView).isChecked()) {
				selectedFiles.add(filesToAdapter.get(pos).getFile());
				getListView().setItemChecked(pos, true);
			} else {
				selectedFiles.remove(filesToAdapter.get(pos).getFile());
				getListView().setItemChecked(pos, false);
			}

		}

	}
	
	public List<File> getSelectedFiles() {
		return selectedFiles;
	}



}
