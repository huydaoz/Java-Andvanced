package session9.kiemtra;

class DigitalProduct extends Product{
    private double size;

    public DigitalProduct(String id, String name, double price, double size){
        super(id, name, price);
        this.size = size;
    }
    public void setSize(double size){
        this.size = size;
    }
    public void displayInfo(){
        System.out.println("Digital Product | ID: " + id +
                " | Name: " + name +
                " | Price: " + price +
                " | Size: " + size + "MB");
    }
}
