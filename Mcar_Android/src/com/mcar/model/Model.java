package com.mcar.model;

import com.mcar.info.DriverLoc;
import com.mcar.info.UserInfo;

public class Model {
	
	public static String HTTPURL = "http://172.16.24.52:8080/mcar/";
	public static String USERHEADURL = "http://172.16.24.52:80/KidBuddies/Userimg/";
	public static String QIMGURL = "http://172.16.24.52:80/KidBuddies/Valueimg/";
	public static boolean IMGFLAG = false;
	public static UserInfo MYUSERINFO = null;
	public static String APPKEY = "fc6f181194f4f6e24e3a7842d5902498";
	public static final String APP_KEY = "3987368837";
	public static String SENDREQUESTCAR = "handleServlet";
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";
}
