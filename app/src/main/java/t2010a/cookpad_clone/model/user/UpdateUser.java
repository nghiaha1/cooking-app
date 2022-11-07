package t2010a.cookpad_clone.model.user;

public class UpdateUser {
    private Long id;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private String detail;

    public UpdateUser() {
    }

    public UpdateUser(Long id, String fullName, String phone, String address, String email, String detail) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
