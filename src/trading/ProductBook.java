package trading;

import exceptions.*;
import price.Price;
import regex.RegexStrings;

import java.sql.Array;

public class ProductBook {
    private final String product;
    private final ProductBookSide buySide;
    private final ProductBookSide sellSide;

    public ProductBook(String product) throws InvalidStringException, InvalidSideException {
        this.product = setProduct(product);
        this.buySide = new ProductBookSide(BookSide.BUY);
        this.sellSide = new ProductBookSide(BookSide.SELL);
    }

    private String setProduct(String product) throws InvalidStringException {
        if (product.isEmpty()){
            throw new InvalidStringException("No empty products");
        }
        return RegexStrings.productTest(product);
    }


    public TradableDTO add(Tradable t) throws InvalidTradableException, InvalidPriceException {
        System.out.println("**ADD " + t +"\n");
        if(t== null){
            throw new InvalidTradableException("Cannot have a null tradable");
        }
        TradableDTO dto;
        if(t.getSide().equals(BookSide.SELL)) {
            dto = sellSide.add(t);
        }else{
            dto = buySide.add(t);
        }
        tryTrade();
        return dto;
    }

    public TradableDTO[] add(Quote qte) throws InvalidQuoteException, InvalidPriceException, InvalidStringException {
        if(qte ==  null){
            throw new InvalidQuoteException("Quote cannot be null");
        }
        removeQuotesForUser(qte.getUser());
        TradableDTO dtoBuy= buySide.add(qte.getQuoteSide(BookSide.BUY));
        TradableDTO dtoSell= sellSide.add(qte.getQuoteSide(BookSide.SELL));
        tryTrade();
        System.out.println("**ADD " +  qte.getQuoteSide(BookSide.BUY) +"\n");
        System.out.println("**ADD " +  qte.getQuoteSide(BookSide.SELL)+"\n");
        return new TradableDTO[]{dtoBuy,dtoSell};
    }

    public TradableDTO cancel(BookSide side, String orderId) throws InvalidStringException {
        if (orderId ==  null){
            throw new InvalidStringException("Order Id cannot be empty");
        }
        if(side ==  BookSide.BUY){
            return buySide.cancel(orderId);
        }else{
            return sellSide.cancel(orderId);
        }
    }

    public TradableDTO[] removeQuotesForUser(String userName) throws InvalidStringException {
        if (userName ==  null){
            throw new InvalidStringException("User name cannot be empty");
        }
        TradableDTO dtoBuy = buySide.removeQuotesForUser(userName);
        TradableDTO dtoSell = sellSide.removeQuotesForUser(userName);
        return new TradableDTO[]{dtoBuy,dtoSell};
    }

    public void tryTrade() throws InvalidPriceException {
        Price pBuy = buySide.topOfBookPrice();
        Price pSell = sellSide.topOfBookPrice();
        if(pSell == null || pBuy == null){
            return;
        }
        int topVolBuy =buySide.topOfBookVolume();
        int topVolSell = sellSide.topOfBookVolume();

        int totalToTrade = Math.max(topVolBuy, topVolSell);
        while (totalToTrade > 0){
            pSell = sellSide.topOfBookPrice();
            pBuy = buySide.topOfBookPrice();
            if(pSell == null || pBuy == null || pSell.greaterThan(pBuy)){
                return;
            }
            int buyVol = buySide.topOfBookVolume();
            int sellVol = sellSide.topOfBookVolume();
            int toTrade = Math.min(buyVol, sellVol);
            buySide.tradeOut(pBuy,toTrade);
            sellSide.tradeOut(pSell,toTrade);
            totalToTrade = totalToTrade-toTrade;

        }

    }

    public String getTopOfBookString(BookSide side) {
        if (side.equals(BookSide.BUY)) {
            if (buySide.topOfBookPrice() == null) {
                return "Top of BUY book: $0.00 x " + buySide.topOfBookVolume();
            }
            return "Top of BUY book: " + buySide.topOfBookPrice() + " x " + buySide.topOfBookVolume();
        } else {
            if (sellSide.topOfBookPrice() == null) {
                return "Top of SELL book: $0.00 x " + sellSide.topOfBookVolume();
            }
            return "Top of SELL book: " + sellSide.topOfBookPrice() + " x " + sellSide.topOfBookVolume();
        }
    }

        @Override
        public String toString () {
            return "Product:" + product + '\n' +
                    "Side: BUY" + "\n" + buySide.toString() + '\n' +
                    "Side: SELL " + "\n" +
                    sellSide.toString();
        }

}
