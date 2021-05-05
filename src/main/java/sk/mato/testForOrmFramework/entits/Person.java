package sk.mato.testForOrmFramework.entits;


import sk.mato.ormFramework.anotacie.Column;
import sk.mato.ormFramework.anotacie.Id;
import sk.mato.ormFramework.anotacie.Table;

@Table("PERSON")
public class Person {

    @Id
    @Column("ID")
    private Long id;

    @Column("NAME")
    private String name;

    @Column("SURNAME")
    private String surname;

    @Column("AGE")
    private Integer age;

    @Column("ADDRESS")
    private String adress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
