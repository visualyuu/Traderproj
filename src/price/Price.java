package price;

import exceptions.InvalidPriceException;

import java.util.Objects;

public class Price implements Comparable<Price>{

    private final int cents;

    public Price(int cents){
        this.cents = cents;
    }

    private int getPrice(){return cents;}
    public boolean isNegative(){
        if(cents < 0){
            return true;
        }
        return false;
    }

    public Price add(Price p) throws InvalidPriceException{
        if (p ==  null){
            throw new InvalidPriceException("Price cannot be null");
        }
        return new Price(cents + p.getPrice());
    }

    public Price subtract(Price p) throws InvalidPriceException{
        if (p ==  null){
            throw new InvalidPriceException("Price cannot be null");
        }
        return new Price(cents - p.getPrice());
    }

    public Price multiply(int n){
        return new Price(cents * n);
    }

    public boolean greaterOrEqual(Price p) throws InvalidPriceException{
        if (p ==  null){
            throw new InvalidPriceException("Price cannot be null");
        }
        if (p.getPrice() <= cents){
            return true;
        }
        return false;
    }

    public boolean lessOrEqual(Price p) throws InvalidPriceException{
        if (p ==  null){
            throw new InvalidPriceException("Price cannot be null");
        }
        if (p.getPrice() >= cents){
            return true;
        }
        return false;
    }

    public boolean greaterThan(Price p) throws InvalidPriceException{
        if (p ==  null){
            throw new InvalidPriceException("Price cannot be null");
        }
        if (p.getPrice() < cents){
            return true;
        }
        return false;
    }

    public boolean lessThan(Price p) throws InvalidPriceException{
        if (p ==  null){
            throw new InvalidPriceException("Price cannot be null");
        }
        if (p.getPrice() > cents){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("$%,.2f",cents /100.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return cents == price.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    @Override
    public int compareTo(Price p){
        if (p == null){
            return getPrice();
        }
        return  getPrice()-p.getPrice();
    }
}
