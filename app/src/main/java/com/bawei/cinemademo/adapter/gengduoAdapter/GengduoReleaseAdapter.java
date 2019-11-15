package com.bawei.cinemademo.adapter.gengduoAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.adapter.HotMovieListAdapter;
import com.bawei.cinemademo.bean.ReleaseMovieListBean;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/7 19:54:00
 * @Description:  正在上映
 */
public class GengduoReleaseAdapter extends RecyclerView.Adapter<GengduoReleaseAdapter.myHolder> {
   List<ReleaseMovieListBean> list = new ArrayList<>();

    public void addAll(List<ReleaseMovieListBean> data){
        if (data!=null)
            list.addAll(data);
    }

    public void clear(){
        list.clear();
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_gengduo_item, viewGroup, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, final int i) {
        myHolder.name.setText(list.get(i).name+"");
        myHolder.daoyan.setText("导演:"+list.get(i).director);
        myHolder.pingfen.setText(list.get(i).score+"分");
        myHolder.zhuyan.setText("主演:"+list.get(i).starring);
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

        private final SimpleDraweeView img;
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

    //初始化接口
    public HotMovieListAdapter.OnClickTopItemListener onClickTopItemListener;

    public void setOnClickTopItemListener(HotMovieListAdapter.OnClickTopItemListener onClickTopItemListener) {
        this.onClickTopItemListener = onClickTopItemListener;
    }

    public interface OnClickTopItemListener{
        void onClick(int movieId);
    }


}
