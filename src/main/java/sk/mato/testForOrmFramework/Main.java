package sk.mato.testForOrmFramework;

import sk.mato.ormFramework.Exception.AnnotationNotFoundException;
import sk.mato.ormFramework.Exception.MissingColumnAnnotationException;
import sk.mato.ormFramework.dbManager.OrmManager;
import sk.mato.testForOrmFramework.entits.Film;
import sk.mato.testForOrmFramework.entits.Person;

public class Main {

    public static void main(String[] args) throws Exception {
        OrmManager manager = new OrmManager();
        Person person = manager.getEntityById(0L, Person.class);

        System.out.println(person.getId());
        System.out.println(person.getName());
        System.out.println(person.getSurname());
        System.out.println(person.getAge());
        System.out.println(person.getAdress());

        Film film = new Film();
        film.setNazov("Fireproof");
        film.setReziser("Alex Kendrick");
        film.setvHlavnejUlohe("Kirk Cameron");
        manager.insertEntity(film);

    }
}
