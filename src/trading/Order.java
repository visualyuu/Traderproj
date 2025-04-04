package trading;

import exceptions.*;
import exceptions.InvalidSideException;
import price.Price;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import regex.RegexStrings;

public class Order implements Tradable{

    private final String user;
    private final String product;
    private final Price price;
    private final BookSide side;
    private final int originalVolume;
    private int remainingVolume;
    private  int cancelledVolume;
    private int filledVolume;
    private final String id;

    public Order(String user, String product, Price price, int originalVolume, BookSide side)
            throws InvalidStringException, InvalidPriceException, InvalidSideException, InvalidNumberException {
        this.user = RegexStrings.userTest(user);
        this.product = RegexStrings.productTest(product);
        this.price = setPrice(price);
        this.side= setSide(side);
        this.originalVolume = setOriginalVolume(originalVolume);
        setRemainingVolume(originalVolume);
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
        return id;
    }

    @Override
    public int getRemainingVolume() {
        return remainingVolume;
    }



    @Override
    public int getCancelledVolume() {
        return cancelledVolume;
    }


    @Override
    public BookSide getSide() {
        return side;
    }

    @Override
    public TradableDTO makeTradableDTO() {
        return new TradableDTO(this);
    }

    @Override
    public Price getPrice() {
        return price;
    }

    @Override
    public int getFilledVolume() {
        return filledVolume;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getProduct() {
        return product;
    }

    @Override
    public int getOriginalVolume() {
        return originalVolume;
    }

    @Override
    public String toString() {
        //done
        return getUser() +" "+ getSide() + " order: " + getProduct() + " at " + getPrice() +", "+
                "Orig Vol: " + getOriginalVolume() +", Rem Vol: " + getRemainingVolume()+
                ", Fill Vol: " + getFilledVolume()+", CXL Vol: "+getCancelledVolume()+
                ", ID: "+ getID();
    }
}
