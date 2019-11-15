package com.bawei.cinemademo.adapter.cinemaAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.bean.cinemaBean.CinemaByBean;
import com.bawei.cinemademo.bean.cinemaBean.RegionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 21:09:36
 * @Description:
 */
public class CinemaByAdapter extends RecyclerView.Adapter<CinemaByAdapter.myHolder> {
    List<CinemaByBean> list = new ArrayList<>();

    public void addAll(List<CinemaByBean> data){
        if (data!=null)
            list.addAll(data);
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tab_region_item, viewGroup, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, int i) {
        myHolder.text.setText(list.get(i).name);

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class myHolder extends RecyclerView.ViewHolder {

        private final TextView text;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tab_region_item_text);
        }
    }
}
