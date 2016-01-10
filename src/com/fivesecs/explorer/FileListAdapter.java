package com.fivesecs.explorer;

import java.io.File;
import java.util.List;

import com.fivesecs.explorer.utils.FileItem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class FileListAdapter extends BaseAdapter {

	private Bitmap mImage;
	private Bitmap mAudio;
	private Bitmap mRar;
	private Bitmap mVideo;
	private Bitmap mFolder;
	private Bitmap mApk;
	private Bitmap mOthers;
	private Bitmap mTxt;
	private Bitmap mWeb;
	List<FileItem> mFileItems;

	private DirFragment mContext;
	// private List<File> mFiles;

	public FileListAdapter(DirFragment dirFragment, List<FileItem> fileItems) {
		// mFiles = files;
		mFileItems = fileItems;

		mContext = dirFragment;

		mImage = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.picture);
		mAudio = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.audio);
		mVideo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.video);
		mApk = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.apk);
		mTxt = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.text);
		mOthers = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.others);
		mFolder = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.folder);
		mRar = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.apk);
		mWeb = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.web);
	}

	public int getCount() {
		return mFileItems.size();
	}

	public Object getItem(int position) {
		return mFileItems.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	

	public View getView(int position, View convertView, ViewGroup viewgroup) {
		View v = convertView;
		ViewHolder viewHolder = null;

		/**
		 * @author Bone Get the ViewHolder;
		 */
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater mLI = mContext.getActivity().getLayoutInflater();
			v = mLI.inflate(R.layout.dir_list_item, null);
			viewHolder.mIV = (ImageView) v.findViewById(R.id.img_item_icon);
			viewHolder.mTV = (TextView) v.findViewById(R.id.txt_item_name);
			viewHolder.mCB = (CheckBox) v.findViewById(R.id.chk_item_selecting);
			viewHolder.mCB.setOnClickListener((DirFragment) mContext);
			// viewHolder.mCB.setOnCheckedChangeListener((DirFragment)
			// mContext);
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}

		/**
		 * @author Bone Set list item state by viewHolder;
		 */
		// Set the check box state.
		viewHolder.mCB.setChecked(mFileItems.get(position).isSelected());

		// Set the file name.
		if (mContext.getPath().equals(File.separator)) {
			viewHolder.mTV.setText(mFileItems.get(position).getFile().getName());
		} else {
			if (position == 0) {
				viewHolder.mTV.setText("..");
			} else
				viewHolder.mTV.setText(mFileItems.get(position).getFile().getName());
		}

		// Set file icons.
		if (mFileItems.get(position).getFile().isDirectory()) {
			viewHolder.mIV.setImageBitmap(mFolder);
		} else {
			String fileName = mFileItems.get(position).getFile().getName();

			if (fileName.contains(".")) {
				String fileEnds = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				if (fileEnds.equals("m4a") || fileEnds.equals("mp3") || fileEnds.equals("mid") || fileEnds.equals("xmf")
						|| fileEnds.equals("ogg") || fileEnds.equals("wav")) {
					viewHolder.mIV.setImageBitmap(mVideo);
				} else if (fileEnds.equals("3gp") || fileEnds.equals("mp4")) {
					viewHolder.mIV.setImageBitmap(mAudio);
				} else if (fileEnds.equals("jpg") || fileEnds.equals("gif") || fileEnds.equals("png")
						|| fileEnds.equals("jpeg") || fileEnds.equals("bmp")) {
					viewHolder.mIV.setImageBitmap(mImage);
				} else if (fileEnds.equals("apk")) {
					viewHolder.mIV.setImageBitmap(mApk);
				} else if (fileEnds.equals("txt")) {
					viewHolder.mIV.setImageBitmap(mTxt);
				} else if (fileEnds.equals("zip") || fileEnds.equals("rar")) {
					viewHolder.mIV.setImageBitmap(mRar);
				} else if (fileEnds.equals("html") || fileEnds.equals("htm") || fileEnds.equals("mht")) {
					viewHolder.mIV.setImageBitmap(mWeb);
				} else {
					viewHolder.mIV.setImageBitmap(mOthers);
				}
			} else {
				viewHolder.mIV.setImageBitmap(mOthers);
			}

		}

		return v;
	}

	class ViewHolder {
		ImageView mIV;
		TextView mTV;
		CheckBox mCB;
	}
}