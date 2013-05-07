package basar.common;

public class PriceUtils {

    public static Long formatPriceToLong(String price) {
        String str = price.replace(',', '.');
        Double priceDoubleValue = Double.valueOf(str) * 100;
        return Math.round(priceDoubleValue);
    }
    
}
