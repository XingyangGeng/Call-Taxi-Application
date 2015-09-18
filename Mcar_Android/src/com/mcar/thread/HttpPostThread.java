package com.mcar.thread;

import android.os.Handler;
import android.os.Message;

import com.mcar.net.MyPost;



public class HttpPostThread implements Runnable {

	private Handler hand;
	private String url;
	private String value;
	private String img = "";
	private MyPost myPost = new MyPost();

	public HttpPostThread(Handler hand, String endParamerse, String value,
			String img) {
		this.hand = hand;
		url = endParamerse;
		this.value = value;
		this.img = img;
	}

	public HttpPostThread(Handler hand, String endParamerse, String value) {
		this.hand = hand;
		url = endParamerse;
		this.value = value;
	}

	@Override
	public void run() {
		Message msg = hand.obtainMessage();
		String result = null;
		if (img.equalsIgnoreCase("")) {
			result = myPost.doPost(url, value);
		} else {
			result = myPost.doPost(url, img, value);
		}
		msg.what = 200;
		msg.obj = result;
		hand.sendMessage(msg);

	}

}
