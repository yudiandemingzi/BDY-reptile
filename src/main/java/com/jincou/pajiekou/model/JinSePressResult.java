package com.jincou.pajiekou.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

/**
 * @author xub
 * @since 2018/6/29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JinSePressResult {

    private int news;
    private int count;
    @JsonProperty("top_id")
    private long topId;
    @JsonProperty("bottom_id")
    private long bottomId;
    private List<DayData> list;

    
    public int getNews() {
		return news;
	}


	public void setNews(int news) {
		this.news = news;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public long getTopId() {
		return topId;
	}


	public void setTopId(long topId) {
		this.topId = topId;
	}


	public long getBottomId() {
		return bottomId;
	}


	public void setBottomId(long bottomId) {
		this.bottomId = bottomId;
	}


	public List<DayData> getList() {
		return list;
	}


	public void setList(List<DayData> list) {
		this.list = list;
	}


	@Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DayData {

        private String title;
        private JinSePressData extra;
        @JsonProperty("topic_url")
        private String topicUrl;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public JinSePressData getExtra() {
			return extra;
		}
		public void setExtra(JinSePressData extra) {
			this.extra = extra;
		}
		public String getTopicUrl() {
			return topicUrl;
		}
		public void setTopicUrl(String topicUrl) {
			this.topicUrl = topicUrl;
		}

    }
}
