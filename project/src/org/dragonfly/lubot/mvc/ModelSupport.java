package org.dragonfly.lubot.mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;

import org.dragonfly.lubot.util.LuLog;

public class ModelSupport<T> {
	private T bean;
	protected PropertyChangeSupport pcs;
	
	public ModelSupport(T bean) {
		if(bean==null) throw new IllegalArgumentException();
		this.bean = bean;
		pcs = new PropertyChangeSupport(bean);
	}
	
	public T getModel(){
		return this.bean;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}

	public boolean hasListeners(String propertyName){
		return pcs.hasListeners(propertyName);
	}

	public void clearListeners() {
		PropertyChangeListener[] listeners = pcs.getPropertyChangeListeners();
		if (listeners != null) {
			for (PropertyChangeListener propertyChangeListener : listeners) {
				pcs.removePropertyChangeListener(propertyChangeListener);
			}
		}
	}
	
	public void updateProperty(String propertyName, Object value) {
		updateProperty(propertyName, value, false);
	}

	public void updateProperty(String propertyName, Object newValue,
			boolean isSilent) {
		if (isEmpty(propertyName))
			return;
		
		Class<? extends Object> clazz = this.bean.getClass();
		Field field;
		
		try {
			field = clazz.getDeclaredField(propertyName);
			if(field==null) throw new IllegalArgumentException("The bean<"+clazz.getName()+"> has no property<"+propertyName+">!");
			
			field.setAccessible(true);
			Object oldValue = field.get(bean);
			field.set(bean, newValue);
			
			if (!isSilent) {
				pcs.firePropertyChange(propertyName, oldValue, newValue);
			}
		} catch (Exception e) {
			LuLog.e(e.getMessage());
		}
		
	}


	private boolean isEmpty(String s) {
		return s == null || s.length() <= 0;
	}

}
