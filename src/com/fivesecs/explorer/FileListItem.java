package com.fivesecs.explorer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FileListItem extends RelativeLayout {

	CheckBox chx;
	TextView txt_name;
	TextView txt_date;
	TextView txt_content;
	ImageView img_icon;
	
	public FileListItem(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		
		// TODO Auto-generated constructor stub
	}
	public FileListItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize(context);
		// TODO Auto-generated constructor stub
	}
	private void initialize(Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.file_list_item, this);
		
		 chx = (CheckBox)findViewById(R.id.c_chk_item_selecting);
		 txt_name = (TextView)findViewById(R.id.c_txt_item_name);
		 txt_date = (TextView)findViewById(R.id.c_txt_item_date);
		 txt_content = (TextView)findViewById(R.id.c_txt_item_content);
		 img_icon = (ImageView)findViewById(R.id.c_img_item_icon);
	}	
	
	public void setSelected(Drawable selectedDrawable) {
		img_icon.setImageDrawable(selectedDrawable);
		// 可以设置两个然后重合起来。打对勾。
		if(!chx.isChecked()){
			chx.setChecked(true);
		}
		
	}
	
	public void setUnselected(){
		chx.setChecked(false);
	}
}
