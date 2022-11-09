
package t2010a.cookpad_clone.model.client_model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeModel implements Serializable
{

    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    @SerializedName("totalPage")
    @Expose
    private Integer totalPage;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("content")
    @Expose
    private List<Content> content = null;
    private final static long serialVersionUID = 4455424455166526317L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HomeModel() {
    }

    /**
     * 
     * @param totalItems
     * @param totalPage
     * @param currentPage
     * @param content
     */
    public HomeModel(Integer totalItems, Integer totalPage, Integer currentPage, List<Content> content) {
        super();
        this.totalItems = totalItems;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.content = content;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HomeModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("totalItems");
        sb.append('=');
        sb.append(((this.totalItems == null)?"<null>":this.totalItems));
        sb.append(',');
        sb.append("totalPage");
        sb.append('=');
        sb.append(((this.totalPage == null)?"<null>":this.totalPage));
        sb.append(',');
        sb.append("currentPage");
        sb.append('=');
        sb.append(((this.currentPage == null)?"<null>":this.currentPage));
        sb.append(',');
        sb.append("content");
        sb.append('=');
        sb.append(((this.content == null)?"<null>":this.content));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
