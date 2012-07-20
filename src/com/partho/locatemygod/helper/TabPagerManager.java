package com.partho.locatemygod.helper;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TabHost.OnTabChangeListener;

public class TabPagerManager extends FragmentPagerAdapter implements OnTabChangeListener, OnPageChangeListener{

	private final Context mContext;
	private final TabHost mTabHost;
	private final ViewPager mViewPager;
	private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>(); 
	
	static final class TabInfo{
		@SuppressWarnings("unused")
		private final String tabTag;
		private final Class<?> mClass;
		private final Bundle args;
		
		TabInfo(String _tag, Class<?> _mClass, Bundle _args) {
			tabTag= _tag;
			mClass = _mClass;
			args = _args;
		}
	}
	
	static class DummyTabFactory implements TabHost.TabContentFactory //helps create tab content
	{

		private final Context mTabFactoryContext;
		
		public DummyTabFactory (Context _mContext)
		{
			mTabFactoryContext= _mContext;
		}
		
		@Override
		public View createTabContent(String tag) {
			View view = new View(mTabFactoryContext);
			view.setMinimumWidth(0);
			view.setMinimumHeight(0);
			return view;
		}
		
	}
	
	public TabPagerManager(FragmentActivity activity, TabHost tabHost, ViewPager pager) {
		super(activity.getSupportFragmentManager());
		mContext = activity;
		mTabHost = tabHost;
		mViewPager = pager;
		mTabHost.setOnTabChangedListener(this);
		mViewPager.setAdapter(this);
		mViewPager.setOnPageChangeListener(this);
	}

	public void addTab(TabSpec tabSpec, Class<?> clss, Bundle args )
	{
		tabSpec.setContent(new DummyTabFactory(mContext));
		String tag =tabSpec.getTag();
		TabInfo info = new TabInfo(tag, clss, args);
		mTabs.add(info);
		mTabHost.addTab(tabSpec);
		notifyDataSetChanged();
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {		
	}

	@Override
	public void onPageSelected(int position) {
		TabWidget widget = mTabHost.getTabWidget();
		int oldFocusability = widget.getDescendantFocusability();
		widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		mTabHost.setCurrentTab(position);
		widget.setDescendantFocusability(oldFocusability);
	}

	@Override
	public void onTabChanged(String tabId) {
		int position = mTabHost.getCurrentTab();
		mViewPager.setCurrentItem(position);
		
	}

	@Override
	public Fragment getItem(int position) {		
		TabInfo info = mTabs.get(position);				
		Fragment fragment =	Fragment.instantiate(mContext, info.mClass.getName(), info.args);		
		return fragment;	
		
	}
		

	@Override
	public int getCount() {		
		return mTabs.size();
	}
	
}