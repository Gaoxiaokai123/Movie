package com.bawei.cinemademo.adapter.cinemaAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.bean.ComingSoonMovieListBean;
import com.bawei.cinemademo.bean.cinemaBean.RecommendBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/8 19:10:24
 * @Description:  查询推荐影院信息
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.myHolder> {

    List<RecommendBean> list = new ArrayList<>();

    public void addAll(List<RecommendBean> data){
        if (data!=null)
            list.addAll(data);
    }


    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tab_recommend_item, viewGroup, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, int i) {
        myHolder.address.setText(list.get(i).name);
        myHolder.address.setText(list.get(i).address);

        myHolder.img.setImageURI(list.get(i).logo);
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class myHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView address;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tab_recommend_item_img);
            name = itemView.findViewById(R.id.tab_recommend_item_name);
            address = itemView.findViewById(R.id.tab_recommend_item_address);
        }
    }


}
