package com.mcbath.rebecca.moviecontrollerjni.model;

/**
 * Created by Rebecca McBath
 * on 1/17/19.
 */

/**
 * This Class represent a Default native object.
 * {@link JNIObject#nativeHandle} is the pointer to this object
 */

public class JNIObject {

	private long nativeHandle;

	public JNIObject(long ptr){
		this.nativeHandle = ptr;
	}

	public long getNativeHandle(){
		return nativeHandle;
	}

}
