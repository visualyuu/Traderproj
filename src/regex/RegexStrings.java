package regex;

import exceptions.InvalidStringException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexStrings {

    static Matcher matcher;
    static Pattern pNotAllowed = Pattern.compile("[0-9!@#%^&*.()_{}\\[\\]|\\\\\\s+]");
    static Pattern pAllowed = Pattern.compile("[0-9!@#%^&*()_{}\\[\\]|\\\\\\s+]");


    public static Matcher getMatcher(String name, Boolean p) {
        if (p){
            return pAllowed.matcher(name);
        }else {return pNotAllowed.matcher(name);}
    }
    public static String userTest(String user) throws InvalidStringException{
        matcher = getMatcher(user,false);
        if (matcher.find()){
            throw new InvalidStringException("User cannot contain special characters/numbers/spaces");
        } else if (user.length() != 3) {
            throw new InvalidStringException("User length is less than or greater than three");
        }
        return user.toUpperCase();
    }
    public static String productTest(String product) throws InvalidStringException{
        matcher = getMatcher(product,true);
        if (matcher.find()){
            throw new InvalidStringException("Product name cannot contain special characters/lowercase letters/numbers/spaces");
        } else if (product.length() > 5) {
            throw new InvalidStringException("Product name length cannot be greater than five");
        }
        return product;
    }
}
