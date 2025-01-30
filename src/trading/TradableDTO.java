package trading;

import price.Price;

public record TradableDTO(String user, String product, Price price, BookSide side, int originalVolume, int remainingVolume, int cancelledVolume, int filledVolume, String id) {
    public TradableDTO(Tradable tradable) {
        this(tradable.getUser(), tradable.getProduct(), tradable.getPrice(),tradable.getSide(),tradable.getOriginalVolume(),tradable.getRemainingVolume(),tradable.getCancelledVolume(), tradable.getFilledVolume(), tradable.getID());
    }

    @Override
    public String toString() {
        return "TradableDTO{" +
                "user='" + user + '\'' +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", side=" + side +
                ", originalVolume=" + originalVolume +
                ", remainingVolume=" + remainingVolume +
                ", cancelledVolume=" + cancelledVolume +
                ", filledVolume=" + filledVolume +
                ", id='" + id + '\'' +
                '}';
    }
}
