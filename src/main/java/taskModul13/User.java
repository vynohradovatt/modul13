package taskModul13;

public class User {

    private int id;
    private String name;
    private String username;
    private String email;
    private Adress adress;
    private Geo geo;
    private String phone;
    private String website;
    private Company company;


    public User( String name, String username, String email, Adress adress, Geo geo, String phone, String website, Company company) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.adress = new Adress("5 avenue", "566", "New York", "432424");
        this.geo = new Geo("-54.545", "87.495");
        this.phone = phone;
        this.website = website;
        this.company = new Company("test", "r=test", "test");

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", adress=" + adress +
                ", geo=" + geo +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}
