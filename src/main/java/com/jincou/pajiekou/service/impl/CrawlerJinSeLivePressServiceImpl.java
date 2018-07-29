
package com.jincou.pajiekou.service.impl;

import com.jincou.pajiekou.model.JinSePressData;
import com.jincou.pajiekou.service.CrawlerJinSeLivePressService;
import com.jincou.pajiekou.service.LivePressService;
import com.jincou.pajiekou.until.CoinPressConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.jincou.pajiekou.model.JinSePressResult;

import com.jincou.pajiekou.until.JsonUtils;
import com.jincou.pajiekou.until.OkHttp;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * 抓取金色财经快讯
 * @author xub
 * @since 2018/6/29
 */
@Slf4j
@Service
public class CrawlerJinSeLivePressServiceImpl extends AbstractCrawlLivePressService implements
        CrawlerJinSeLivePressService {

    //这个参数代表每一次请求获得多少个数据
    private static final int PAGE_SIZE = 15;

    //这个是真正翻页参数，每一次找id比它小的15个数据（有写接口是通过page=1，2来进行翻页所以比较好理解一点，其实它们性质一样）
    private long bottomId;


    //这个这里没有用到，但是如果有数据层，就需要用到，这里我只是把它答应到控制台
    @Autowired
    private LivePressService livePressService;
    
    
    
    //定时任务运行这个方法，doTask没有被重写，所有运行父类的方法
    @Override
    public void start() {
        try {
            doTask(CoinPressConsts.CHAIN_FOR_LIVE_PRESS_DATA_URL_FORMAT);
        } catch (IOException e) {
          //  log.error("抓取金色财经新闻异常", e);
        }
    }


    @Override
    protected List<PageListPress> crawlPage(int pageNum) throws IOException {
        // 最多抓取100页，多抓取也没有特别大的意思。
        if (pageNum >= 100) {
            return Collections.emptyList();
        }
        // 格式化翻页参数
        String requestUrl = String.format(CoinPressConsts.CHAIN_FOR_LIVE_PRESS_DATA_URL_FORMAT, PAGE_SIZE, bottomId);
        Response response = OkHttp.singleton().newCall(
                new Request.Builder().url(requestUrl).addHeader("referer", CoinPressConsts.CHAIN_FOR_LIVE_URL).get().build())
                .execute();
        if (response.isRedirect()) {
            // 如果请求发生了跳转，说明请求不是原来的地址了，返回空数据。
            return Collections.emptyList();
        }

        //先获得json数据格式
        String responseText = response.body().string();

        //在通过工具类进行数据赋值
        JinSePressResult jinSepressResult = JsonUtils.objectFromJson(responseText, JinSePressResult.class);
        if (null == jinSepressResult) {
            // 反序列化失败
            System.out.println("抓取金色财经快讯列表反序列化异常");
            return Collections.emptyList();
        }
        // 取金色财经最小的记录id，来进行翻页
        bottomId = jinSepressResult.getBottomId();

        //这个是谷歌提供了guava包里的工具类，Lists这个集合工具，对list集合操作做了些优化提升。
        List<PageListPress> pageListPresss = Lists.newArrayListWithExpectedSize(PAGE_SIZE);

        for (JinSePressResult.DayData dayData : jinSepressResult.getList()) {
            JinSePressData data = dayData.getExtra();
            //新闻发布时间（时间戳格式）这里可以来判断只爬多久时间以内的新闻
            long   createTime = data.getPublishedAt() * 1000;
            Long timemill=System.currentTimeMillis();
//           if (System.currentTimeMillis() - createTime > CoinPressConsts.MAX_CRAWLER_TIME) {
//               // 快讯过老了，放弃
//               continue;
//           }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sd = sdf.format(new Date(createTime));   // 时间戳转换成时间
            Date newsCreateTime=new Date();
            try {
                //获得新闻发布时间
                newsCreateTime = sdf.parse(sd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //具体文章页面路径（这里可以通过这个路径+jsoup就可以爬新闻正文所有信息了）
            String href = data.getTopicUrl();
            //新闻摘要
            String summary = data.getSummary();
            //新闻阅读数量
            String pressreadcount = data.getReadNumber();
            //新闻标题
            String title = dayData.getTitle();

                pageListPresss.add(new PageListPress(href,title, Integer.parseInt(pressreadcount),
                        newsCreateTime ,  summary));

        }
        return pageListPresss;

    }

}
