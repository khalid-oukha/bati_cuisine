package entities;

public class Client {
    private int id;
    private String name;
    private String address;
    private String phone;
    private boolean isProfessional = false;

    public Client(int id, String name, String address, String phone, boolean isProfessional) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isProfessional = isProfessional;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isProfessional() {
        return isProfessional;
    }
}
