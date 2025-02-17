package trading;


public final class ProductManager {
    //is a singleton

    private static ProductManager instance;

    public static ProductManager getInstance(){
        if (instance == null){
            instance = new ProductManager();
        }
        return instance;
    }
}
