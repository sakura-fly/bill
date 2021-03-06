package com.bill.util;

import com.bill.model.SensorData;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HibernateUtils {
    /**
     * 根据对象生成查询对象
     *  @param session hibernate session对象
     * @param o       查询对象
     */
    public static Query getQuery(Session session, Object o) {
        // 获取字段内容，为空、空字符串、0的不查
        List<SensorData> rre = RrelationData.sensorDataList(o);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<?> query = builder.createQuery(o.getClass());
        Root root = query.from(o.getClass());
        query.select(root);


        queryWhere(rre, query, root, builder);

        return session.createQuery(query);
    }


    //这个有问题
    public static Query getQuery(Session session, Object o, String starttime, String endtime) throws ParseException {
        // 获取字段内容，为空、空字符串、0的不查
        List<SensorData> rre = RrelationData.sensorDataList(o);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<?> query = builder.createQuery(o.getClass());
        Root root = query.from(o.getClass());
        query.select(root);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        queryWhere(rre, query, root, builder);
        // Restrictions
        query.where(builder.gt(root.<Number>get("time"), (Expression<? extends Number>) sdf.parse(starttime)));
        return session.createQuery(query);
    }


    public static void queryWhere(List<SensorData> rre, CriteriaQuery<?> query, Root root, CriteriaBuilder builder) {
        Predicate[] predicates = new Predicate[rre.size()];
        for (int i = 0; i < rre.size(); i++) {
            predicates[i] = builder.equal(root.get(rre.get(i).getSensorId()), rre.get(i).getSensorValue());
        }
        query.where(predicates);
    }
}
