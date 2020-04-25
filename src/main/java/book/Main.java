package book;

import book.model.Book;
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.PersistenceModule;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        BookDao bookDao = injector.getInstance(BookDao.class);
        for (int i = 0; i < 100; i++) {
            Book book = BookGenerator.generate();
            bookDao.persist(book);
        }
        bookDao.findAll()
                .stream()
                .forEach(System.out::println);

        String isbn="";
        Scanner in = new Scanner(System.in);



        while(true){

            System.out.println("Könyv keresése ISBN13 alapján: ");
            isbn=in.nextLine();
            if (isbn.equals("exit")) break;
            System.out.println(bookDao.findByIsbn13(isbn));

        }
    }

}
