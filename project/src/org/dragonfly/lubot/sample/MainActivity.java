package org.dragonfly.lubot.sample;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import org.dragonfly.lubot.R;
import org.dragonfly.lubot.mvc.ModelSupport;
import org.dragonfly.lubot.mvc.ViewChangeEvent;
import org.dragonfly.lubot.mvc.ViewChangeListener;
import org.dragonfly.lubot.mvc.ViewSupport;
import org.dragonfly.lubot.sample.model.SampleBean;
import org.dragonfly.lubot.util.LuLog;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getLayoutInflater();
		ViewGroup layout = (ViewGroup)inflater.inflate(R.layout.activity_main, null);
		setContentView(layout);
		
		SampleBean bean = new SampleBean();
		final ModelSupport<SampleBean> ms = new ModelSupport<SampleBean>(bean);
		
		TextView view = (TextView)findViewById(R.id.textView1);
		final ViewSupport<TextView> vs = new ViewSupport<TextView>(view);
		
		ms.addPropertyChangeListener("name", new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				vs.updateValue(event.getNewValue(), true);
				
				LuLog.d(vs.getValue().toString());
			}
		});
		vs.addChangeListener(new ViewChangeListener<TextView>() {
			
			@Override
			public void onChange(ViewChangeEvent<TextView> event) {
				ms.updateProperty("name", event.getNewValue(), true);
				
				LuLog.d(ms.getModel().getName());
			}
		});
		
//		ms.updateProperty("name", "123334hello!!!!");
		
		
		vs.updateValue("okoadakda!!!");
		
//		final Model model = new Model();
//		SampleController controller = new SampleController(layout);
//		controller.updateModel(model);
//		
//		model.updateProperty(R.id.textView1+"", "updateProperty!");
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put(R.id.textView1+"", "update!");
//		controller.getModel().update(map, false);
		
//		final LuToggleButton toggleButton = (LuToggleButton)findViewById(R.id.toggleButton1);
		
//		DefaultTextViewBinder textBinder = new DefaultTextViewBinder();
//		textBinder.bind(toggleButton, model, "hello");
		
		
		
//		toggleButton.updateValue(false, "update by view!");
		
	}

}
