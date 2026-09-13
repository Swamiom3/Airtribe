package DesignPattern.meditrack.entity;
import DesignPattern.meditrack.exception.InvalidDataException;
import DesignPattern.meditrack.util.Validator;

public abstract class Person {

    /*
     * Encapsulation:
     * All fields are private.
     */
    private final int id;

    private String name;
    private int age;
    private String phone;
    private String email;

    /*
     * Constructor
     *
     * protected because Person is an abstract
     * parent class and should not be directly created.
     */
    protected Person(
            int id,
            String name,
            int age,
            String phone,
            String email
    ) throws InvalidDataException {

//        Validator.validateId(id);
        Validator.validateName(name);
        Validator.validateAge(age);
        Validator.validatePhone(phone);
        Validator.validateEmail(email);

        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    // =========================
    // GETTERS
    // =========================

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // =========================
    // SETTERS
    // =========================

    public void setName(String name)
            throws InvalidDataException {

        Validator.validateName(name);

        this.name = name;
    }

    public void setAge(int age)
            throws InvalidDataException {

        Validator.validateAge(age);

        this.age = age;
    }

    public void setPhone(String phone)
            throws InvalidDataException {

        Validator.validatePhone(phone);

        this.phone = phone;
    }

    public void setEmail(String email)
            throws InvalidDataException {

        Validator.validateEmail(email);

        this.email = email;
    }

    public void displayBasicInfo() {

        System.out.println(
                "ID      : " + id
        );

        System.out.println(
                "Name    : " + name
        );

        System.out.println(
                "Age     : " + age
        );

        System.out.println(
                "Phone   : " + phone
        );

        System.out.println(
                "Email   : " + email
        );
    }

    public abstract void displayInfo();

    public abstract String getPersonType();

    @Override
    public String toString() {

        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Person)) {
            return false;
        }

        return this.id == ((Person) obj).getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
}
