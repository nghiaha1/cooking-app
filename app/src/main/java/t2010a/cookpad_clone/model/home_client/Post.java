package t2010a.cookpad_clone.model.home_client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import t2010a.cookpad_clone.model.user.User;

public class Post implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("ingredient")
    @Expose
    private List<PostGradient> gradients;
    @SerializedName("making")
    @Expose
    private List<PostStep> steps;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumbnails")
    @Expose
    private String thumbnails;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("likes")
    @Expose
    private int likes;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("eaterNumber")
    @Expose
    private int eaterNumber;
    @SerializedName("cookingTime")
    @Expose
    private int cookingTime;

    public Post() {
    }

    public Post(int id, User user, List<PostGradient> gradients, List<PostStep> steps, String name, String description, String thumbnails, String detail, int likes, int status, String origin, int eaterNumber, int cookingTime) {
        this.id = id;
        this.user = user;
        this.gradients = gradients;
        this.steps = steps;
        this.name = name;
        this.description = description;
        this.thumbnails = thumbnails;
        this.detail = detail;
        this.likes = likes;
        this.status = status;
        this.origin = origin;
        this.eaterNumber = eaterNumber;
        this.cookingTime = cookingTime;
    }

    public Post(String name, String thumbnails, String detail) {
        this.name = name;
        this.thumbnails = thumbnails;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PostGradient> getGradients() {
        return gradients;
    }

    public void setGradients(List<PostGradient> gradients) {
        this.gradients = gradients;
    }

    public List<PostStep> getSteps() {
        return steps;
    }

    public void setSteps(List<PostStep> steps) {
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getEaterNumber() {
        return eaterNumber;
    }

    public void setEaterNumber(int eaterNumber) {
        this.eaterNumber = eaterNumber;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", gradients=" + gradients +
                ", steps=" + steps +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnails='" + thumbnails + '\'' +
                ", detail='" + detail + '\'' +
                ", likes=" + likes +
                ", status=" + status +
                ", origin='" + origin + '\'' +
                ", eaterNumber=" + eaterNumber +
                ", cookingTime=" + cookingTime +
                '}';
    }
}
