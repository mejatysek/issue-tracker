/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.helper;

import cz.cvut.fel.wpa.tracker.bo.AbstractBusinessObject;
import org.hibernate.proxy.HibernateProxy;


/**
 * Hibernate support tools
 * @author Pavel Micka
 */
public class HibernateTools {
    /**
     * Get id from the proxy of the object without hitting the database
     * @return identifikator objektu
     */
    public static Long getIdentifier(AbstractBusinessObject o){
        if(o == null) return null;
        Long id = null;
        if(o instanceof HibernateProxy){
            id = (Long)((HibernateProxy) o).getHibernateLazyInitializer().getIdentifier();
        }else{
            id = o.getId();
        }
        return id;
    }
}