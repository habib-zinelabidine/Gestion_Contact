package com.example.gestioncontacts;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class Edition extends AppCompatActivity  {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Edition.this.setContentView(R.layout.activity_edition);

        lv = findViewById(R.id.lv_aff_edi);

        ContactAdapter ad=new ContactAdapter(Edition.this, Accueil.data);
        lv.setAdapter(ad);

        lv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.showContextMenu();
                return true;
            }
        });
        lv.setOnCreateContextMenuListener(this);

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.add(0,1,0,"Appeler");
        menu.add(0,2,0,"Composer");
        menu.add(0,3,0,"Supprimer");
        menu.add(0,4,0,"Quitter");

    }


    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1 :
                for (int i=0; i<Accueil.data.size();i++) {

                    if (ContextCompat.checkSelfPermission(Edition.this, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Edition.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    } else {
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Accueil.data.get(i).numero)));
                    }
                }
                break;
            case 2:
                for (int i=0; i<Accueil.data.size();i++) {
                    Uri dial = Uri.parse("tel:" + Accueil.data.get(i).numero);

                    Intent composer = new Intent(Intent.ACTION_DIAL, dial);
                    startActivity(composer);
                }
                break;
            case 3:
                for (int i=0; i<Accueil.data.size();i++) {
                    Accueil.data.remove(i);
                    lv.invalidateViews();


                }
                break;
            case 4:
                this.finish();
                break;

        }
        return super.onContextItemSelected(item);
    }

}