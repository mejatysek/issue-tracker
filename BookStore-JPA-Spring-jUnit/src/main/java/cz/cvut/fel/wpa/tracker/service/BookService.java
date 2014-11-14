/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.BookDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mickapa1
 */
@Transactional
public interface BookService {
    /**
     * Get all books stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<BookDto> getAllBooks();
    /**
     * Get all books owned by the given user
     * @param userId identifier of the user
     * @return users books
     */
    @Transactional(readOnly=true)
    public List<BookDto> getUsersBooks(Long userId);
    /**
     * Add book to the system
     * @param title title of the book
     * @param owner id of the owner
     * @return identifier of the newly added book
     */
    public Long addBook(String title, Long owner);
    /**
     * Deletes book from the system
     * @param bookId idenfier of the book to be removed
     */
    public void deleteBook(Long bookId);
}
