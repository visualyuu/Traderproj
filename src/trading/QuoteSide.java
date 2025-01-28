package trading;

import price.Price;

public class QuoteSide implements Tradable{
    @Override
    public String getID() {
        return null;
    }

    @Override
    public int getRemainingVolume() {
        return 0;
    }

    @Override
    public void setCancelledVolume(int newVol) {

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
    public BookSide getSide() {
        return null;
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
