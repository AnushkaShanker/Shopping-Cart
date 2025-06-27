package entity;
 private int adminId;
    private String name;

    public Admin(int adminId, String name) {
        this.adminId = adminId;
        this.name = name;
    }

    public void manageProduct(Product product) {
        System.out.println("Managing product: " + product.getName());
    }
}
