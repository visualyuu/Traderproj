package trading;

import price.Price;

public class Order implements Tradable{

    private String user;
    private String product;
    private Price price;
    //private BookSide side;
    private int originalVolume;
    private int remainingVolume;
   private  int cancelledVolume = 0;
    private int filledVolume = 0;
    private String id;

    public Order(String user, String product, Price price, BookSide side,
                 int originalVolume, int remainingVolume, int cancelledVolume, int filledVolume, String id){
        this.user =  user;
        product = getProduct();
        this.price = price;


    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public int getRemainingVolume() {
        return 0;
    }

    @Override
    public void setCancelledVolume(int newvol) {

    }

    @Override
    public int getCancelledVolume() {
        return 0;
    }

    @Override
    public void setRemainingVolume(int newVol) {

    }

    @Override
    public TradableDTO makeTradableDTO() {
        return null;
    }

    @Override
    public Price getPrice() {
        return null;
    }

    @Override
    public void setFilledVolume(int newVol) {

    }

    @Override
    public int getFilledVolume() {
        return 0;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getProduct() {
        return null;
    }

    @Override
    public int getOriginalVolume() {
        return 0;
    }
}
