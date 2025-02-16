package trading;

import exceptions.InvalidPriceException;
import exceptions.InvalidSideException;
import price.Price;

import java.util.ArrayList;
import java.util.*;

public class ProductBookSide {
    private final BookSide side;
    private final TreeMap<Price, ArrayList<Tradable>> bookEntries;

    public ProductBookSide(BookSide side) throws InvalidSideException {
        this.side = setSide(side);
        if (side == BookSide.BUY) {
            this.bookEntries = new TreeMap<>(Collections.reverseOrder());
        }else {
            this.bookEntries =new TreeMap<>();
        }
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
        }
        bookEntries.get(price).add(o);
        return new TradableDTO(o);
    }

    public  TradableDTO cancel(String tradableId){
        Set<Price> keySet = bookEntries.keySet();
        for(Price k : keySet){
            ArrayList<Tradable> list = bookEntries.get(k);
            for (Tradable t : list){
                if(t.getID().equals(tradableId)){
                    System.out.println("**CANCEL "+t+"\n");
                    list.remove(t);
                    t.setCancelledVolume(t.getCancelledVolume() + t.getRemainingVolume());
                    t.setRemainingVolume(0);
                    if(list.isEmpty()){
                        bookEntries.remove(k,list);;
                    }
                    return new TradableDTO(t);
                }
            }

        }
        return null;
    }

    public TradableDTO removeQuotesForUser(String userName){
        Set<Price> keySet = bookEntries.keySet();
        for(Price k : keySet) {
            ArrayList<Tradable> qlist = bookEntries.get(k);
            for (Tradable t : qlist) {
                if (t.getUser().equals(userName)) {
                    TradableDTO cDTO = cancel(t.getID());
                    if(qlist.isEmpty()){
                        bookEntries.remove(k,qlist);
                    }
                    return cDTO;
                }
            }

        }
        return null;
    }

    public Price topOfBookPrice(){
        if (bookEntries.isEmpty()){
            return null;
        }
        return bookEntries.firstKey();
    }

    public int topOfBookVolume(){
        if (bookEntries.isEmpty()){
            return 0;
        }
        int sum = 0;
        ArrayList<Tradable> qlist = bookEntries.get(topOfBookPrice());
        for(Tradable t: qlist){
            sum = sum + t.getRemainingVolume();
        }
        return sum;

    }
    public void tradeOut(Price price, int vol) throws InvalidPriceException {
        Price top = topOfBookPrice();
        if (top == null){
            return;
        } else if (top.greaterThan(price)) {
            return;
        }

        ArrayList<Tradable> list = bookEntries.get(top);
        int sum = topOfBookVolume();

        if (vol >= sum){
            for(Tradable t : list){
                int rv = t.getRemainingVolume();
                t.setFilledVolume(t.getOriginalVolume());
                t.setRemainingVolume(0);
                System.out.println("   FULL FILL: ("+ side + " " +t.getFilledVolume()+") "+ t);
            }
            bookEntries.remove(top,list);
        }else {
            int remainder = vol;
            for (Tradable t : list) {
                double ratio = (double) t.getRemainingVolume() / sum;
                int toTrade = (int) Math.ceil(vol * ratio);
                toTrade = Math.min(toTrade, remainder);
                t.setFilledVolume(t.getFilledVolume() + toTrade);
                t.setRemainingVolume(t.getRemainingVolume() - toTrade);
                System.out.println("   PARTIAL FILL: ("+ side + " " +t.getFilledVolume()+") "+ t);
                remainder = remainder - toTrade;
            }
        }
    }

    @Override
    public String toString() {
        if (this.bookEntries.isEmpty()){
            return "   <Empty>";
        }

        StringBuilder finalStr = new StringBuilder();
        for (Map.Entry<Price, ArrayList<Tradable>> entry : bookEntries.entrySet()) {
            finalStr.append("   ").append(entry.getKey()).append(":\n");
            for (Tradable item : entry.getValue()) {
                finalStr.append("        ").append(item).append("\n");
            }
        }
        return finalStr.toString();
    }
}
