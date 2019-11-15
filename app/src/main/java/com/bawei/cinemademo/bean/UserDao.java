package com.bawei.cinemademo.bean;

import android.support.annotation.IdRes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Auther: 高晨凯
 * @Date: 2019/11/11 15:39:00
 * @Description:
 */
@Entity
public class UserDao {
        @Id(autoincrement = true)
        private Long userId;
        private String sessionId;
        public boolean login;
        public boolean getLogin() {
                return this.login;
        }
        public void setLogin(boolean login) {
                this.login = login;
        }
        public String getSessionId() {
                return this.sessionId;
        }
        public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
        }
        public Long getUserId() {
                return this.userId;
        }
        public void setUserId(Long userId) {
                this.userId = userId;
        }
        @Generated(hash = 439437325)
        public UserDao(Long userId, String sessionId, boolean login) {
                this.userId = userId;
                this.sessionId = sessionId;
                this.login = login;
        }
        @Generated(hash = 917059161)
        public UserDao() {
        }


}
