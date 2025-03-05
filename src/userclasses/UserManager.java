package userclasses;

import exceptions.DataValidationException;
import exceptions.InvalidStringException;
import trading.TradableDTO;

import java.util.Map;
import java.util.TreeMap;

public final class UserManager {
    //is a singleton and a facade
    private static  UserManager instance;
    public TreeMap<String,User> userList;


    private UserManager(){
        userList = new TreeMap<>();
    }

    public static UserManager getInstance(){
        if (instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    public void init (String[] userIn) throws DataValidationException, InvalidStringException {
        if (userIn == null){
            throw new DataValidationException("User list cannot be null");
        }
        for (String user: userIn){
            if (user == null){
                throw new DataValidationException("No users can be null");
            }else{
            userList.put(user, new User(user));
            }
        }
    }

    public void updateTradable(String userId, TradableDTO o) throws DataValidationException {
        if (userId == null){
            throw new DataValidationException("User list cannot be null");
        }
        if (o == null){
            throw new DataValidationException("TradableDto cannot be null");
        }
        if (!userList.containsKey(o.user())){
            throw new DataValidationException("User not found");
        }
        userList.get(o.user()).updateTradable(o);
    }

    public User getUser(String userId){
        //remember the exception if user does not exist
        return userList.get(userId);
    }

    @Override
    public String toString() {
        //update, loop through User.toString s and append to StringBuilder
        StringBuilder finalStr = new StringBuilder();
        for(Map.Entry<String, User> entry: userList.entrySet()){
            finalStr.append(entry.getValue().toString()).append("\n");
        }
        return finalStr.toString();
    }
}
