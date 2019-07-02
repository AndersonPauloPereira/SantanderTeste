package pereira.anderson.testeandroidv2.controller.Validar;

import android.util.Log;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by Anderson on 14/06/2019.
 */

public class Monetary {
    public static String Saldo = null;
    public static String convertToBigDecimal(String value){
        BigDecimal parsed = null;
        try {

            if(!value.isEmpty()) {
                int Zero = Integer.valueOf(value.charAt(value.length() - 1) + "");
                if (Zero != 0) {

                    value = value + "0";
                }

                String cleanString = value.replaceAll("[R,$,.]", "");
                parsed = new BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(
                        new BigDecimal(100), BigDecimal.ROUND_FLOOR);

                BigDecimal valor = new BigDecimal(parsed + "");
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                Saldo = nf.format(valor);
            }
        
        
        
        } catch (Exception e) {
            Log.e("sua_tag", e.getMessage(), e);
        }
        return Saldo;
    }
}
