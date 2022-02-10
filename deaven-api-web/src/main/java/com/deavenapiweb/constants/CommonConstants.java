package com.deavenapiweb.constants;

/**
 * 共用变量constants
 *
 * @author xy
 * @since 2022-02-09 11:59:34
 */
public class CommonConstants {

    /**
     * 上传编号
     */
    public final static String UPLOAD_TASKNO = "UP";

    /**
     * 数据回传编号
     */
    public final static String PASSBACK_TASKNO = "PB";

    //逗号
    public final static String SYMBOL_COMMA = ",";

    //行程id
    public final static String PARAM_STROKID = "strokeId";

    //名称重复异常
    public final static String ERROR_DUPLICATENAME = "名称重复";
    //参数为空
    public final static String ERROR_PARAMETERNULL = "参数为空";
    //场景id列表sceneIds
    public final static String PARAM_SCENEIDS="sceneDataIds";

    //校验数字以逗号分隔字符串正则表达式
    public final static String REGULAR_INT_COMMA="\\d+(,\\d+)*";

    //场景提取任务切分真值ID最大阀值
    public final static int THRESHOLD_SCENT_EXTRACE_TASK_MAX_IDS = 3;

    //两条场景提取开始时间和结束时间间隔毫秒数
    public final static int THRESHOLD_MAX_INTERVAL = 2000;

    //两个会话最多可接受的间隔时间
    public final static int THRESHOLD_SESSION_MAX_INTERVAL = 2000;

    //-1
    public final static int NUMBER_NEGATIVE_ONE = -1;

    //-2
    public final static int NUMBER_NEGATIVE_TWO = -2;

    //数字2
    public final static int NUMBER_TWO = 2;
    //数字3
    public final static int NUMBER_THREE = 3;
    //数字4
    public final static int NUMBER_FOUR = 4;
    //数字5
    public final static int NUMBER_FIVE = 5;
    //数字1
    public final static int NUMBER_ONE = 1;

    //数字0
    public final static int NUMBER_ZERO=0;

    //数字6
    public final static int NUMBER_SIX=6;

    //数字10
    public final static int NUMBER_TEN=10;

    //数字三千
    public final static int NUMBER_THREE_THOUSAND=3000;
    //字符串1
    public final static String STRING_ONE="1";
    //字符串2
    public final static String STRING_TWO="2";
    //字符串3
    public final static String STRING_THREE="3";
    //字符串4
    public final static String STRING_FOUR="4";
    //字符串10
    public final static String STRING_TEN="10";
    //字符串0
    public final static String STRING_ZERO="0";

    //时间格式yyyyMMddHH
    public final static String DATE_FORMATE_HH="yyyyMMddHH";

    //redis key过期
    public final static int EXPRIE_REDIS_KEY_30=30;

    //redis key过期
    public final static long EXPRIE_TASKINFO = 24 * 3600L;



    //执行成功
    public final static String TASK_SUCCESS = "SUCCESS :";



    public final static String DEFAULT_USER = "admin";

    public final static String DEFAULT_SCRIPT_TYPE="python3";

    public final static String DEFAULT_EMPTY_FLAG="empty";

    public final static String STRING_CSV_FILE_DIR="csv";

    public final static String STRING_PYTHON_FILE_DIR="py";
}
