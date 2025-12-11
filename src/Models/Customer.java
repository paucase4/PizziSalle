package Models;

public class Customer {
    private String name;
    private String phone;
    private String address;
    private boolean first;
    private boolean adult;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean isFirst() { return first; }
    public void setFirst(boolean first) { this.first = first; }
}
