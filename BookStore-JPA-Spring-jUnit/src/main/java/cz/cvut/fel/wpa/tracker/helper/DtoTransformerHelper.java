/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.helper;

import cz.cvut.fel.wpa.tracker.bo.AbstractBusinessObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mickapa1
 */
public class DtoTransformerHelper {
    /**
     * Convert list of entities to list of identifiers
     * @param list list to be converted
     * @return list of identifiers, null - if list is null
     */
    public static List<Long> getIdentifiers(List<? extends AbstractBusinessObject> list) {
        if (list == null) {
            return null;
        }
        List<Long> ids = new ArrayList<Long>();

        for (AbstractBusinessObject abo : list) {
            ids.add(abo.getId());
        }
        return ids;
    }
}
