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
        this.product = RegexStrings.productTest(product);
        this.buySide = new ProductBookSide(BookSide.BUY);
        this.sellSide = new ProductBookSide(BookSide.SELL);
    }

    public TradableDTO add(Tradable t) throws InvalidTradableException, InvalidSideException, InvalidPriceException {
        System.out.println("**ADD" + t );
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

    public TradableDTO[] add(Quote qte) throws InvalidQuoteException,InvalidPriceException {
        if(qte ==  null){
            throw new InvalidQuoteException("Quote cannot be null");
        }
        removeQuotesForUser(qte.getUser());
        TradableDTO dtoBuy= buySide.add(qte.getQuoteSide(BookSide.BUY));
        TradableDTO dtoSell= sellSide.add(qte.getQuoteSide(BookSide.SELL));
        tryTrade();
        return new TradableDTO[]{dtoBuy,dtoSell};
    }

    public TradableDTO cancel(BookSide side, String orderId){
        if(side ==  BookSide.BUY){
            return buySide.cancel(orderId);
        }else{
            return sellSide.cancel(orderId);
        }
    }

    public TradableDTO[] removeQuotesForUser(String userName){
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
        int totalToTrade = Math.max(buySide.topOfBookVolume(), sellSide.topOfBookVolume());
        while (totalToTrade > 0){
            pSell = sellSide.topOfBookPrice();
            pBuy = buySide.topOfBookPrice();
            if(pSell == null || pBuy == null || pSell.greaterThan(pBuy)){
                return;
            }
            int buyVol = buySide.topOfBookVolume();
            int sellVol = sellSide.topOfBookVolume();
            int toTrade = Math.min(buyVol, sellVol);
            buySide.tradeOut(pBuy,buyVol);
            sellSide.tradeOut(pSell,sellVol);
            totalToTrade = totalToTrade-toTrade;

        }

    }

    public String getTopOfBookString(BookSide side){
        if (side.equals(BookSide.BUY)){
            return "Top of BUY book: $" + buySide.topOfBookPrice() + "x" + buySide.topOfBookVolume();
        }else{
            return "Top of BUY book: $" + sellSide.topOfBookPrice() + "x" + sellSide.topOfBookVolume();
        }

    }

    @Override
    public String toString() {
        return "ProductBook{" +
                "product='" + product + '\'' +
                ", buySide=" + buySide +
                ", sellSide=" + sellSide +
                '}';
    }
}
