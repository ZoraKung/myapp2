package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoMaker {
    public static void main(String[] args) {
        //生成数据库的实体类,还有版本号
        Schema schema = new Schema(1, "com.student.entity");
        addStudent(schema);
        //指定dao
        schema.setDefaultJavaPackageDao("com.student.dao");
        try {
            //指定路径
            new DaoGenerator().generateAll(schema, "F:\\workspace\\android\\GreenDao\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据库的表
     *
     * @param schema
     */
    public static void addStudent(Schema schema) {
        //创建数据库的表
        Entity entity = schema.addEntity("Student");
        //主键 是int类型
        entity.addIdProperty();
        //名称
        entity.addStringProperty("name");
        //年龄
        entity.addIntProperty("age");
        //地址
        entity.addStringProperty("address");
    }
}
