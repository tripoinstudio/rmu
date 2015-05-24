package com.tripoin.rmu.model.api;

/**
 * Created by Achmad Fauzi on 1/25/2015.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Constant related to model that will be used by DAO to deliver Objects
 */
public interface ModelConstant {

    public final String TIME_STAMP_RESET = "01-01-2000 23:59:59.0";

    /*SELF TEST*/
    public final String SELF_TEST_TABLE = "self_test";

    public final String SELF_TEST_ID= "self_test_id";

    public final String ONE_CLICK_TEST_ID = "one_click_test_id";

    public final String RETRY_NUMBER = "retry_number";

    public final String START_ONE_CLICK_TEST = "start_test";

    public final String TEST_TYPE = "test_type";

    public final String DEVICE_ID = "device_id";

    public final String USER_CODE = "user_code";

    public final String TEST_STATUS = "test_status";

    public final String REASON = "reason";

    /*LATENCY*/
    public final String LATENCY_TABLE = "latency";


    /*DOWNLOAD*/
    public final String DOWNLOAD_TABLE = "download";


    /*UPLOAD*/
    public final String UPLOAD_TABLE = "upload";


    /*YST*/
    public final String YST_TABLE = "yst";


    /*BST*/
    public final String BST_TABLE = "bst";

    /*Menu*/
    public final String MENU_TABLE = "m_rmu_menu";

    public final String REST_MENU_TABLE = "master_menu";

    public final String MENU_ID = "menu_id";

    public final String MENU_CODE = "menu_code";

    public final String MENU_NAME = "menu_name";

    public final String MENU_TYPE = "menu_type";

    public final String MENU_PRICE = "menu_price";

    public final String MENU_STOCK = "menu_stock";

    public final String MENU_IMAGE_URL = "menu_image_url";

    public final String MENU_RATING = "menu_rating";

    /*Version*/
    public final String VERSION_TABLE = "m_rmu_version";

    public final String VERSION_ID = "version_id";

    public final String VERSION_NAMETABLE = "version_nametable";

    public final String VERSION_TIMESTAMP = "version_timestamp";
    /*Order List*/
    public final String ORDER_LIST_TABLE = "m_rmu_order_list";

    public final String ORDER_LIST_ID = "order_list_id";

    public final String ORDER_LIST_ORDER_ID = "order_list_order_id";

    public final String ORDER_LIST_CARRIAGE_NUMBER = "order_list_carriage_number";

    public final String ORDER_LIST_SEAT_NUMBER = "order_list_seat_number";

    public final String ORDER_LIST_TOTAL_PAID = "order_list_total_paid";

    public final String ORDER_LIST_ORDER_TIME = "order_list_order_time";

    public final String ORDER_LIST_PROCESS_STATUS = "order_list_process_status";

    public final String ORDER_LIST_WAITING_STATUS = "order_list_waiting_status";

    public final String REST_ORDER_HEADER_TABLE = "trx_order_header";

    public final String REST_APPLICATION_VERSION = "apk_version";

    /*Override model const*/


    /*Carriage*/
    public final String CARRIAGE_TABLE = "m_rmu_carriage";

    public final String REST_CARRIAGE_TABLE = "master_carriage";

    public final String CARRIAGE_CODE = "carrige_code";

    public final String CARRIAGE_NO = "carriage_no";

    public final String CARRIAGE_REMARKS = "carriage_remarks";

    public final String CARRIAGE_ID = "carriage_id";

    /*Seat*/
    public final String SEAT_TABLE = "m_rmu_seat";

    public final String REST_SEAT_TABLE = "master_seat";

    public final String SEAT_CODE = "seat_code";

    public final String SEAT_NO = "seat_no";

    public final String SEAT_REMARKS = "seat_remarks";

    public final String SEAT_ID = "seat_id";

    /*Train*/
    public final String TRAIN_TABLE = "m_rmu_train";

    public final String REST_TRAIN_TABLE = "master_train";

    public final String TRAIN_CODE = "train_code";

    public final String TRAIN_NO = "train_no";

    public final String TRAIN_REMARKS = "train_remarks";

    public final String TRAIN_ID = "train_id";

    /*Detail Order*/
    public final String ORDER_DETAIL_TABLE = "m_rmu_order_detail";

    public final String ORDER_DETAIL_ID = "order_detail_id";

    public final String REST_ORDER_DETAIL_TABLE = "trx_order_detail";

    public final String ORDER_DETAIL_ORDER_HEADER_NO = "order_detail_order_header_no";

    public final String ORDER_DETAIL_ORDER_DETAIL_TOTAL_ORDER = "order_detail_order_detail_total_order";

    public final String ORDER_DETAIL_ORDER_DETAIL_TOTAL_AMOUNT = "order_detail_order_detail_total_amount";

    public final String ORDER_DETAIL_ORDER_HEADER_STATUS = "order_header_status";

    public final String ORDER_DETAIL_ORDER_HEADER_STATUS_WAITING = "order_header_status_waiting";

    public final String ORDER_DETAIL_USER_CODE = "order_detail_user_code";

    public final String ORDER_DETAIL_MENU_CODE = "order_detail_menu_code";

    public final String ORDER_DETAIL_MENU_NAME = "order_detail_menu_name";

    public final String ORDER_DETAIL_SEAT_CODE = "order_detail_seat_code";

    public final String ORDER_DETAIL_CARRIAGE_CODE = "order_detail_carriage_code";

    public final String ORDER_DETAIL_TRAIN_CODE = "order_detail_train_code";


    public final String REST_MASTER_TABLE = "master_component";

    /*Menu*/
    public final String IMAGE_TABLE = "m_rmu_image";

    public final String REST_IMAGE_TABLE = "master_image";

    public final String IMAGE_ID = "image_id";

    public final String IMAGE_CODE = "image_code";

    public final String IMAGE_NAME = "image_name";

    public final String IMAGE_STATUS = "image_status";

    /*temporary*/
    public final String ORDER_TEMP_TABLE = "m_rmu_order_temp";

    public final String ORDER_TEMP_QUANTITY = "order_temp_quantity";

    public final String ORDER_TEMP_PRICE = "order_temp_price";

    public final String ORDER_TEMP_MENU_ID = "order_temp_menu_id";

    public final String ORDER_TEMP_SEAT_ID = "order_temp_seat_id";

    public final String ORDER_TEMP_CARRIAGE_ID = "order_temp_carriage_id";

    public final String ORDER_TEMP_TRAIN_ID = "order_temp_train_id";

    public final String ORDER_TEMP_ID = "order_temp_id";


}
