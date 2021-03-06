package com.lgl.greendao;

import android.content.Context;
import android.util.Log;

import com.student.dao.StudentDao;
import com.student.entity.Student;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 完成对某一张表的具体操作
 * Created by LGL on 2016/7/2.
 */
public class CommonUtils {

    //TAG
    private static final String TAG = CommonUtils.class.getSimpleName();

    private DaoManager daoManager;

    //构造方法
    public CommonUtils(Context context) {
        daoManager = DaoManager.getInstance();
        daoManager.initManager(context);
    }

    /**
     * 对数据库中student表的插入操作
     *
     * @param student
     * @return
     */
    public boolean insertStudent(Student student) {
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(student) != -1 ? true : false;
        return flag;
    }

    /**
     * 批量插入
     *
     * @param students
     * @return
     */
    public boolean inserMultStudent(final List<Student> students) {
        //标识
        boolean flag = false;
        try {
            //插入操作耗时
            daoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Student student : students) {
                        daoManager.getDaoSession().insertOrReplace(student);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改
     *
     * @param student
     * @return
     */
    public boolean updateStudent(Student student) {
        boolean flag = false;
        try {
            daoManager.getDaoSession().update(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param student
     * @return
     */
    public boolean deleteStudent(Student student) {
        boolean flag = false;
        try {
            //删除指定ID
            daoManager.getDaoSession().delete(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //daoManager.getDaoSession().deleteAll(); //删除所有记录
        return flag;
    }

    /**
     * 查询单条
     *
     * @param key
     * @return
     */
    public Student listOneStudent(long key) {
        return daoManager.getDaoSession().load(Student.class, key);
    }

    /**
     * 全部查询
     *
     * @return
     */
    public List<Student> listAll() {
        return daoManager.getDaoSession().loadAll(Student.class);
    }

    /**
     * 原生查询
     */
    public void queryNative() {
        //查询条件
        String where = "where name like ? and _id > ?";
        //使用sql进行查询
        List<Student> list = daoManager.getDaoSession().queryRaw(Student.class, where,
                new String[]{"%l%", "6"});
        Log.i(TAG, list + "");
    }

    /**
     * QueryBuilder查询大于
     */
    public void queryBuilder() {
        //查询构建器
        QueryBuilder<Student> queryBuilder = daoManager.getDaoSession().queryBuilder(Student.class);
        //查询年龄大于19的北京
        List<Student> list = queryBuilder.where(StudentDao.Properties.Age.ge(19)).where(StudentDao.Properties.Address.like("北京")).list();
        Log.i(TAG, list + "");
    }
}
