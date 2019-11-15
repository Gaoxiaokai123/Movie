package com.bawei.cinemademo.adapter.cinemaAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.bean.cinemaBean.NearbyCinemasBean;
import com.bawei.cinemademo.bean.cinemaBean.RecommendBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 19:48:25
 * @Description:
 */
public class NearbyCinemasAdapter extends RecyclerView.Adapter<NearbyCinemasAdapter.myHolder> {
    List<NearbyCinemasBean> list = new ArrayList<>();

    public void addAll(List<NearbyCinemasBean> data){
        if (data!=null)
            list.addAll(data);
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tab_nearbycinemas_item, viewGroup, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, int i) {
        myHolder.name.setText(list.get(i).name);
        myHolder.address.setText(list.get(i).address);
        myHolder.distance.setText(list.get(i).distance/1000+"km");
        myHolder.img.setImageURI(list.get(i).logo);

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class myHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView address;
        private final TextView distance;
        private final SimpleDraweeView img;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tab_nearbycinemas_item_img);
            name = itemView.findViewById(R.id.tab_nearbycinemas_item_name);
            address = itemView.findViewById(R.id.tab_nearbycinemas_item_address);
            distance = itemView.findViewById(R.id.tab_nearbycinemas_item_distance);
        }
    }
}
