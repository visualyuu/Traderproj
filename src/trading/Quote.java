package trading;

import exceptions.InvalidNumberException;
import exceptions.InvalidPriceException;
import exceptions.InvalidSideException;
import exceptions.InvalidStringException;
import price.Price;
import regex.RegexStrings;

public class Quote {
    private final String user;
    private final String product;
    private final QuoteSide buySide;
    private final QuoteSide sellSide;

    public Quote(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume, String userName)
            throws InvalidStringException, InvalidPriceException, InvalidNumberException, InvalidSideException {
        this.user = RegexStrings.userTest(userName);
        this.product = RegexStrings.productTest(symbol);
        this.buySide = new QuoteSide(userName,symbol,buyPrice,buyVolume,BookSide.BUY);
        this.sellSide = new QuoteSide(userName,symbol,sellPrice,sellVolume,BookSide.SELL);
    }

    public QuoteSide getQuoteSide(BookSide in){
        if (in == BookSide.BUY){
            return buySide;
        }else{
            return sellSide;
        }
    }

    public String getUser() {
        return user;
    }

    public String getSymbol() {
        return product;
    }
}
