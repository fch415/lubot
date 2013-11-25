package org.dragonfly.lubot.core;

import java.util.ArrayList;
import java.util.List;

public class ViewSupport<T> {
	T view;
	ViewAccessor<T> accessor;

	@SuppressWarnings("unchecked")
	public ViewSupport(T view) {
		this.view = view;
		this.listeners = new ArrayList<ViewChangeListener<T>>();

		this.accessor = (ViewAccessor<T>) ViewAccessorManager.find(this.view
				.getClass());
	}

	public T getView() {
		return view;
	}
	
	public Object getValue(){
		return this.accessor.getValue(view);
	}
	
	private List<ViewChangeListener<T>> listeners = null;

	public void addChangeListener(ViewChangeListener<T> listener) {
		this.listeners.add(listener);
	}

	public void removeChangeListener(ViewChangeListener<T> listener) {
		this.listeners.remove(listener);
	}

	public void clearListeners() {
		this.listeners.clear();
	}

	private void fireChangeEvent(Object oldValue, Object newValue) {
		ViewChangeEvent<T> event = new ViewChangeEvent<T>(this.view, oldValue,
				newValue);
		for (ViewChangeListener<T> listener : listeners) {
			listener.onChange(event);
		}
	}

	public void updateValue(Object newValue) {
		this.updateValue(newValue, false);
	}

	public void updateValue(Object newValue, boolean isSlient) {
		Object oldValue = this.accessor.getValue(view);

		this.accessor.setValue(view, newValue);
		if (!isSlient)
			this.fireChangeEvent(oldValue, newValue);
	}
}
