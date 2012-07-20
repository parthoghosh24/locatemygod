package com.partho.locatemygod.aboutus;

import com.partho.locatemygod.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutUsFragment extends Fragment {

	private TextView feedbackText;
	private ImageButton fbLikeUs;
	private ImageButton gplus;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.aboutusfragment, container, false);
		feedbackText = (TextView) view.findViewById(R.id.aboutus_feedback_text);
		fbLikeUs = (ImageButton) view
				.findViewById(R.id.aboutus_facebook_button);
		gplus = (ImageButton) view.findViewById(R.id.aboutus_gplus_button);
		Linkify.addLinks(feedbackText, Linkify.EMAIL_ADDRESSES);
		fbLikeUs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent fbLikeUsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/LocateMyGod"));
				startActivity(fbLikeUsIntent);
			}
		});

		gplus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent fbLikeUsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/108822902886724857088"));
				startActivity(fbLikeUsIntent);
			}
		});
		return view;
	}

}
