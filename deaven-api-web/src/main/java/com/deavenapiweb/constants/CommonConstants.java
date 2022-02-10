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

    //真值数据ID列表processedDataIds
    public final static String PARAM_PROCESSEDDATAIDS = "processedDataIds";

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

    //场景提取主任务编号前缀
    public final static String SCENE_AUTO_TASK = "AUTOSEM";

    //场景提取主任务编号前缀
    public final static String SCENE_TASK_MAIN = "SEM";

    //场景提取主任务编号单次执行
    public final static String SCENE_TASK_SIGIN_MAIN = "SIGNSEM";

    //新建行程编号前缀
    public final static String STROKE_NUMBER = "STORKE";

    //场景提取子任务编号前缀
    public final static String SCENE_TASK_SUB = "SES";

    //行程评测记录编号
    public final static String STROKE_EVALUATION_RECODE = "EVASTROKE";

    //场景评测记录编号
    public final static String SCENE_EVALUATION_RECODE = "EVASCENE";

    //打包记录编号
    public final static String PACKAGE_RECODE = "PACK";

    //切片记录编号
    public final static String PICK_RECODE = "PICK";

    //真值处理记录编号
    public final static String PROCESS_RECODE = "PCE";

    //自动化处理任务编号前缀
    public final static String AUTO_TASK_PROCESS = "AP";

    //执行成功
    public final static String TASK_SUCCESS = "SUCCESS :";
    //场景提取redis队列名称
    public final static String SCENE_EXTARCE_QUEUENAME = "queue_scene_extrace";
    //真值处理redis队列名称
    public final static String PROCESSED_EXTARCE_QUEUENAME = "processed_extarce_result";

    //场景评测redis队列名称
    public final static String SCENE_EVALUATION_QUEUENAME = "queue_scene_evaluation";

    //行程评测redis队列名称
    public final static String STROKE_EVALUATION_QUEUENAME = "queue_stroke_evaluation";

    public final static String DEFAULT_USER = "admin";

    public final static String DEFAULT_SCRIPT_TYPE="python3";

    public final static String DEFAULT_EMPTY_FLAG="empty";

    public final static String STRING_CSV_FILE_DIR="csv";

    public final static String STRING_PYTHON_FILE_DIR="py";
}
