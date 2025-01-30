package trading;

import exceptions.InvalidSideException;
import exceptions.InvalidStringException;
import regex.RegexStrings;

public class ProductBook {
    private final String product;
    private final ProductBookSide buySide;
    private final ProductBookSide sellSide;

    public ProductBook(String product, ProductBookSide buySide, ProductBookSide sellSide) throws InvalidStringException, InvalidSideException {
        this.product = RegexStrings.productTest(product);
        this.buySide = new ProductBookSide(BookSide.BUY);
        this.sellSide = new ProductBookSide(BookSide.SELL);
    }
}
