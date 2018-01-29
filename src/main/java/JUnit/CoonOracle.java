package JUnit;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mozs on 17-8-18.
 */
public class CoonOracle {
    protected static Logger logger = LoggerFactory.getLogger(CoonOracle.class);

    public boolean conOracle() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String OracleDriver = "oracle.jdbc.driver.OracleDriver";
        String URL = "jdbc:oracle:thin:@ip:1521:orcl";
        //查看NUM_ROWS行数的存储结构名（这种方式查看行数时要先进行表的统计，即执行存储结构）
        //call + 包名 + 存储过程名(传入、传出值)
        //单个表统计数据的统计数据更新
        String strOneName = "{call dbms_stats.gather_table_stats(?,?,cascade=>true)}";
        //更新整个用户所有对象（包括表、索引、簇）的统计数据更新,method_opt可以指定对象类型（下面是只统计表）
        String strAllName = "{call  dbms_stats.gather_schema_stats(?,estimate_percent=>100,method_opt=>'for table')}";
        //按行列出每一张表的表明与表行数（需要执行存储结构）
        String sql10 = "select TABLE_NAME,NUM_ROWS from dba_all_tables where owner='KAIFA' order by num_rows desc";
        //查看该用户下的所有表的行数（需要执行存储结构）
        String sql1101 = "SELECT sum(num_rows) FROM user_tables";
        //获取该用户下的所有表的表名
        String sql = "select t.table_name from user_tables t ";
        //获得该用户下的所有表实际占用的磁盘大小=行数*行对象的平均值（单位bytes）（需要执行存储结构）
        String sql211 = "select table_name,num_rows * avg_row_len from user_tables ";
        //获取KAI2表行数（不需要执行存储结构）
        String sql11 = "SELECT count(*)  FROM KAI2";
        //获取KAI2表注释
        String sql1 = "select comments from user_tab_comments t where t.table_name ='KAI2'";
        //获得KAI2表的结构信息包括（字段名、字段类型、字段长度）
        String sql3 = "select column_name,data_type,data_length from user_tab_cols t where t.table_name ='KAI2'";
        //KAI2表的字段注释
        String sql2 = "select comments from user_col_comments t where t.table_name ='KAI2'";

        try {
            Class.forName(OracleDriver);
            conn = DriverManager.getConnection(URL, "kaifa", "kaifa");
            //使用java.sql.*类,创建 CallableStatement 对象
            CallableStatement cs = conn.prepareCall(strOneName);
            //传入参数
            cs.setString(1, "KAIFA");
            cs.setString(2, "H123");
            //执行
            cs.execute();
            preparedStatement = conn.prepareStatement(sql211);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String string = rs.getString(1);
                String string1 = rs.getString(2);
                logger.info("{}:{}", string, string1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
