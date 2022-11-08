package t2010a.cookpad_clone.model.home_client;

import java.io.Serializable;
import java.util.List;

public class HomeModel implements Serializable {
    private List<Post> section_1;
    private List<Post> section_2;
    private List<Post> section_3;
    private List<Post> section_4;

    public List<Post> getSection_1() {
        return section_1;
    }

    public void setSection_1(List<Post> section_1) {
        this.section_1 = section_1;
    }

    public List<Post> getSection_2() {
        return section_2;
    }

    public void setSection_2(List<Post> section_2) {
        this.section_2 = section_2;
    }

    public List<Post> getSection_3() {
        return section_3;
    }

    public void setSection_3(List<Post> section_3) {
        this.section_3 = section_3;
    }

    public List<Post> getSection_4() {
        return section_4;
    }

    public void setSection_4(List<Post> section_4) {
        this.section_4 = section_4;
    }
}
