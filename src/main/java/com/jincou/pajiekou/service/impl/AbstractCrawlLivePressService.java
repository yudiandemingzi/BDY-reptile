
package com.jincou.pajiekou.service.impl;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xub
 * @since 2018/6/26
 */
 public abstract class AbstractCrawlLivePressService {

    String url;

    public void doTask(String url) throws IOException {
        this.url = url;
        int pageNum = 1;

        while (true) {
            List<PageListPress> newsList = crawlPage(pageNum++);
            // 抓取不到新的内容本次抓取结束
            if (CollectionUtils.isEmpty(newsList)) {
                break;
            }
       

            for (int i = newsList.size() - 1; i >= 0; i--) {
                PageListPress pageListNews = newsList.get(i);
                System.out.println(pageListNews.toString());
          
            }

        }
    }
    protected abstract List<PageListPress> crawlPage(int pageNum) throws IOException;


@Data
@AllArgsConstructor
@NoArgsConstructor
    public static class PageListPress {

        //新闻详情页面url
         private String href;
       //新闻标题
        private String title;
        //新闻阅读数量
        private int readCounts;
        //新闻发布时间
        private Date createTime;
        //新闻摘要
        private String summary;
		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getReadCounts() {
			return readCounts;
		}
		public void setReadCounts(int readCounts) {
			this.readCounts = readCounts;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public PageListPress(String href, String title, int readCounts, Date createTime, String summary) {
			super();
			this.href = href;
			this.title = title;
			this.readCounts = readCounts;
			this.createTime = createTime;
			this.summary = summary;
		}
		public PageListPress() {
			super();
		}

     }
  }
