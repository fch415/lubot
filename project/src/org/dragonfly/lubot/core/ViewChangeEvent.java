package org.dragonfly.lubot.core;


public class ViewChangeEvent<T> {
	private T source;
	private Object newValue;
	private Object oldValue;
	
	public ViewChangeEvent(T source, Object oldValue, Object newValue) {
		this.source = source;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	public T getSource(){
		return source;
	}

	public Object getNewValue() {
		return newValue;
	}

	public Object getOldValue() {
		return oldValue;
	}

}
