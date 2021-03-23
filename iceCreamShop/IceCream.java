package practice.iceCreamShop;

public class IceCream {
    private String name;
    private float price;


    public static IceCream createIceCreamFlavour(String name, float price)
    {
        IceCream i = new IceCream();
        i.name = name;
        i.price = price;
        return i;
    }

    public String getName()
    {
        return this.name;
    }

    public float getPrice()
    {
        return this.price;
    }

    @Override
    public String toString()
    {
        return "The ice cream flavour " + this.getName() + " costs " + this.getPrice() + " Euros";
    }

    public void print()
    {
        System.out.println(toString());
    }
}
