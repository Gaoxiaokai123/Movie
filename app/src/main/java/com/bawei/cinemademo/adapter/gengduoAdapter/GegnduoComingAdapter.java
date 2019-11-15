package com.bawei.cinemademo.adapter.gengduoAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.bean.ComingSoonMovieListBean;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/7 19:54:00
 * @Description:  即将上映
 */
public class GegnduoComingAdapter extends RecyclerView.Adapter<GegnduoComingAdapter.myHolder> {
   List<ComingSoonMovieListBean> list = new ArrayList<>();

    public void addAll(List<ComingSoonMovieListBean> data){
        if (data!=null)
            list.addAll(data);
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_gengduo_item, viewGroup, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, int i) {
        Date date = new Date(list.get(i).releaseTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        myHolder.name.setText(list.get(i).name+"");
        myHolder.daoyan.setText(list.get(i).wantSeeNum+"想看");
        myHolder.zhuyan.setText(simpleDateFormat.format(date)+"上映");
        myHolder.pingfen.setText("剩余座位:"+list.get(i).whetherReserve);


        Glide.with(myHolder.itemView.getContext()).load(list.get(i).imageUrl)
                   .into(myHolder.img);
//        myHolder.img.setImageURI(list.get(i).imageUrl);
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class myHolder extends RecyclerView.ViewHolder {

//        private final SimpleDraweeView img;
        private final ImageView img;
        private final TextView name;
        private final TextView daoyan;
        private final TextView zhuyan;
        private final TextView pingfen;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.frag_gengduo_item_img);
            name = itemView.findViewById(R.id.frag_gengduo_item_name);
            daoyan = itemView.findViewById(R.id.frag_gengduo_item_daoyan);
            zhuyan = itemView.findViewById(R.id.frag_gengduo_item_zhuyan);
            pingfen = itemView.findViewById(R.id.frag_gengduo_item_pingfen);


        }
    }
}
