package org.dragonfly.lubot.core;


public interface ViewAccessor<T> {
	
	public void setValue(T view, Object newValue);
	public Object getValue(T view);

}
