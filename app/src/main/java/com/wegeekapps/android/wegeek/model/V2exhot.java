
package com.wegeekapps.android.wegeek.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class V2exhot {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("content_rendered")
    @Expose
    private String contentRendered;
    @SerializedName("replies")
    @Expose
    private Integer replies;
    @SerializedName("member")
    @Expose
    private Member member;
    @SerializedName("node")
    @Expose
    private Node node;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("last_modified")
    @Expose
    private Integer lastModified;
    @SerializedName("last_touched")
    @Expose
    private Integer lastTouched;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return The contentRendered
     */
    public String getContentRendered() {
        return contentRendered;
    }

    /**
     * @param contentRendered The content_rendered
     */
    public void setContentRendered(String contentRendered) {
        this.contentRendered = contentRendered;
    }

    /**
     * @return The replies
     */
    public Integer getReplies() {
        return replies;
    }

    /**
     * @param replies The replies
     */
    public void setReplies(Integer replies) {
        this.replies = replies;
    }

    /**
     * @return The member
     */
    public Member getMember() {
        return member;
    }

    /**
     * @param member The member
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * @return The node
     */
    public Node getNode() {
        return node;
    }

    /**
     * @param node The node
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * @return The created
     */
    public Integer getCreated() {
        return created;
    }

    /**
     * @param created The created
     */
    public void setCreated(Integer created) {
        this.created = created;
    }

    /**
     * @return The lastModified
     */
    public Integer getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified The last_modified
     */
    public void setLastModified(Integer lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return The lastTouched
     */
    public Integer getLastTouched() {
        return lastTouched;
    }

    /**
     * @param lastTouched The last_touched
     */
    public void setLastTouched(Integer lastTouched) {
        this.lastTouched = lastTouched;
    }

}
