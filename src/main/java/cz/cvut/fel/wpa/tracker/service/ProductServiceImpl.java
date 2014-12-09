package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.Customer;
import cz.cvut.fel.wpa.tracker.bo.Issue;
import cz.cvut.fel.wpa.tracker.bo.Product;
import cz.cvut.fel.wpa.tracker.dto.ProductDto;
import cz.cvut.fel.wpa.tracker.helper.DtoTransformerHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 8.12.14
 */
public class ProductServiceImpl extends AbstractDataAccessService implements ProductService {
    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> list = new ArrayList<ProductDto>();
        List<Product> products = genericDao.getAll(Product.class);

        if(products != null) {
            for (Product product : products) {
                list.add(dtoFromBo(product));
            }
        }

        return list;
    }


    @Override
    public List<ProductDto> getCustomerProducts(Long customerId) {
        List<ProductDto> list = new ArrayList<ProductDto>();
        List<Product> relations = genericDao.getByProperty("customer", customerId, Product.class);
        if (relations != null) {
            for (Product u : relations) {
                list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product u = genericDao.getById(id, Product.class);
        if (u != null) {
            return dtoFromBo(u);
        }
        return new ProductDto();
    }

    @Override
    public Long editProduct(ProductDto productDto) {
        Product product = genericDao.getById(productDto.getId(), Product.class);
        
        product.setCustomer(genericDao.getById(productDto.getCustomer(), Customer.class));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setSla(productDto.getSla());
        product.setState(productDto.isState());

        List<Issue> issueList = new ArrayList<Issue>();
        if (productDto.getIssues() != null) {
            for (Long issue : productDto.getIssues()) {
                issueList.add(genericDao.getById(issue, Issue.class));
            }
        }
        product.setIssues(issueList);

        return genericDao.saveOrUpdate(product).getId();
    }

    @Override
    public Long addProduct(String name, String sla, float price, boolean state, Long customer) {
        return addProduct(name,sla,price,state,customer,null);
    }

    @Override
    public Long addProduct(String name, String sla, float price, boolean state, Long customer, List<Long> issues) {
        Product product = new Product();
        product.setCustomer(genericDao.getById(customer, Customer.class));
        product.setName(name);
        product.setPrice(price);
        product.setSla(sla);
        product.setState(state);

        List<Issue> list = new ArrayList<Issue>();
        if (issues != null) {
            for (Long issue : issues) {
                list.add(genericDao.getById(issue, Issue.class));
            }
        }
        product.setIssues(list);

        return genericDao.saveOrUpdate(product).getId();
    }

    @Override
    public void deactivateProduct(Long id) {
        ProductDto productDto = getProductById(id);
        productDto.setState(false);
        editProduct(productDto);
    }

    private ProductDto dtoFromBo(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getSla(), product.getPrice(), product.isState(), product.getCustomer().getId(), DtoTransformerHelper.getIdentifiers(product.getIssues()));
    }
}
