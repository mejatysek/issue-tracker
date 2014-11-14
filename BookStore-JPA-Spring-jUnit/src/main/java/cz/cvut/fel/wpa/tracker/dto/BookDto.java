/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.dto;

/**
 *
 * @author mickapa1
 */
public class BookDto extends AbstractDto{
    private String title;
    private Long owner;

    public BookDto() {
    }

    public BookDto(Long id, String title, Long owner) {
        this.id = id;
        this.title = title;
        this.owner = owner;
    }



    
    
    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
