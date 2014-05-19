package org.dragonfly.lubot.controller;

import org.dragonfly.lubot.mvc.ModelSupport;
import org.dragonfly.lubot.mvc.ViewSupport;


public abstract class BaseController {
	
	private ModelSupport<?> modelSupport;
	private ViewSupport<?> viewSupport;
	
	public BaseController(ModelSupport<?> modelSupport, ViewSupport<?> viewSupport){
		this.modelSupport = modelSupport;
		this.viewSupport = viewSupport;
		
		this.init(this.modelSupport, this.viewSupport);
	}

	public abstract void init(ModelSupport<?> modelSupport, ViewSupport<?> viewSupport);

	public ModelSupport<?> getModelSupport() {
		return modelSupport;
	}

	public ViewSupport<?> getViewSupport() {
		return viewSupport;
	}
	
	
	
}
