
package t2010a.cookpad_clone.model.client_model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("ingredient")
    @Expose
    private List<Ingredient> ingredient = null;
    @SerializedName("making")
    @Expose
    private List<Making> making = null;
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
    private Integer likes;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("eater")
    @Expose
    private Integer eater;
    @SerializedName("cookingTime")
    @Expose
    private Integer cookingTime;
    @SerializedName("createdAt")
    @Expose
    private LocalDateTime createdAt;
    @SerializedName("updatedAt")
    @Expose
    private LocalDateTime updatedAt;
    private final static long serialVersionUID = -3569091240082294738L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Content() {
    }

    /**
     *
     * @param making
     * @param ingredient
     * @param origin
     * @param description
     * @param cookingTime
     * @param name
     * @param eater
     * @param id
     * @param detail
     * @param thumbnails
     * @param user
     * @param likes
     * @param status
     * @param createdAt
     * @param updatedAt
     */
    public Content(LocalDateTime createdAt, LocalDateTime updatedAt, Integer id, User user, List<Ingredient> ingredient, List<Making> making, String name, String description, String thumbnails, String detail, Integer likes, Integer status, String origin, Integer eater, Integer cookingTime) {
        super();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.user = user;
        this.ingredient = ingredient;
        this.making = making;
        this.name = name;
        this.description = description;
        this.thumbnails = thumbnails;
        this.detail = detail;
        this.likes = likes;
        this.status = status;
        this.origin = origin;
        this.eater = eater;
        this.cookingTime = cookingTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public List<Making> getMaking() {
        return making;
    }

    public void setMaking(List<Making> making) {
        this.making = making;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getEater() {
        return eater;
    }

    public void setEater(Integer eater) {
        this.eater = eater;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Content.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("user");
        sb.append('=');
        sb.append(((this.user == null)?"<null>":this.user));
        sb.append(',');
        sb.append("ingredient");
        sb.append('=');
        sb.append(((this.ingredient == null)?"<null>":this.ingredient));
        sb.append(',');
        sb.append("making");
        sb.append('=');
        sb.append(((this.making == null)?"<null>":this.making));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("thumbnails");
        sb.append('=');
        sb.append(((this.thumbnails == null)?"<null>":this.thumbnails));
        sb.append(',');
        sb.append("detail");
        sb.append('=');
        sb.append(((this.detail == null)?"<null>":this.detail));
        sb.append(',');
        sb.append("likes");
        sb.append('=');
        sb.append(((this.likes == null)?"<null>":this.likes));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("origin");
        sb.append('=');
        sb.append(((this.origin == null)?"<null>":this.origin));
        sb.append(',');
        sb.append("eater");
        sb.append('=');
        sb.append(((this.eater == null)?"<null>":this.eater));
        sb.append(',');
        sb.append("cookingTime");
        sb.append('=');
        sb.append(((this.cookingTime == null)?"<null>":this.cookingTime));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
