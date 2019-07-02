package pereira.anderson.testeandroidv2.model;

/**
 * Created by Anderson on 16/06/2019.
 */

public class ModeloCurrency {
    String TitleCurremcy, DescCurrency, DateCurrency, ValueCurrency;

    public ModeloCurrency(String titleCurremcy, String descCurrency, String dateCurrency, String valueCurrency) {
        TitleCurremcy = titleCurremcy;
        DescCurrency = descCurrency;
        DateCurrency = dateCurrency;
        ValueCurrency = valueCurrency;
    }

    public String getTitleCurremcy() {
        return TitleCurremcy;
    }

    public void setTitleCurremcy(String titleCurremcy) {
        TitleCurremcy = titleCurremcy;
    }

    public String getDescCurrency() {
        return DescCurrency;
    }

    public void setDescCurrency(String descCurrency) {
        DescCurrency = descCurrency;
    }

    public String getDateCurrency() {
        return DateCurrency;
    }

    public void setDateCurrency(String dateCurrency) {
        DateCurrency = dateCurrency;
    }

    public String getValueCurrency() {
        return ValueCurrency;
    }

    public void setValueCurrency(String valueCurrency) {
        ValueCurrency = valueCurrency;
    }
}
