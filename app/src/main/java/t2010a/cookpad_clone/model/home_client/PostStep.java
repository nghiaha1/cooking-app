package t2010a.cookpad_clone.model.home_client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostStep {
    private int id;
    @SerializedName("name")
    @Expose
    private String detail;

    public PostStep() {
    }

    public PostStep(int id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "PostStep{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                '}';
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


}
