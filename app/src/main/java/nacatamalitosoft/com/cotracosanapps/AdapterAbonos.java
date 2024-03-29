package nacatamalitosoft.com.cotracosanapps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import nacatamalitosoft.com.cotracosanapps.Modelos.Abonos;

public class AdapterAbonos extends RecyclerView.Adapter<AdapterAbonos.ViewHolderAdapterAbonos>  {

    public AdapterAbonos(ArrayList<Abonos> listaAbonos, Context context) {
        this.listaAbonos = listaAbonos;
        this.context = context;
    }

    private ArrayList<Abonos> listaAbonos;
    private Context context;
    DecimalFormat formato = new DecimalFormat("#,###.##");

    @NonNull
    @Override
    public AdapterAbonos.ViewHolderAdapterAbonos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_abono, null, false);
        return new ViewHolderAdapterAbonos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAbonos.ViewHolderAdapterAbonos viewHolderAdapterAbonos, int i) {
        viewHolderAdapterAbonos.setData(listaAbonos.get(i));
    }

    @Override
    public int getItemCount() {
        return listaAbonos.size();
    }


    public class ViewHolderAdapterAbonos extends RecyclerView.ViewHolder {
        TextView codigoAbono, codigoCredito, fechaAbono, montoAbono, placa;


        public ViewHolderAdapterAbonos(@NonNull View itemView) {
            super(itemView);

            codigoAbono = (TextView)itemView.findViewById(R.id.CodigoAbono);
            codigoCredito = (TextView)itemView.findViewById(R.id.CodigoCredito);
            fechaAbono = (TextView)itemView.findViewById(R.id.FechaAbono);
            montoAbono = (TextView)itemView.findViewById(R.id.MontoAbono);
            placa = (TextView)itemView.findViewById(R.id.Placa);
        }

        public void setData(Abonos abonos)
        {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            codigoAbono.setText("Abono: " + abonos.getCodigoAbono());
            codigoCredito.setText("Credito: CRED-" +abonos.getCreditoId() );
            fechaAbono.setText("Fecha: " + format.format(abonos.getFechaDeAbono()));
            montoAbono.setText("Monto: " + formato.format(abonos.getMontoDeAbono()));
            placa.setText("Placa: " +abonos.getPlaca());
        }
    }
}
