package trading;

import price.Price;

public record TradableDTO(String user, String product, Price price,
                          BookSide side, int originalVolume, int remainingVolume,
                          int cancelledVolume, int filledVolume, String tradableId) {
    public TradableDTO(Tradable tradable) {
        this(tradable.getUser(), tradable.getProduct(), tradable.getPrice(),
                tradable.getSide(),tradable.getOriginalVolume(),tradable.getRemainingVolume(),
                tradable.getCancelledVolume(), tradable.getFilledVolume(), tradable.getID());
    }

}
