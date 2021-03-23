package practice.iceCreamShop;

public class Menu {
    protected IceCream[] flavours;

    public Menu()
    {
        this.flavours = new IceCream[0];
    }

    public void addFlavour(IceCream iceCream)
    {
        IceCream[] copy = new IceCream[flavours.length+1];

            for(int i = 0; i < flavours.length; i++)
            {
                copy[i] = flavours[i];
            }
        copy[copy.length-1] = iceCream;
        this.flavours = copy;
    }

    public boolean iceCreamAvailable(IceCream iceCream)
    {
        for(int i = 0; i < this.flavours.length; i++)
        {
            if(this.flavours[i].getName().equals(iceCream.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public void removeFlavour(IceCream iceCream)
    {
        if(this.iceCreamAvailable(iceCream))
        {
            IceCream[] copy = new IceCream[this.flavours.length-1];
            int copyIndex = 0;
            for(int i = 0; i < this.flavours.length; i++)
            {
                if(!this.flavours[i].getName().equals(iceCream.getName()))
                {
                        copy[copyIndex++] = this.flavours[i];
                }
            }
            this.flavours = copy;
        }
    }

    public IceCream getIceCreamAtIndex(int index)
    {
        if(index>=flavours.length || index < 0)
        {
            return null;
        }
        return flavours[index];
    }

    public void print()
    {
        System.out.println("=========== Menu ===========");
        for(int i = 0; i < this.flavours.length; i++)
        {
            System.out.printf("%-18s %.2f Euro %n", this.flavours[i].getName(), this.flavours[i].getPrice());
        }
        System.out.println();
    }
}
