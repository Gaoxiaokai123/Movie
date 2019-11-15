package com.bawei.cinemademo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.bean.HotMovieListBean;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/7 19:54:00
 * @Description:
 */
public class HotMovieListAdapter extends RecyclerView.Adapter<HotMovieListAdapter.myHolder> {
   List<HotMovieListBean> list = new ArrayList<>();

    public void addAll(List<HotMovieListBean> data){
        if (data!=null)
            list.addAll(data);
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_home_hotrecy, viewGroup, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, final int i) {
        myHolder.name.setText(list.get(i).name+"");
        myHolder.price.setText(list.get(i).score+"分");
        myHolder.img.setImageURI(list.get(i).imageUrl);
        myHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTopItemListener.onClick(list.get(i).movieId);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class myHolder extends RecyclerView.ViewHolder {
//
        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView price;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.frag_home_hot_img);
            name = itemView.findViewById(R.id.frag_home_hot_name);
            price = itemView.findViewById(R.id.frag_home_hot_price);
        }
    }

    //初始化接口
    public OnClickTopItemListener onClickTopItemListener;

    public void setOnClickTopItemListener(OnClickTopItemListener onClickTopItemListener) {
        this.onClickTopItemListener = onClickTopItemListener;
    }

    public interface OnClickTopItemListener{
        void onClick(int movieId);
    }
}
