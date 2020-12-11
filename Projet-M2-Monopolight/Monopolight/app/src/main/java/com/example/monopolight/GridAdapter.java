package com.example.monopolight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridAdapter  extends BaseAdapter {

    private List<Prize> prizeList;
    private LayoutInflater layoutInflater;
    private Context context;

    public GridAdapter(Context aContext,  List<Prize> prizeList) {
        this.context = aContext;
        this.prizeList = prizeList;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return prizeList.size();
    }

    @Override
    public Object getItem(int position) {
        return prizeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_gift_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.giftNameView = (TextView) convertView.findViewById(R.id.nameView);
            holder.giftNumberView = (TextView) convertView.findViewById(R.id.numberView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Prize prize = this.prizeList.get(position);
        holder.giftNameView.setText(prize.getReward());
        holder.giftNumberView.setText("" + prize.getNumber());

        int imageId = this.getIdImageByName(prize.getGiftImage());

        holder.imageView.setImageResource(imageId);

        return convertView;
    }

    // Récupère l'ID d'une image du dossier mipmap
    public int getIdImageByName(String resName)  {
        String pkgName = context.getPackageName();

        int idImage = context.getResources().getIdentifier(resName , "drawable", pkgName);
        return idImage;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView giftNameView;
        TextView giftNumberView;
    }

}
