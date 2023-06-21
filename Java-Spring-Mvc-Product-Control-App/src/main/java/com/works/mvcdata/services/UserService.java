package com.works.mvcdata.services;

import com.works.mvcdata.entities.User;
import com.works.mvcdata.utils.DB;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    DB db = new DB();

    public List<User> users(int p) {
        List<User> ls = new ArrayList<>();
        DB db = new DB();
        try {
            p = p - 1;
            p =p*5;
            String sql = "select * from users where status =1 order by uid asc limit ?,5";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,p);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setAge(rs.getInt("age"));
                u.setDate(rs.getString("date"));
                ls.add(u);
            }
        } catch (Exception ex) {
            System.err.println("Users Error : " + ex);
        } finally {
            db.close();
        }
        return ls;
    }

    public User loginUser(User user) {
        User u = null;

        try {
            String sql = "select * from users where email =? and password=? ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, user.getEmail());
            pre.setString(2, user.getPassword());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setAge(rs.getInt("age"));
                u.setDate(rs.getString("date"));

            }

        } catch (Exception ex) {
            System.err.println("Login User ERROR :" + ex);

        } finally {
            db.close();
        }
        return u;

    }

    public User single(int uid) {
        User u = new User();
        try {
            String sql = "select*from users where uid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, uid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                u.setUid(rs.getInt("uid"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setAge(rs.getInt("age"));
                u.setDate(rs.getString("date"));

            }

        } catch (Exception ex) {
            System.err.println("User Info ERROR : " + ex);
        } finally {
            db.close();
        }
        return u;
    }

    public int userSave(User user) {
        int status = 0;
        try {
            String sql = "insert into users values(null,?,?,?,?,1,?,now())";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, user.getName());
            pre.setString(2, user.getSurname());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPassword());
            pre.setInt(5, user.getAge());
            status = pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("User Save ERROR : " + ex);

        } finally {
            db.close();
        }
        return status;
    }

    public int deleteUser(int uid, int dbStatus) {
        int status = 0;
        try {
            String sql = "update  users set status =? where uid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, dbStatus);
            pre.setInt(2, uid);
            status = pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("User Delete ERROR: " + ex);
        } finally {
            db.close();
        }
        return status;
    }

    public int updateUser(User user) {
        int status = 0;
        DB db = new DB();

        try {
            String sql = "update users set name=?, surname=?, email=?, password=?, age=? where uid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, user.getName());
            pre.setString(2, user.getSurname());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPassword());
            pre.setInt(5, user.getAge());
            pre.setInt(6, user.getUid());
            status = pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("Update ERROR : " + ex);
        } finally {
            db.close();
        }
        return status;
    }

    public int totalCount() {
        int count = 0;
        try {
            String sql = "select count(uid) as count from users where status=1";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }

        } catch (Exception ex) {
            System.err.println("Pagination ERROR : " + ex);

        } finally {
            db.close();
        }
        return count;
    }


}
