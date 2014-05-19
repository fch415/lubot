package org.dragonfly.lubot.mvc;


public interface ViewChangeListener<T>{
	
	public void onChange(ViewChangeEvent<T> event);

}