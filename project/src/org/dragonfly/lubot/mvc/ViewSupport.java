package org.dragonfly.lubot.mvc;

import java.util.ArrayList;
import java.util.List;

public class ViewSupport<T> {
	T view;
	ViewAccessor<T> accessor;

	@SuppressWarnings("unchecked")
	public ViewSupport(T view) {
		if (view == null) {
			throw new IllegalArgumentException(this.getClass()
					+ " 's View is null!");
		}

		this.view = view;
		this.listeners = new ArrayList<ViewChangeListener<T>>();
	}

	public ViewAccessor<T> getAccessor() {
		if (accessor == null) {
			accessor = (ViewAccessor<T>) ViewAccessorManager.find(this.view
					.getClass());
		}
		return accessor;
	}

	public void setAccessor(ViewAccessor<T> accessor) {
		this.accessor = accessor;
	}

	public T getView() {
		return view;
	}

	public Object getValue() {
		ViewAccessor<T> accessor = this.getAccessor();
		return accessor == null ? null : accessor.getValue(view);
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
		ViewAccessor<T> va = this.getAccessor();
		if (va == null) {
			throw new IllegalAccessError(this.getView()
					+ " 's ViewAccessor is null!");
		}

		Object oldValue = va.getValue(view);

		va.setValue(view, newValue);
		if (!isSlient)
			this.fireChangeEvent(oldValue, newValue);
	}
}
