package com.example.ss;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrakListAdapter extends RecyclerView.Adapter<TrakListAdapter.MemberViewHolder> {

    private List<TrackDataDetail> members;
    private Context context;
    String strUser = "";



    String name, driver, phone, sourcetime, desttime, sourceplace, destplace, dates;


    public TrakListAdapter(List<TrackDataDetail> members, Context context) {
        this.members = members;
        this.context = context;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        TextView editname, editblood, editphone, etgcell, etorgan, etheight, etweight, etlastdonate, etaddress;

        private Button calls, gmap;

        public MemberViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cv);
            editname = (TextView) itemView.findViewById(R.id.editname);
            editblood = (TextView) itemView.findViewById(R.id.editblood);
            editphone = (TextView) itemView.findViewById(R.id.editphone);
            etheight = (TextView) itemView.findViewById(R.id.etheight);
            etweight = (TextView) itemView.findViewById(R.id.etweight);
            etlastdonate = (TextView) itemView.findViewById(R.id.etlastdonate);
            etaddress = (TextView) itemView.findViewById(R.id.etaddress);
            calls = (Button) itemView.findViewById(R.id.call);
            gmap = (Button) itemView.findViewById(R.id.gmap);


        }
    }


    @Override
    public void onBindViewHolder(MemberViewHolder memberViewHolder, final int i) {

        memberViewHolder.editname.setText("Name : " + members.get(i).getName());
        memberViewHolder.editblood.setText("Blood : " + members.get(i).getBlood());
        memberViewHolder.editphone.setText("Phone : " + members.get(i).getPhone());

        memberViewHolder.etheight.setText("Height : " + members.get(i).getHeight());

        memberViewHolder.etweight.setText("Weight : " + members.get(i).getWeight());
        memberViewHolder.etlastdonate.setText("Last Donate : " + members.get(i).getLastdonate());

        memberViewHolder.etaddress.setText("Address : " + members.get(i).getAddress());


        final int pos = i;
        memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });

        memberViewHolder.gmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = members.get(i).getPhone().toString().trim();


                Intent intent=new Intent(context,DonarLocation.class);
                intent.putExtra("phone",phone);

                context.startActivity(intent);


            }
        });


        memberViewHolder.calls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = members.get(i).getPhone().toString().trim();

                String strphone = "tel:"+phone;

                Log.i("Phone : ",phone);

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    //Intent callIntent = new Intent(Intent.ACTION_CALL);
                  //  callIntent.setData(Uri.parse(strphone));

                  //  context.startActivity(callIntent);

                    return;
                }
                else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse(strphone));
                    context.startActivity(callIntent);

                }

            }
        });




    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_list_adapter, viewGroup, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}
