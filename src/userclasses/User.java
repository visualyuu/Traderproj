package userclasses;

import exceptions.InvalidStringException;
import regex.RegexStrings;
import trading.TradableDTO;
import java.util.HashMap;


 class User {
     //package visible
    private String userId;
    private HashMap<String, TradableDTO> tradables;
    //id, tradabledto

    public User(String userId) throws InvalidStringException {
        setUserId(userId);
        this.tradables = new HashMap<>();
    }

    private void setUserId(String userId) throws InvalidStringException {
        this.userId = RegexStrings.userTest(userId);
    }

    public void updateTradable (TradableDTO o){
        if (o == null) {
            return;
        }
        tradables.put(o.tradableId(), o);
    }

    @Override
    public String toString() {
        //fix this
        return "User{" +
                "userId='" + userId + '\'' +
                ", tradables=" + tradables +
                '}';
    }
}
