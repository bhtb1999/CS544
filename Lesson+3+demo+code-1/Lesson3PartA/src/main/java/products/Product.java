package products;

public class Product {
    private int productNumber;
    private String name;
    private int price;
    private Supplier supplier;

    public Product(int productNumber, String name, int price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "productNumber=" + productNumber + ", name='" + name + '\'' + ", price=" + price + '}';
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        supplier.setProduct(this);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }
}
