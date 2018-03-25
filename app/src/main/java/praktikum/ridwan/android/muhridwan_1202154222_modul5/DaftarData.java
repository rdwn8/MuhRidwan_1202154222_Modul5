package praktikum.ridwan.android.muhridwan_1202154222_modul5;

/**
 * Created by Muh Ridwan on 25/03/2018.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class DaftarData extends AppCompatActivity {
    // membuat variabel yang dibutuhkan : database, recycler view, adapter dan ArrayList
    Database database;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<TambahDataSetterGetter> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_recycler_view);

        // membuat dan mengisi variabel dengan recyclerview yang sudah dibuat
        recyclerView = findViewById(R.id.recyclerview);
        // membuat variabel untuk diisi array list
        arrayList = new ArrayList<>();
        // membuat variabel database baru
        database = new Database(this);
        // menghubungkan antara database dengan array list
        database.readdata(arrayList);

        // menggunakana sharedPreferences untuk menyimpan perubahan
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int warna = sharedPreferences.getInt("Colourground", R.color.white);

        // membuat adapter yang berisi warna dalam bentuk arraylist
        adapter = new Adapter(this,arrayList, warna);
        // mengatur ukuran recycler view menjadi otomatis menyesuaikan dengan ukuran datanya
        recyclerView.setHasFixedSize(true);
        // menampilkan reyclcer view dengan LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // menghubungkan reyclcer view dengan adapter yang telah dubat
        recyclerView.setAdapter(adapter);

        // metode untuk menghapus data
        slideGone();
    }

    //membuat method untuk menghapus item pada to do list
    public void slideGone(){
        // touch helper pada recyclerview
        ItemTouchHelper.SimpleCallback SENTUHGESER = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                TambahDataSetterGetter posisi = adapter.getData(position);
                // jika swipe ke kiri :
                if(direction==ItemTouchHelper.LEFT){
                    // hapus data nya pada posisi
                    if(database.removedata(posisi.getTodo())){
                        // hapus data dari reyclcer view dan database
                        adapter.deleteData(position);
                        // snackbar pada bagian bawah layar untuk memberi tahu bawa data sudah di hapus
                        Snackbar.make(findViewById(R.id.coor), "Data terhapus", 1000).show();
                    }
                }
            }
        };
        // membuat variabel baru untuk diberikan pengenalan bahawa TOUCHHELPER akan dilkakukan ke arah kiri
        ItemTouchHelper touchHelper = new ItemTouchHelper(SENTUHGESER);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        // ambil id dari data yang terpilih
        int id = menuItem.getItemId();
        // jika item dalam action bar yaitu setting terpilih
        if (id==R.id.settings){
            // tombol setting di tap, maka akan pindah ke laman pengaturan
            Intent intent = new Intent(DaftarData.this, Setting.class);
            // inten yang sudah dibuat akan dimulai dengan method startActivity
            startActivity(intent);
            // activity akan ditutup setelah pindah ke laman pengaturan
            finish();
        }
        // mengembalikan nilai true
        return true;
    }

    // jika tombol add di tap
    public void tambahdata(View view) {
        // membuat intent untuk pindah dari sini ke laman tambah data
        Intent intent = new Intent(DaftarData.this, TambahData.class);
        startActivity(intent);
    }
}
