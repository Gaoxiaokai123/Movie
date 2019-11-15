package com.bawei.cinemademo.adapter.cinemaAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.bean.cinemaBean.RecommendBean;
import com.bawei.cinemademo.bean.cinemaBean.RegionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 20:51:08
 * @Description:
 */
public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.myHolder> {
    List<RegionBean> list = new ArrayList<>();

    public void addAll(List<RegionBean> data){
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
        myHolder.text.setText(list.get(i).regionName);
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
