package org.dragonfly.lubot.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;

import org.dragonfly.lubot.mvc.ModelSupport;
import org.dragonfly.lubot.mvc.ViewChangeEvent;
import org.dragonfly.lubot.mvc.ViewChangeListener;
import org.dragonfly.lubot.mvc.ViewSupport;

public class SimpleController extends BaseController {

	public SimpleController(ModelSupport<?> modelSupport,
			ViewSupport<?> viewSupport) {
		super(modelSupport, viewSupport);
	}

	@Override
	public void init(ModelSupport<?> modelSupport, ViewSupport<?> viewSupport) {
		Field[] fields = modelSupport.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			viewSupport.getView();
		}
	}

	private <M, V> void bindProperty(final ModelSupport<M> modelSupport,
			final ViewSupport<V> viewSupport, final String propertyName) {
		modelSupport.addPropertyChangeListener(propertyName,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent event) {
						viewSupport.updateValue(event.getNewValue(), true);
					}
				});
		viewSupport.addChangeListener(new ViewChangeListener<V>() {

			@Override
			public void onChange(ViewChangeEvent<V> event) {
				modelSupport.updateProperty(propertyName, event.getNewValue(),
						true);
			}
		});
	}

}
