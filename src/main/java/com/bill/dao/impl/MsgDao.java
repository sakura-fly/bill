package com.bill.dao.impl;

import com.bill.model.Msg;
import com.bill.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MsgDao extends BaseDaoImpl<Msg> {

    // @Override
    public List<Msg> list(Msg msg, String starttime, String endtime) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Msg> r = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            NativeQuery q = session.createSQLQuery("select * from Msg where " + (msg.getUserid() == 0 ? "" : "userid = :userid and ") + "time > :starttime and time < :endtime");
            if (msg.getUserid() != 0 )
               q.setParameter("userid",msg.getUserid());
            q.setParameter("starttime",sdf.parse(starttime));
            q.setParameter("endtime",sdf.parse(endtime));

            q.list();
            q.addEntity(Msg.class);
            // Query query = HibernateUtils.getQuery(session, msg,starttime,endtime);
            r = q.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return r;
    }
}
