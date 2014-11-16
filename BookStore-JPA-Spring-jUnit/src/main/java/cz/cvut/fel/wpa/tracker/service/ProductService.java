/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface ProductService {

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts();

    @Transactional(readOnly = true)
    public List<ProductDto> getCustomerProducts(Long customerId);

    public ProductDto getProductById(Long id);

    public Long editProduct(ProductDto product);

    public Long addProduct(String name, String sla, float price, boolean state, Long customer);

    public Long addProduct(String name, String sla, float price, boolean state, Long customer, List<Long> issues);

    public void deactivateProduct(Long id);
}
