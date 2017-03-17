package com.lgl.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TAG
    public static final String TAG = MainActivity.class.getSimpleName();
    //添加数据
    private Button btn_add;
    //批量添加数据
    private Button btn_add_mult;
    //修改一条数据
    private Button btn_update;
    //删除一条数据
    private Button btn_delete;
    //查询一条数据
    private Button btn_load;
    //查询全部数据
    private Button btn_load_all;
    //QueryBuilder查询
    private Button btn_querybuilder;
    //链接工具类
    private CommonUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    //初始哈View
    private void initView() {

        dbUtils = new CommonUtils(this);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_add_mult = (Button) findViewById(R.id.btn_add_mult);
        btn_add_mult.setOnClickListener(this);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);
        btn_load_all = (Button) findViewById(R.id.btn_load_all);
        btn_load_all.setOnClickListener(this);
        btn_querybuilder = (Button) findViewById(R.id.btn_querybuilder);
        btn_querybuilder.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Student student = new Student();
                student.setId((long) 0);
                student.setName("lgl");
                student.setAge(18);
                student.setAddress("北京");
                dbUtils.insertStudent(student);
                break;
            case R.id.btn_add_mult:
                List<Student> list = new ArrayList<>();
                for (int i = 1; i < 10; i++) {
                    Student studentlist = new Student();
                    studentlist.setName("lgl" + i);
                    studentlist.setAge(18 + i);
                    studentlist.setAddress("北京" + i);
                    list.add(studentlist);
                }
                dbUtils.inserMultStudent(list);
                break;
            case R.id.btn_update:
                Student studentupdate = new Student();
                //根据ID来修改
                studentupdate.setId((long) 1);
                //把年龄改成100岁
                studentupdate.setAge(100);
                studentupdate.setName("lgl");
                studentupdate.setAddress("深圳");
                dbUtils.updateStudent(studentupdate);
                break;
            case R.id.btn_delete:
                Student studentdelete = new Student();
                studentdelete.setId((long) 3);
                dbUtils.deleteStudent(studentdelete);
                break;
            case R.id.btn_load:
                Log.i(TAG, dbUtils.listOneStudent(5) + "");
                break;
            case R.id.btn_load_all:
                List<Student> lists = dbUtils.listAll();
                Log.i(TAG, lists.toString());
                break;
            case R.id.btn_querybuilder:
                dbUtils.queryNative();
                dbUtils.queryBuilder();
                break;
        }
    }
}
