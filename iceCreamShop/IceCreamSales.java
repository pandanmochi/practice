package practice.iceCreamShop;

public class IceCreamSales {
    private IceCream iceCream;
    private int quantitySold;

    public IceCreamSales(IceCream iceCream)
    {
        this.iceCream = iceCream;
        quantitySold = 0;
    }

    public void sell()
    {
        this.quantitySold++;
    }

    public IceCream getIceCream()
    {
        return this.iceCream;
    }

    public int getQuantitySold()
    {
        return this.quantitySold;
    }

    public double sales()
    {
        return getQuantitySold() * iceCream.getPrice();
    }

    @Override
    public String toString()
    {
        return "The ice cream flavour " + getIceCream().getName()
                + " was sold " + getQuantitySold() + " times at " + getIceCream().getPrice() + " Euros";
    }

    public void print()
    {
        System.out.println(toString());
    }
}
