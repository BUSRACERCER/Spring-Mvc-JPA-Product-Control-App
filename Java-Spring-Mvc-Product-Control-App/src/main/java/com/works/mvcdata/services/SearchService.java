package com.works.mvcdata.services;
import com.works.mvcdata.entities.User;
import com.works.mvcdata.utils.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
    public List<User> search(String q){
        List<User> ls = new ArrayList<>();

        DB db = new DB();
        try {
            String sql="select *from  users where  name like  ? or surname like ? or email like  ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);

            pre.setString(1,"%"+q+'%');
            pre.setString(2,'%'+q+'%');
            pre.setString(3,'%'+q+'%');
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setDate(rs.getString("date"));
                ls.add(u);
            }


        }catch (Exception ex){
            System.err.println("Users Error : "+ex);

        }finally {
            db.close();
        }
        return ls;
    }
    public int totalCount() {
        int count = 0;
        DB db = new DB();
        try {
            String sql = "select  count(pid) as count from users ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count");

            }

        } catch (Exception ex) {
            System.err.println("Pagination Error : " + ex);

        } finally {
            db.close();
        }
        return count;
    }



}
