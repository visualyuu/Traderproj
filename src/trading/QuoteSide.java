package trading;

import exceptions.InvalidNumberException;
import exceptions.InvalidPriceException;
import exceptions.InvalidSideException;
import exceptions.InvalidStringException;
import price.Price;
import regex.RegexStrings;

public class QuoteSide implements Tradable{
    private final String user;
    private final String product;
    private final Price price;
    private final BookSide side;
    private int originalVolume;
    private int remainingVolume = originalVolume;
    private  int cancelledVolume = 0;
    private int filledVolume = 0;
    private final String id;

    public QuoteSide(String user, String product, Price price, int originalVolume,BookSide side)
            throws InvalidStringException, InvalidPriceException, InvalidSideException, InvalidNumberException {
        this.user = RegexStrings.userTest(user);
        this.product = RegexStrings.productTest(product);
        this.price = setPrice(price);
        this.side = setSide(side);
        this.originalVolume = setOriginalVolume(originalVolume);
        setRemainingVolume(remainingVolume);
        setCancelledVolume(cancelledVolume);
        setFilledVolume(filledVolume);
        id  = user+product+price+System.nanoTime();
    }

    private Price setPrice(Price price) throws InvalidPriceException{
        if (price == null){
            throw new InvalidPriceException("Price cannot be null");
        }
        return price;
    }

    private BookSide setSide(BookSide side) throws InvalidSideException {
        if (side == null){
            throw new InvalidSideException("Side cannot be null");
        }
        return side;
    }

    private int setOriginalVolume(int originalVolume) throws InvalidNumberException {
        if (originalVolume < 0 || originalVolume > 10000){
            throw new InvalidNumberException("Original volume cannot be less than 0 or greater than 10,000");
        }
        return originalVolume;
    }

    @Override
    public void setRemainingVolume(int newVol) {
        this.remainingVolume = newVol;
    }

    @Override
    public void setFilledVolume(int newVol) {
        this.filledVolume = newVol;
    }

    @Override
    public void setCancelledVolume(int newVol) {
        this.cancelledVolume = newVol;
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
    public int getCancelledVolume() {
        return 0;
    }

    @Override
    public TradableDTO makeTradableDTO() {
        return new TradableDTO(this);
    }

    @Override
    public Price getPrice() {
        return null;
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

    @Override
    public String toString() {
        //change
        return "QuoteSide{" +
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
