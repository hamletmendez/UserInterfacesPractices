package com.example.hmendez.userinterfacespractices;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    public static class OpcionMenu {
        int id;
        String texto;

        public OpcionMenu(int id, String texto) {
            this.id = id;
            this.texto = texto;
        }
    }

    public static List<OpcionMenu> OPTIONS = new ArrayList<>();
    static {
        OPTIONS.add(new OpcionMenu(R.drawable.ic_account_circle,
                "PERFIL"));
        OPTIONS.add(new OpcionMenu(R.drawable.ic_school,
                "ACADEMIA"));
        OPTIONS.add(new OpcionMenu(R.drawable.ic_local_atm,
                "FINANZA"));
        OPTIONS.add(new OpcionMenu(R.drawable.ic_group_work,
                "COMUNIDAD"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_opciones);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager( new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(new OptionMenuAdaptet(this,OPTIONS));

    }

    private class OptionMenuAdaptet extends RecyclerView.Adapter<OptionMenuAdaptet.ViewHolder>{

        private List<OpcionMenu> options;
        Context context;

        public OptionMenuAdaptet(Context context, List<OpcionMenu> options){
            this.context = context;
            this.options = options;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_perfil,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            OpcionMenu opcion = options.get(position);
            holder.text.setText(opcion.texto);
            Glide.with(context).load(opcion.id).into(holder.image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String optionSelected="Nada";
                    switch (position){
                        case 0:
                            optionSelected = "Perfil";
                            break;
                        case 1:
                            optionSelected = "Academia";
                            break;
                        case 2:
                            optionSelected = "Finanzas";
                            break;
                        case 3:
                            optionSelected = "Comunidad";
                            break;
                    }
                    Snackbar.make(v,optionSelected,Snackbar.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return options.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            ImageView image;
            TextView text;
            private ViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.logo);
                text = itemView.findViewById(R.id.description);
            }
        }
    }
}
