package trading;


import exceptions.*;
import userclasses.UserManager;

import java.util.Random;
import java.util.random.*;
import java.util.HashMap;

public final class ProductManager {
    //is a singleton
    private static ProductManager instance;
    public HashMap<String,ProductBook> pbList = new HashMap<>();

    public static ProductManager getInstance(){
        if (instance == null){
            instance = new ProductManager();
        }
        return instance;
    }

   public void addProduct(String symbol) throws InvalidSideException, DataValidationException {
        if (symbol == null){
            throw new DataValidationException("Symbol cannot be null");
        }
        try {
            pbList.put(symbol,new ProductBook(symbol));
            }catch(InvalidStringException e){
                throw new DataValidationException("Product name cannot contain special characters/lowercase letters/numbers/spaces");
       }
    }
    
    public ProductBook getProductBook(String symbol) throws DataValidationException {
        if (!pbList.containsKey(symbol) || symbol ==null){
            throw new DataValidationException("Symbol not a product book");
        }

        return pbList.get(symbol);
    }

    public String getRandomProduct() throws DataValidationException {
        if(pbList == null){
            throw new DataValidationException("No product books available");
        }
        String[] keys = (String[]) pbList.keySet().toArray();
        Random random = new Random();
        return keys[random.nextInt(keys.length)];
    }

    public TradableDTO addTradable(Tradable o) throws InvalidPriceException, InvalidTradableException, DataValidationException {
        if( o == null){
            throw new DataValidationException("Null tradable");
        }
        String symbol = o.getProduct();
        pbList.get(symbol).add(o);
        TradableDTO dto = o.makeTradableDTO();
        UserManager.getInstance().updateTradable(o.getID(), dto);
        return dto;
    }

    public TradableDTO[] addQuote(Quote q) throws DataValidationException, InvalidStringException, InvalidPriceException, InvalidTradableException {
        if( q == null){
            throw new DataValidationException("Null quote");
        }
        String symbol = q.getSymbol();
        ProductBook pb = pbList.get(symbol);
        pb.removeQuotesForUser(q.getUser());
        TradableDTO buyDto = addTradable(q.getQuoteSide(BookSide.BUY));
        TradableDTO sellDto = addTradable(q.getQuoteSide(BookSide.SELL));
        return new TradableDTO[] {buyDto,sellDto};
    }

    public TradableDTO cancel(TradableDTO o) throws InvalidStringException, DataValidationException {
        if( o == null){
            throw new DataValidationException("Null DTO");
        }
        ProductBook pb = pbList.get(o.product());
        TradableDTO endDto = pb.cancel(o.side(),o.tradableId());
        if (endDto == null){
            System.out.println("Tradable failed to cancel");
            return null;
        }
        return endDto;
    }

    public TradableDTO[] cancelQuote(String symbol, String user) throws InvalidStringException, DataValidationException {
        if( symbol == null || user == null){
            throw new DataValidationException("Null field(s)");
        }
        if(!pbList.containsKey(symbol)){
            throw new DataValidationException("Product with symbol does not exist");
        }
        ProductBook pb = pbList.get(symbol);
        return pb.removeQuotesForUser(user);

    }

    @Override
    public String toString() {
        //update
        return "ProductManager{" +
                "pbList=" + pbList +
                '}';
    }
}
