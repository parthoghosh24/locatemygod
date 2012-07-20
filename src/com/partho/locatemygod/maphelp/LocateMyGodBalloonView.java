package com.partho.locatemygod.maphelp;

import com.google.android.maps.OverlayItem;
import com.partho.locatemygod.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocateMyGodBalloonView extends FrameLayout{

	private RelativeLayout layout;
	private TextView title;
	private TextView snippet;
	
	public LocateMyGodBalloonView(Context context, int balloonBottomOffset) {
		super(context);		
		setPadding(10, 0, 10, balloonBottomOffset);
		layout = new RelativeLayout(context);
		layout.setVisibility(VISIBLE);
		
		LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.locatemygodballoonoverlay, layout);
		title = (TextView)view.findViewById(R.id.map_balloon_title_text);
		snippet= (TextView)view.findViewById(R.id.map_balloon_snippet_text);
		ImageView close = (ImageView)view.findViewById(R.id.map_balloon_close_button);
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				layout.setVisibility(GONE);				
			}
		});
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity= Gravity.NO_GRAVITY;
		addView(layout, params);
	}
	
	public void setData(OverlayItem item)
	{
		layout.setVisibility(VISIBLE);
		if(item.getTitle()!= null)
		{
			title.setVisibility(VISIBLE);
			title.setText(item.getTitle());
		}
		else
		{
			title.setVisibility(GONE);
		}
		
		if(item.getSnippet()!= null)
		{
			snippet.setVisibility(VISIBLE);
			snippet.setText(item.getSnippet());
		}
		else
		{
			snippet.setVisibility(GONE);
		}
		
	}

}
