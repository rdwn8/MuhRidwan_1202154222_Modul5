package praktikum.ridwan.android.muhridwan_1202154222_modul5;

/**
 * Created by Muh Ridwan on 25/03/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TambahData extends AppCompatActivity {
    //deklarasi variable yang akan digunakan
    EditText editText_ToDo, editText_Description, editText_Priority;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdodo);

        // mengisi variabel berdasarkan id dari edit text yang sudha dibuat
        editText_ToDo = (EditText) findViewById(R.id.todoname);
        editText_Description = (EditText) findViewById(R.id.description);
        editText_Priority = (EditText) findViewById(R.id.priority);
        database = new Database(this);
    }

    @Override
    public void onBackPressed() {
        //intent dari add to do menuju list to do
        Intent intent = new Intent(TambahData.this, DaftarData.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivitas setelah intent dijalankan
        this.finish();
    }

    // jika tombol tambah to do di klik
    public void addtodo(View view) {
        //jika data todoname, deskripsi dan prioritas telah diisi
        if (database.inputdata(new TambahDataSetterGetter(editText_ToDo.getText().toString(), editText_Description.getText().toString(), editText_Priority.getText().toString()))){
            //maka akan menampilkan toast bawha data berhasil di tambahkan ke dalam list
            Toast.makeText(this, "Data baru telah dimasukan", Toast.LENGTH_SHORT).show();
            // pindah ke daftar list utam
            startActivity(new Intent(TambahData.this, DaftarData.class));
            this.finish();
        }else {
            //apabila edit text kosong maka akan _muncul toast bahwa tidak bisa menambah ke dalam list
            Toast.makeText(this, "Data masih kosong, harap isi dulu", Toast.LENGTH_SHORT).show();
            //set semua edit text menjadi kosong
            editText_ToDo.setText(null);
            editText_Description.setText(null);
            editText_Priority.setText(null);
        }
    }
}