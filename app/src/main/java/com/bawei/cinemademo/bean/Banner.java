package com.bawei.cinemademo.bean;

import java.util.List;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/7 18:43:30
 * @Description:
 */
public class Banner {
        /**
         * imageUrl : http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1_h.jpg
         * jumpUrl : wd://movie?movieId=16
         * rank : 1
         */

        public String imageUrl;
        public String jumpUrl;
        public int rank;

    public Banner(String imageUrl, String jumpUrl, int rank) {
        this.imageUrl = imageUrl;
        this.jumpUrl = jumpUrl;
        this.rank = rank;
    }


}
