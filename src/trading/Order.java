package trading;

import exceptions.*;
import exceptions.InvalidSideException;
import price.Price;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order implements Tradable{

    private String user;
    private String product;
    private Price price;
    private BookSide side;
    private int originalVolume;
    private int remainingVolume = originalVolume;
    private  int cancelledVolume = 0;
    private int filledVolume = 0;
    private String id;

    public Order(String user, String product, Price price, BookSide side, int originalVolume,
                 int remainingVolume, int cancelledVolume, int filledVolume, String id)
            throws InvalidStringException, InvalidPriceException, InvalidSideException, InvalidNumberException {
        setUser(user);
        setProduct(product);
        setPrice(price);
        setSide(side);
        setOriginalVolume(originalVolume);
        setRemainingVolume(remainingVolume);
        setCancelledVolume(cancelledVolume);
        setFilledVolume(filledVolume);
        setId(id);
    }

    private void setUser(String user) throws InvalidStringException {
        Pattern pattern = Pattern.compile("[0-9!@#%^&*.()_{}\\[\\]|\\\\\\s+]");
        Matcher matcher = pattern.matcher(user);

        if (matcher.find()){
            throw new InvalidStringException("User cannot contain special characters/numbers/spaces");
        } else if (user.length() != 3) {
            throw new InvalidStringException("User length is less than or greater than three");
        }
        this.user = user.toUpperCase();
    }
    private void setProduct(String product) throws InvalidStringException {
        Pattern pattern = Pattern.compile("[0-9!@#%^&*()_{}\\[\\]|\\\\\\s+]");
        Matcher matcher = pattern.matcher(product);

        if (matcher.find()){
            throw new InvalidStringException("Product name cannot contain special characters/lowercase letters/numbers/spaces");
        } else if (product.length() > 5) {
            throw new InvalidStringException("Product name length cannot be greater than five");
        }
        this.product = product;
    }

    private void setPrice(Price price) throws InvalidPriceException{
        if (price == null){
            throw new InvalidPriceException("Price cannot be null");
        }
        this.price = price;
    }

    private void setSide(BookSide side) throws InvalidSideException {
        if (side == null){
            throw new InvalidSideException("Side cannot be null");
        }
        this.side = side;
    }

    private void setOriginalVolume(int originalVolume) throws InvalidNumberException {
        if (originalVolume < 0 || originalVolume > 10000){
            throw new InvalidNumberException("Original volume cannot be less than 0 or greater than 10,000");
        }
        this.originalVolume = originalVolume;
    }

    @Override
    public void setRemainingVolume(int newVol) {
        //should be private. figure it out.
        this.remainingVolume = newVol;
    }

    @Override
    public void setFilledVolume(int newVol) {
        //should be private. figure it out.
        this.filledVolume = newVol;
    }

    @Override
    public void setCancelledVolume(int newVol) {
        //should be private. figure it out.
        this.cancelledVolume = newVol;
    }

    private void setId(String id) {
        this.id = id;
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
        return TradableDTO;
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
        return "Order{" +
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
