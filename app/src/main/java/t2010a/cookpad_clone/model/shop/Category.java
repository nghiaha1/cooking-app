package t2010a.cookpad_clone.model.shop;

public class Category {
    private long id;
    private String name;
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
/*
*   @RequestMapping(method = RequestMethod.GET, value = "userss")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAllUser());
    }
* */