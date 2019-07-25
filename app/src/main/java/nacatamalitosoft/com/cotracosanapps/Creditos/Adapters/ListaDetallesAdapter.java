package nacatamalitosoft.com.cotracosanapps.Creditos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nacatamalitosoft.com.cotracosanapps.Modelos.Articulos;
import nacatamalitosoft.com.cotracosanapps.R;

public class ListaDetallesAdapter extends BaseAdapter {
    private final List<Articulos> data;
    private Context context;

    public ListaDetallesAdapter(Context context, List<Articulos> data){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Articulos getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.resultados_busqueda_item, parent);

        TextView txtDescripcion = view.findViewById(R.id.tvDescripcionArticulo);
        TextView txtCodigo = view.findViewById(R.id.tvCodigoArticulo);
        TextView txtPrecio = view.findViewById(R.id.tvPrecioArticulo);

        txtDescripcion.setText(data.get(i).getDescripcion());
        txtCodigo.setText("Sub total: C$ "+ data.get(i).getPrecio());
        txtPrecio.setText("");
        txtPrecio.setCompoundDrawablesRelativeWithIntrinsicBounds(0, android.R.drawable.ic_delete, 0,0);
        return view;
    }

    public void updateDataSet(List<Articulos> newDataset){
        this.data.clear();
        this.data.addAll(newDataset);
        this.notifyDataSetChanged();
    }
}