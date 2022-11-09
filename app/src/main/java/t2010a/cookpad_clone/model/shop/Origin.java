package t2010a.cookpad_clone.model.shop;

public class Origin {
    private long id;
    private String country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}
