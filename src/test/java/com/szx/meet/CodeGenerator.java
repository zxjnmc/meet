package com.szx.meet;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.szx.meet.base.BaseEntity;

/**
 * @Author szx
 * @Date 2021/7/3 09:35
 * @Description 代码生成
 */
public class CodeGenerator {

    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String oPath = System.getProperty("user.dir");//得到当前项目的路径
        gc.setOutputDir(oPath + "/src/main/java");   //生成文件输出根目录
        gc.setOpen(false);//生成完成后不弹出文件框
        gc.setFileOverride(true);  //文件覆盖
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("szx");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);   //设置数据库类型，我是postgresql
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("szx");
        dsc.setPassword("1234");
        dsc.setUrl("jdbc:mysql://192.168.0.107:3306/meet_soul");  //指定数据库
        autoGenerator.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);      // 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude("user");     // 需要生成的表
        strategy.setSuperEntityClass(BaseEntity.class);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperEntityColumns("id", "create_time", "update_time");
        strategy.setLogicDeleteFieldName("is_use");
        autoGenerator.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.szx.meet");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("mapper");
        autoGenerator.setPackageInfo(pc);

        // 执行生成
        autoGenerator.execute();

    }

}
