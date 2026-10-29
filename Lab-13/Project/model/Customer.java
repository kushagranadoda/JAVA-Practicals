
package model;

public class Customer {

    private String name;
    private String mobile;
    private String email;
    private Address address;

    public Customer(String name, String mobile, String email,
                    Address address) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public static class Address {
        private String city;
        private String state;
        private String pincode;

        public Address(String city, String state, String pincode) {
            this.city = city;
            this.state = state;
            this.pincode = pincode;
        }

        @Override
        public String toString() {
            return city + ", " + state + " - " + pincode;
        }
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', mobile='" + mobile
                + "', email='" + email + "', address=" + address + "}";
    }
}
