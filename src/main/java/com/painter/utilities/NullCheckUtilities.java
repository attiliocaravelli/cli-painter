package com.painter.utilities;

public class NullCheckUtilities {
	public static boolean isNull(Object...params) {
		for (Object param:params) {
			if (param == null) return true;
		}
		return false;
	}
}
