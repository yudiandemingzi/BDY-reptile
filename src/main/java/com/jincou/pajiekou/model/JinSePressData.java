package com.jincou.pajiekou.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xub
 * @since 2018/6/29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JinSePressData {
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("thumbnail_pic")
    private String thumbnailPic;
    @JsonProperty("read_number")
    private String readNumber;

    @JsonProperty("topic_id")
    private Long topicId;

    @JsonProperty("published_at")
    private long publishedAt;

    @JsonProperty("topic_url")
    private String topicUrl;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getThumbnailPic() {
		return thumbnailPic;
	}

	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}

	public String getReadNumber() {
		return readNumber;
	}

	public void setReadNumber(String readNumber) {
		this.readNumber = readNumber;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public long getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(long publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getTopicUrl() {
		return topicUrl;
	}

	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}



}
