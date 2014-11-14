/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.bo;

import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "admin")
@Configurable(preConstruction=true)
public class Admin extends Salesman {


}
