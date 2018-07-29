package com.jincou.pajiekou.until;

/**
 * 新闻资讯项目公共常量
 *
 * @author 徐波
 * @since 2018/7／16
 */
public class CoinPressConsts {

    // 30 分钟
    public static final long MINTUES30 = 30 * 60 * 1000;

    // 抓取最大时间区间
    public static final long MAX_CRAWLER_TIME = 30 * 7 * 24 * 60 * 60 * 1000;

    // ================================资讯常量=======================================

    // ==============金色财经==================
    /**
     * 金色财经新闻地址
     */
    public static final String CHAIN_FOR_LIVE_URL = "https://www.jinse.com/xinwen";

    /**
     * 金色财经新闻接口地址
     */
    public static final String CHAIN_FOR_LIVE_PRESS_DATA_URL_FORMAT = "https://api.jinse.com/v6/information/list?catelogue_key=news&limit=%d&information_id=%d&flag=down&version=9.9.9";

    // ==============链得得==================
    /**
     * 金色财经新闻地址
     */
    public static final String LDD_LIVE_URL = "http://www.chaindd.com";

    /**
     * 金色财经新闻接口地址
     */
    public static final String LDD_PRESS_DATA_URL_FORMAT = "https://api.jinse.com/v6/information/list?catelogue_key=news&limit=%d&information_id=%d&flag=down&version=9.9.9";


}
