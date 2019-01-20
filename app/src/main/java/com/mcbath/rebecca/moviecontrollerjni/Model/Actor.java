package com.mcbath.rebecca.moviecontrollerjni.model;

/**
 * Created by Rebecca McBath
 * on 1/17/19.
 */
public class Actor extends JNIObject {

	public Actor(long ptr) {
		super(ptr);
	}

	public native int getAge();
	public native String getName();
	public native String getImageUrl();
}
