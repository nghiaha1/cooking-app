package t2010a.cookpad_clone.model.home_client;

import java.io.Serializable;

public class Post implements Serializable {
    private String title;
    private String thumbnail;
    private String detail;

    public Post() {
    }

    public Post(String title, String thumbnail, String detail) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
