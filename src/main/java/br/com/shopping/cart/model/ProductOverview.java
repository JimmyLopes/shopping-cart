package br.com.shopping.cart.model;


import java.math.BigDecimal;

public class ProductOverview {

    private Long id;
    private String name;
    private BigDecimal price;

    public ProductOverview(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ProductOverview setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductOverview setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductOverview setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
