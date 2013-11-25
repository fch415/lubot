package org.dragonfly.lubot.core;

import java.util.HashMap;
import java.util.Map;

import org.dragonfly.lubot.accessor.TextViewAccessor;

import android.widget.TextView;

public class ViewAccessorManager {
	
	private static Map<Class<?>, ViewAccessor<?>> map = null;
	
	static {
		map = new HashMap<Class<?>, ViewAccessor<?>>();
		map.put(TextView.class, new TextViewAccessor());
	}
	
	public static <T> void register(Class<T> clazz, ViewAccessor<T> accessor){
		map.put(clazz, accessor);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> ViewAccessor<T> find(Class<T> clazz){
		return (ViewAccessor<T>) map.get(clazz);
	}

}
