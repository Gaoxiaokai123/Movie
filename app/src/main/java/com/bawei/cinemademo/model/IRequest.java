package com.bawei.cinemademo.model;


import com.bawei.cinemademo.bean.Banner;
import com.bawei.cinemademo.bean.CinemaName;
import com.bawei.cinemademo.bean.Cinemas;
import com.bawei.cinemademo.bean.ComingSoonMovieListBean;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.Detail;
import com.bawei.cinemademo.bean.HotMovie;
import com.bawei.cinemademo.bean.HotMovieListBean;
import com.bawei.cinemademo.bean.LoginBean;
import com.bawei.cinemademo.bean.MBanner;
import com.bawei.cinemademo.bean.ReleaseMovieListBean;
import com.bawei.cinemademo.bean.Soon;
import com.bawei.cinemademo.bean.cinemaBean.NearbyCinemasBean;
import com.bawei.cinemademo.bean.cinemaBean.RecommendBean;
import com.bawei.cinemademo.bean.cinemaBean.RegionBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @describe(描述)：IRequest
 * @data（日期）: 2019/11/5
 * @time（时间）: 17:06
 * @author（作者）: 盖磊
 **/
public interface IRequest {
    //版本更新
//    http://172.17.8.100/movieApi/tool/v1/findNewVersion
    @GET("tool/v1/findNewVersion")
    Observable<Data>findNewVersion(@Header("userId") int userId, @Header("sessionId") String sessionId, @Header("ak") String ak);

//    上传用户头像
//    http://172.17.8.100/movieApi/user/v1/verify/uploadHeadPic
    @POST("user/v1/verify/uploadHeadPic")
    Observable<Data<Data>>uploadHeadPic(@Header("userId")String userId,@Header("sessionId")String sessionId,@Part MultipartBody.Part part);


    //注册
//    http://172.17.8.100/movieApi/user/v2/register
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<Data<LoginBean>> register(@Field("nickName")String nickName,@Field("pwd")String pwd,@Field("email")String email,@Field("code")String code);

    //邮箱验证
//    http://172.17.8.100/movieApi/user/v2/sendOutEmailCode
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<Data>sendOutEmailCode(@Field("email")String email);

    //登录
    //    http://172.17.8.100/movieApi/user/v2/login
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<Data<LoginBean>> login(@Field("email")String email,@Field("pwd")String pwd);

    /*
        影片接口
     */

    //Banner展示
//    http://172.17.8.100/movieApi/tool/v2/banner
    @GET("tool/v2/banner")
    Observable<Data<List<Banner>>> banner();


    //正在上映(热映)
//    http://172.17.8.100/movieApi/movie/v2/findReleaseMovieList
    @GET("movie/v2/findReleaseMovieList")
    Observable<Data<List<ReleaseMovieListBean>>> findReleaseMovieList(@Query("page") int page, @Query("count") int count);


    //即将上映
//    http://172.17.8.100/movieApi/movie/v2/findComingSoonMovieList
    @GET("movie/v2/findComingSoonMovieList")
    Observable<Data<List<ComingSoonMovieListBean>>> findComingSoonMovieList(@Query("page") int page, @Query("count") int count);


    //查询热门电影列表
    //http://172.17.8.100/movieApi/movie/v2/findHotMovieList
    @GET("movie/v2/findHotMovieList")
    Observable<Data<List<HotMovieListBean>>> findHotMovieList(@Query("page") int page, @Query("count") int count);

//   模糊查询  根据关键字查询电影信息
    //http://172.17.8.100/movieApi/movie/v2/findMovieByKeyword
    @GET("movie/v2/findMovieByKeyword")
    Observable<Data<List<ReleaseMovieListBean>>>findMovieByKeyword(@Query("keyword")String keyword,@Query("page")int page,@Query("count")int count);




    /*
        影院接口
     */
    //查询推荐影院信息
//    http://172.17.8.100/movieApi/cinema/v1/findRecommendCinemas
    @GET("cinema/v1/findRecommendCinemas")
    Observable<Data<List<RecommendBean>>> findRecommendCinemas(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);

    //查询附近影院
//    http://172.17.8.100/movieApi/cinema/v1/findNearbyCinemas
    @GET("cinema/v1/findNearbyCinemas")
    Observable<Data<List<NearbyCinemasBean>>> findNearbyCinemas(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("longitude") String longitude, @Query("latitude") String latitude, @Query("page") int page, @Query("count") int count);

//    根据区域查询影院
//    http://172.17.8.100/movieApi/cinema/v2/findCinemaByRegion
//    @GET("cinema/v2/findCinemaByRegion")
//    Observable<Data<>>


    //        查询区域列表
//    http://172.17.8.100/movieApi/tool/v2/findRegionList
    @GET("tool/v2/findRegionList")
    Observable<Data<List<RegionBean>>> findRegionList();





    //附近影院
    @GET("cinema/v1/findNearbyCinemas")
    Observable<Data<List<Cinemas>>> findNearbyCinemas(@Query("page") int page,
                                                      @Query("count") int count);

    //影院区域查询
    @GET("cinema/v2/findCinemaByRegion")
    Observable<Data<List<CinemaName>>> findCinemaByRegion(@Query("regionId") int regionId);


    //电影详情
    @GET("movie/v2/findMoviesDetail")
    Observable<Data<Detail>> findMoviesDetail(@Query("movieId") int movieId);




}
