package com.hsm.flyway;

import org.flywaydb.core.Flyway;

/**
 * @author: steven
 * @date: 2020/10/17
 * @description
 */
public class FlywayTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/flyway?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&rewriteBatchedStatements=true&useSSL=false&serverTimezone=GMT%2B8";
        String user = "root";
        String password = "root";
        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();

        // 创建 flyway_schema_history 表
//		flyway.baseline();

        // 删除 flyway_schema_history 表中失败的记录
//		flyway.repair();

        // 检查 sql 文件
//		flyway.validate();

        // 执行数据迁移
        flyway.migrate();

        // 删除当前 schema 下所有表
//		flyway.clean();
    }
}
