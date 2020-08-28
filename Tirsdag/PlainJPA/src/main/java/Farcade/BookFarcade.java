/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farcade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author matti
 */
public class BookFarcade {
    
    public static void main(String[] args) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
    BookFarcade facade = BookFarcade.getBookFacade(emf);
    Book b1 = facade.addBook("Author 1");
    Book b2 = facade.addBook("Author 2");
    //Find book by ID
    System.out.println("Book1: "+facade.findBook(b1.getId()).getAuthor());
    System.out.println("Book2: "+facade.findBook(b2.getId()).getAuthor());
    //Find all books
    System.out.println("Number of books: "+facade.getAllBooks().size());

    }
    
     private static EntityManagerFactory emf;
    private static BookFarcade instance;

    private BookFarcade() {}

    public static BookFarcade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFarcade();
        }
        return instance;
    }
    //adds a book
    public Book addBook(String author){
        Book book = new Book(author);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        }finally {
            em.close();
        }
    }
    //finds a book with id
    public Book findBook(long id){
         EntityManager em = emf.createEntityManager();
        try{
            Book book = em.find(Book.class,id);
            return book;
        }finally {
            em.close();
        }
    }
    //getsallbooks
    public List<Book> getAllBooks(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Book> query = 
                       em.createQuery("Select book from Book book",Book.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

}
