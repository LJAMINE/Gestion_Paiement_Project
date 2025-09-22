package Model;

abstract public class Person {

    protected String name;
    protected String prénom;
    protected String email;
    protected String motDePasse;

    public Person(String name, String prénom, String email, String motDePasse) {
        this.name = name;
        this.prénom = prénom;
        this.email = email;
        this.motDePasse = motDePasse;
    }
}
