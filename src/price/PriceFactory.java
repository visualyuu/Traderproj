package price;

import exceptions.InvalidPriceException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PriceFactory {
    //edited for flyweight implementation 2/17
    private static HashMap<Integer, Price> prices = new HashMap<>();
    public static Price makePrice (int value){
        if(!prices.containsKey(value)){
            prices.put(value, new Price(value));;
        }
        return prices.get(value);
    }

    public static Price makePrice(String stringValueIn) throws InvalidPriceException {
        if (stringValueIn.isEmpty()){
            throw new InvalidPriceException("Field cannot be empty");
        }

        Pattern pattern = Pattern.compile("[A-Za-z!@#%^&*()_{}\\[\\]|\\\\]");
        Matcher matcher = pattern.matcher(stringValueIn);
        if (matcher.find()){
            throw new InvalidPriceException("No letters allowed or symbols that are not $-,.");
        }

        char[] parray = stringValueIn.toCharArray();
        int dollarcount = 0;
        int pcount = 0;
        int afterp = 0;
        int neg = 0;

        for (int i = 0; i <= parray.length-1; i++){

            if (parray[i] == '-'){
                neg++;
                if (dollarcount == 1 && i != 1|| i != 0 && dollarcount == 0||neg > 1){
                    throw new InvalidPriceException("Misplaced '-' sign(s)");
                }
            }
            if (parray[i] == '$'){
                dollarcount++;
                if (i != 0 || dollarcount > 1){
                    throw new InvalidPriceException("'$' sign(s) misplaced");
                }
            }
            if(pcount == 1) {
                afterp++;
            }

            if (parray[i] == '.'){
                pcount++;
            }

        }

        if(pcount == 1 && afterp  == 1  ||pcount == 1 && afterp > 2 || pcount > 1){
            throw new InvalidPriceException("Improper cents formatting " + stringValueIn);
        }

        if (!stringValueIn.contains(".")|| pcount == 1 && afterp == 0){
            stringValueIn = stringValueIn+"00";
        }

        String result = stringValueIn.replaceAll("[$.,]", "");

        return makePrice(Integer.parseInt(result));
        //make int from str, pass into fweight check
    }
}
