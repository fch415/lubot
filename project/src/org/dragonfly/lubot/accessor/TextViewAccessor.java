package org.dragonfly.lubot.accessor;

import org.dragonfly.lubot.core.ViewAccessor;

import android.widget.TextView;

public class TextViewAccessor implements ViewAccessor<TextView>{

	public void setValue(TextView view, Object newValue) {
		view.setText(newValue.toString());
	}
	
	public String getValue(TextView view) {
		return view.getText().toString();
	}

}
