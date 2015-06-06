package com.tripoin.core.rest.util;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class RestConstant {
	
	private static final String HOME = "/tripoin/wscontext";
	public static final String WS_VERSION = HOME.concat("/version");
	public static final String WS_CONNECTION = HOME.concat("/connection");
	public static final String WS_TRAIN = HOME.concat("/train");
	public static final String WS_CARRIAGE = HOME.concat("/carriage");
	public static final String WS_SEAT = HOME.concat("/seat");
	public static final String WS_MENU = HOME.concat("/menu");
	public static final String WS_IMAGE = HOME.concat("/image");
	public static final String WS_ORDER_HEADER = HOME.concat("/getOrderHeader");
	public static final String WS_ORDER_DETAIL = HOME.concat("/getOrderDetail");

	public static final String VERSION_MASTER_TRAIN = "master_train";
	public static final String VERSION_MASTER_CARRIAGE = "master_carriage";
	public static final String VERSION_MASTER_SEAT = "master_seat";
	public static final String VERSION_MASTER_MENU = "master_menu";
	public static final String VERSION_MASTER_IMAGE = "master_image";
	public static final String VERSION_MASTER_COMPONENT = "master_component";
	public static final String VERSION_ORDER_HEADER = "trx_order_header";
	
	public static final String ACCEPT_JSON = "application/json";
	
}
