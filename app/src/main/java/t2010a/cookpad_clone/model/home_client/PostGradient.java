package t2010a.cookpad_clone.model.home_client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostGradient implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("detail")
    @Expose
    private String detail;

    public PostGradient() {
    }

    public PostGradient(int id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "PostGradient{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                '}';
    }
}
