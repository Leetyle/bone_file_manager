package com.fivesecs.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

class FileListAdapter extends BaseAdapter {

	private Bitmap mImage;
	private Bitmap mAudio;
	private Bitmap mRar;
	private Bitmap mVideo;
	private Bitmap mFolder;
	private Bitmap mApk;
	private Bitmap mOthers;
	private Bitmap mTxt;
	private Bitmap mWeb;

	private Context mContext;
	private List<File> mFiles;

	public FileListAdapter(Context context, List<File> files) {
		mFiles = files;

		mContext = context;

		mImage = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_filter_hdr_black_48dp);
		mAudio = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_filter_hdr_black_48dp);
		mVideo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_filter_hdr_black_48dp);
		mApk = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_filter_hdr_black_48dp);
		mTxt = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_filter_hdr_black_48dp);
		mOthers = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_filter_hdr_black_48dp);
		mFolder = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.folder);
		mRar = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_filter_hdr_black_48dp);
		mWeb = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_filter_hdr_black_48dp);
	}

	public int getCount() {
		return mFiles.size();
	}

	public Object getItem(int position) {
		return mFiles.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup viewgroup) {
		View v = convertView;
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater mLI = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = mLI.inflate(R.layout.dir_list_item, null);
			viewHolder.mIV = (ImageView) v.findViewById(R.id.img_item_icon);
			viewHolder.mTV = (TextView) v.findViewById(R.id.txt_item_name);
			viewHolder.mCB = (CheckBox)v.findViewById(R.id.chk_item_selecting);
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}
		
		viewHolder.mCB.setChecked(false);

		if (DirFragment.path.equals(File.separator)) {
			viewHolder.mTV.setText(mFiles.get(position).getName());
		} else {
			if(position == 0) {
				viewHolder.mTV.setText("..");
			} else viewHolder.mTV.setText(mFiles.get(position).getName());
		}
		if (mFiles.get(position).isDirectory()) {
			viewHolder.mIV.setImageBitmap(mFolder);
		} else {
			String fileName = mFiles.get(position).getName();
			
			if (fileName.contains(".")) {
				String fileEnds = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length() - 1);
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