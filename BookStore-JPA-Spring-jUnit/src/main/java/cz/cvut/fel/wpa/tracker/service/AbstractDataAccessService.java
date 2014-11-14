/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mickapa1
 */
public abstract class AbstractDataAccessService {
    @Autowired
    protected GenericDao genericDao;
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }    
}
