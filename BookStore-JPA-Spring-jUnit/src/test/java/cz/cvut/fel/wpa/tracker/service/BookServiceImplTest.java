///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package cz.cvut.fel.wpa.tracker.service;
//
//import cz.cvut.fel.wpa.tracker.dto.BookDto;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
///**
// *
// * @author mickapa1
// */
//public class BookServiceImplTest extends AbstractServiceTest {
//
//    @Autowired
//    private BookService bookService;
//    @Autowired
//    private UserService userService;
//
//    public BookServiceImplTest() {
//        super();
//    }
//
//    @Test
//    public void testAddAndRetrieveBook() {
//
//        Long userId = addUser();
//
//        String title = "Bob a Bobek, kralici z klobouku";
//
//        Long bookId = bookService.addBook(title, userId);
//        List<BookDto> books = bookService.getUsersBooks(userId);
//        assertEquals(1, books.size());
//
//        BookDto book = books.get(0);
//
//        assertEquals(title, book.getTitle());
//        assertEquals(userId, book.getOwner());
//        assertEquals(bookId, book.getId());
//    }
//
//    @Test
//    public void testAddAndRemoveBook() {
//        Long userId = addUser();
//
//        String title = "Bob a Bobek, kralici z klobouku";
//        Long bookId = bookService.addBook(title, userId);
//        assertEquals(1, bookService.getAllBooks().size());
//        bookService.deleteBook(bookId);
//        assertEquals(0, bookService.getAllBooks().size());
//    }
//
//    @Test
//    public void testBookDeletedWhenUserRemoved(){
//        Long userId = addUser();
//
//        String title = "Bob a Bobek, kralici z klobouku";
//        bookService.addBook(title, userId);
//        assertEquals(1, bookService.getAllBooks().size());
//
//        userService.deleteUser(userId);
//        assertEquals(0, bookService.getAllBooks().size());
//
//    }
//
//    private long addUser() {
//        String userName = "UserName" + System.currentTimeMillis();
//        String passwd = "passwd" + System.currentTimeMillis();
//        int age = 18;
//
//        return userService.addUser(userName, passwd, age);
//    }
//}
