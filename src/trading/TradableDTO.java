package trading;

import price.Price;

//JDK HAS TO BE 16+ FOR RECORDS TO WORK

public record TradableDTO(String user, String product, Price price,
                          BookSide side, int originalVolume, int remainingVolume,
                          int cancelledVolume, int filledVolume, String tradableId) {
    public TradableDTO(Tradable tradable) {
        this(tradable.getUser(), tradable.getProduct(), tradable.getPrice(),
                tradable.getSide(),tradable.getOriginalVolume(),tradable.getRemainingVolume(),
                tradable.getCancelledVolume(), tradable.getFilledVolume(), tradable.getID());
    }

    @Override
    public String toString() {
        return
                 "Product: " + product + ", Price: " + price +", "+
                        "OriginalVol: " + originalVolume +", RemainingVol: " + remainingVolume+
                        ", Cancelled Vol : "+cancelledVolume+ ", Filled Vol: " + filledVolume+
                         ", User: "+ user+ ", Side: "+ side+
                        ", ID: "+ tradableId;
    }
}
