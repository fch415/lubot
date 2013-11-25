package org.dragonfly.lubot.core;


public interface ViewChangeListener<T>{
	
	public void onChange(ViewChangeEvent<T> event);

}