package praktikum.ridwan.android.muhridwan_1202154222_modul5;

/**
 * Created by Muh Ridwan on 25/03/2018.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    TextView textView_color;
    int idwarna;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_menu_setting_warna);

        // MEMBUAT ALERT DIALOG
        alert = new AlertDialog.Builder(this);

        //menginisialisasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        sharedPreferences = sharedP.edit();
        idwarna = sharedP.getInt("Colourground", R.color.white);
        //mengakses text view pada layout
        textView_color = findViewById(R.id.hasilwarna);
        //set shape color dengan color id yang terpilih
        textView_color.setText(getShapeColor(idwarna));
    }

    @Override
    public void onBackPressed() {
        //intent baru dari pengaturan menuju list to do
        Intent intent = new Intent(Setting.this, DaftarData.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivity setelah intent di jalanlan
        finish();
    }

    //method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //mendapatkan string warna yang akan digunakan untuk mengubah shape color
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void pilihwarna(View view) {
        //set title dari alert dialog menjadi Shape Color
        alert.setTitle("Color Configuration");
        //membuat view baru
        View vieww = getLayoutInflater().inflate(R.layout.pilihan_warna, null);
        //menampilkan view yang telah dibuat
        alert.setView(vieww);

        //mengakses radio group pada layout
        final RadioGroup radG = vieww.findViewById(R.id.radiocolor);
        radG.check(getColorid(idwarna));

        //ketika menekan Ok pada alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        idwarna = R.color.red;
                        break;
                    case R.id.green:
                        idwarna = R.color.green;
                        break;
                    case R.id.blue:
                        idwarna = R.color.blue;
                        break;
                    case R.id.white:
                        idwarna = R.color.white;
                        break;
                }
                //set shape color menjadi color id yang dipilih
                textView_color.setText(getShapeColor(idwarna));
                //menaruh shared preference
                sharedPreferences.putInt("Colourground", idwarna);
                //commit shared preference
                sharedPreferences.commit();
            }
        });

        //ketika menekan Cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //membuat dan menampilkan alert dialog
        alert.create().show();
    }
}
