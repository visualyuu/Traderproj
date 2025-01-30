package trading;

import exceptions.InvalidSideException;
import price.Price;

import java.util.ArrayList;
import java.util.TreeMap;

public class ProductBookSide {
    private final BookSide side;
    private final TreeMap<Price, ArrayList<Tradable>> bookEntries;

    public ProductBookSide(BookSide side) throws InvalidSideException {
        this.side = setSide(side);
        this.bookEntries = new TreeMap<>();
    }

    private BookSide setSide(BookSide side) throws InvalidSideException{
        if(side == null){
            throw new InvalidSideException("Side cannot be null");
        }
        return side;
    }

    public TradableDTO add(Tradable o){
        Price price = o.getPrice();
        if(!bookEntries.containsKey(price)){
            bookEntries.put(price,new ArrayList<>());
            bookEntries.get(price).add(o);
        }else{
            bookEntries.get(price).add(o);
        }
        return new TradableDTO(o);
    }

}
