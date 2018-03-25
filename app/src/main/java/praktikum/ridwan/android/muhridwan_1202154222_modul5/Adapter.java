package praktikum.ridwan.android.muhridwan_1202154222_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muh Ridwan on 25/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    //deklarasi variable yang akan digunakan
    private Context context;
    private List<TambahDataSetterGetter> list;
    int warna;

    //konstruktor
    public Adapter(Context cntx, ArrayList<TambahDataSetterGetter> list, int color){
        this.context=cntx;
        this.list=list;
        this.warna=color;
    }

    // membuat holder dalam bentuk view
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru dengan template yang sudah dibuat pada file XML
        View view = LayoutInflater.from(context).inflate(R.layout.template_card_view, parent, false);
        holder holder = new holder(view);
        return holder;
    }

    //menyetting nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        TambahDataSetterGetter data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(this.warna));
    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mendapatkan list dari adapter
    public TambahDataSetterGetter getData(int position){
        return list.get(position);
    }

    //untuk menghapus list pada todolist
    public void deleteData(int a){
        //remove item yang terpilih
        list.remove(a);
        //memberi notifikasi item yang di remove
        notifyItemRemoved(a);
        notifyItemRangeChanged(a, list.size());
    }

    class holder extends RecyclerView.ViewHolder{
        //deklarasi variable yang akan digunakan
        public TextView ToDo, Description, Priority;
        public CardView cardView;
        public holder(View itemView){
            super(itemView);

            //mengakses id text view pada layout dan juga cardview
            ToDo = itemView.findViewById(R.id.namatodo);
            Description = itemView.findViewById(R.id.description);
            Priority = itemView.findViewById(R.id.prioritas);
            cardView = itemView.findViewById(R.id.cardlist);
        }
    }
}
