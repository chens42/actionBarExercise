package com.androidbook.customLayouts;

import com.androidbook.customLayouts.R;

import android.util.Log;
import android.view.MenuItem;

public class TestCustomLayoutlActivity extends MonitoredDebugActivity
implements IReportBack
{
	public static final String tag="TestCompoundControlActivity";
	
	public TestCustomLayoutlActivity()
	{
		super(R.menu.main_menu,tag);
		this.retainState();
	}
    protected boolean onMenuItemSelected(MenuItem item)
    {
    	Log.d(tag,item.getTitle().toString());
    	if (item.getItemId() == R.id.menu_no_op)
    	{
    		return true;
    	}
    	return true;
    }
}//eof-class