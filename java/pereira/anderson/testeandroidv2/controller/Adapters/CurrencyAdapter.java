package pereira.anderson.testeandroidv2.controller.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pereira.anderson.testeandroidv2.R;
import pereira.anderson.testeandroidv2.model.ModeloCurrency;

/**
 * Created by Anderson on 16/06/2019.
 */

public class CurrencyAdapter extends ArrayAdapter<ModeloCurrency> {

    public CurrencyAdapter(Context context, ArrayList<ModeloCurrency> Dados) {
        super(context, 0, Dados);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final ModeloCurrency dados = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_currency, parent, false);
        }

        TextView textViewTitle = (TextView)  convertView.findViewById(R.id.textViewTitle);
        TextView textViewData = (TextView)  convertView.findViewById(R.id.textViewData);
        TextView textViewValue = (TextView)  convertView.findViewById(R.id.textViewValue);
        TextView textViewDesc = (TextView)  convertView.findViewById(R.id.textViewDesc);

        textViewData.setText(dados.getDateCurrency().toString());
        textViewDesc.setText(dados.getDescCurrency().toString());
        textViewTitle.setText(dados.getTitleCurremcy().toString());
        textViewValue.setText(dados.getValueCurrency().toString());


        return convertView;
    }
}
