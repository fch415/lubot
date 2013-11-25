package org.dragonfly.lubot.core;


public class ModelAndViewBinder {
	
	protected ViewSupport<?> viewSupport;
	protected ModelSupport modelSupport;
	
	public ModelAndViewBinder(ViewSupport<?> viewSupport, ModelSupport modelSupport){
		this.viewSupport = viewSupport;
		this.modelSupport = modelSupport;
	}

	
}
