package t2010a.cookpad_clone.model.home_client;

import java.util.List;

public class Section {
    private String title;
    private String note;
    private List<Post> postList;

    public Section(String title, String note, List<Post> postList) {
        this.title = title;
        this.note = note;
        this.postList = postList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
