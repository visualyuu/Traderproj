package userclasses;

import exceptions.DataValidationException;
import exceptions.InvalidStringException;
import trading.TradableDTO;

import java.util.TreeMap;

public final class UserManager {
    //is a singleton and a facade
    private static  UserManager instance;
    private TreeMap<String,User> userList = new TreeMap<>();

    public static UserManager getInstance(){
        if (instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    public void init (String userIn[]) throws DataValidationException, InvalidStringException {
        if (userIn == null){
            throw new DataValidationException("User list cannot be null");
        }
        for (String user: userIn){
            if (user == null){
                throw new DataValidationException("No users can be null");
            }
            userList.put(user, new User(user));
        }
    }

    public void updateTradable(String userId, TradableDTO o) throws DataValidationException {
        if (userId == null){
            throw new DataValidationException("User list cannot be null");
        }
        if (o == null){
            throw new DataValidationException("TradableDto cannot be null");
        }
        if (!userList.containsKey(userId)){
            throw new DataValidationException("User not found");
        }
        updateTradable(userId,o);
    }

    @Override
    public String toString() {
        //update, loop through User.toString s and append to StringBuilder
        return "UserManager{" +
                "userList=" + userList +
                '}';
    }
}
