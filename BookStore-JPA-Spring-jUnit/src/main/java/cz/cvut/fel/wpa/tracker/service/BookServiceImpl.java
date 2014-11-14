///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package cz.cvut.fel.wpa.tracker.service;
//
//
//import cz.cvut.fel.wpa.tracker.bo.User;
//import cz.cvut.fel.wpa.tracker.dto.BookDto;
//import cz.cvut.fel.wpa.tracker.helper.HibernateTools;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author mickapa1
// */
//@Component
//public class BookServiceImpl extends AbstractDataAccessService implements BookService{
//
//    @Override
//    public List<BookDto> getAllBooks(){
//        List<Book> books = genericDao.getAll(Book.class);
//        List<BookDto> bookDtos = new ArrayList<BookDto>();
//
//        for(Book b : books){
//            bookDtos.add(new BookDto(b.getId(), b.getTitle(), b.getId()));
//        }
//        return bookDtos;
//    }
//    @Override
//    public List<BookDto> getUsersBooks(Long userId){
//        List<Book> books = genericDao.getByProperty("owner", genericDao.loadById(userId, User.class), Book.class);
//        List<BookDto> bookDtos = new ArrayList<BookDto>();
//
//        for(Book b : books){
//            bookDtos.add(new BookDto(b.getId(), b.getTitle(), HibernateTools.getIdentifier(b.getOwner())));
//        }
//        return bookDtos;
//    }
//
//    @Override
//    public Long addBook(String title, Long owner) {
//        Book newBook = new Book();
//        newBook.setTitle(title);
//        newBook.setOwner(genericDao.loadById(owner, User.class));
//
//        return genericDao.saveOrUpdate(newBook).getId();
//    }
//
//
//    @Override
//    public void deleteBook(Long bookId) {
//        genericDao.removeById(bookId, Book.class);
//    }
//}
