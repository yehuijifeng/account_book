package com.lh.accountbook.db.base;

import com.j256.ormlite.dao.Dao;
import com.lh.accountbook.appliaction.ABAppliaction;
import com.lh.accountbook.utils.LogUtils;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Luhao
 * on 2016/1/6.
 * 数据库dao方法的代理类
 */
public class BaseDBDao<T> {

    /**
     * 条件查询QueryBuilder的使用：
     * 1、简单的where 条件
     * articleDaoOpe.queryBuilder().where().eq("user_id", userId).query();直接返回Article的列表
     * <p>
     * 2,where and
     * QueryBuilder<Article, Integer> queryBuilder = articleDaoOpe
     * .queryBuilder();
     * Where<Article, Integer> where = queryBuilder.where();
     * where.eq("user_id", 1);
     * where.and();
     * where.eq("name", "xxx");
     * <p>
     * //或者
     * articleDaoOpe.queryBuilder().//
     * where().//
     * eq("user_id", 1).and().//
     * eq("name", "xxx");
     * <p>
     * <p>
     * 上述两种都相当与：select * from tb_article where user_id = 1 and name = 'xxx' ;
     * <p>
     * <p>
     * 3、更复杂的查询
     * <p>
     * where.or(
     * where.and(
     * where.eq("user_id", 1),
     * where.eq("name", "xxx")),
     * where.and(where.eq("user_id", 2),
     * where.eq("name", "yyy")));
     * <p>
     * 相当于：select * from tb_article where ( user_id = 1 and name = 'xxx' )  or ( user_id = 2 and name = 'yyy' )  ;
     * <p>
     * <p>
     * 4，事务操作
     * TransactionManager.callInTransaction(helper.getConnectionSource(),
     * new Callable<Void>()
     * {
     *
     * @Override public Void call() throws Exception
     * {
     * return null;
     * }
     * });
     */

    /**
     * 数据库帮助类
     */
    private BaseDBHelper dBHelper;
    private Dao dao;
    private Class cla;

    /**
     * 工具类中操作增删改查的dao方法
     * 注意：初始化dao类的时候，数据库表已经被创建
     */
    public BaseDBDao(Class cla) {
        this.cla = cla;
        dBHelper = BaseDBHelper.getInstance(ABAppliaction.INSTANCE);
        try {
            dao = dBHelper.getDao(cla);//获得dao实例，根据传入的实体类来
        } catch (Exception e) {
            LogUtils.e("Dao方法错误：" + e.getMessage());
        }
    }


    /**
     * 是否有数据库辅助类对象存在
     */
    private boolean isOpenDB() {
        return dao != null;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao() {
        if (!isOpenDB()) return null;
        return dao;
    }

    //删除表
    public boolean deleteTable() {
        if (!isOpenDB()) return false;
        return dBHelper.deleteTable(cla);
    }

    //添加表
    public boolean addTable() {
        if (!isOpenDB()) return false;
        return dBHelper.addTable(cla);
    }

    //添加数据
    public int insertData(T obj) {
        try {
            if (!isOpenDB() || obj == null) return 0;
            return dao.create(obj);
        } catch (Exception e) {
            return 0;
        }
    }

    //修改数据
    public int updateData(T obj) {
        try {
            if (!isOpenDB() || obj == null) return 0;
            return dao.update(obj);
        } catch (Exception e) {
            return 0;
        }
    }

    //添加或者修改数据
    public int insertDataAll(Collection<T> list) {
        try {
            if (!isOpenDB() || list == null) return 0;
            return dao.create(list);
        } catch (Exception e) {
            return 0;
        }
    }

    //添加或者修改数据
    public int insertOrUpdate(T obj) {
        try {
            if (!isOpenDB()) return 0;
            Dao.CreateOrUpdateStatus createOrUpdateStatus = dao.createOrUpdate(obj);
            return createOrUpdateStatus.getNumLinesChanged();
        } catch (Exception e) {
            return 0;
        }
    }

    //删除数据，根据id来
    public int deleteDataById(int id) {
        try {
            if (!isOpenDB()) return 0;
            return dao.deleteById(id);
        } catch (Exception e) {
            return 0;
        }
    }

    //根据表名删除
    public int deleteDataByTableName(String tableName) {
        try {
            if (!isOpenDB()) return 0;
            return dao.executeRawNoArgs("delete  from " + tableName);
        } catch (Exception e) {
            return 0;
        }
    }

    //查询全部数据
    public List<T> queryAll() {
        try {
            if (!isOpenDB()) return null;
            return dao.queryForAll();
        } catch (Exception e) {
            return null;
        }
    }

    //通过UserId获取所有的文章
    public Object queryByUserId(int userId) {
        try {
            return dao.queryForId(userId);
        } catch (SQLException e) {
            return null;
        }
    }

    //更具参数查询
    public List<T> queryByName(String userNameStr, String userName) {
        try {
            return dao.queryBuilder().where().eq(userNameStr, userName).query();
        } catch (SQLException e) {
            return null;
        }
    }

    //自定义sql语句，返回影响行数
    public int sql(String sql) {
        try {
            return dao.executeRawNoArgs(sql);
        } catch (SQLException e) {
            return 0;
        }
    }

    /**
     * 释放资源
     */
    public void closeDao() {
        dao = null;
        dBHelper = null;
    }

}
