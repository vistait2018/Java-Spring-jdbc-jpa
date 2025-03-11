package com.pks.example;

import com.pks.example.model.OrderDetails;
import com.pks.example.model.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class JDBCCommandLineRunner implements CommandLineRunner {


    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Logger log = LoggerFactory.getLogger(JDBCCommandLineRunner.class);

    public JDBCCommandLineRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("In JDBC COMMAND LINE RUNNER");
        log.info("Truncating Table");
        jdbcTemplate.execute("truncate table order_details");
        log.info("Truncating Table ended");
        
        insertHardCodedData();
        insertDataUsingPreparedStatement(2,"sewing-machine","bola","lagos");
        insertDataUsingNamedJdbcTemplate(3,"marker","bola","lagos");
        deleteData(2);
        insertBatchUpdate(5);
        selectOrder();
        selectSingleOrder(5);
    }

    private void selectSingleOrder(int orderId) {

        try{
            System.out.println("selecting with order_id " + orderId);

            String query = """
                              select * from order_details 
                              where order_id = :orderId
                          """;
            MapSqlParameterSource mapSqlParameterSource =
                    new MapSqlParameterSource();
            mapSqlParameterSource.addValue("orderId",orderId);

            OrderDetails orderDetails =
                    namedParameterJdbcTemplate.queryForObject(query,
                            mapSqlParameterSource,new OrderMapper());
            System.out.println("selecting single order "+ orderDetails);
        }catch(EmptyResultDataAccessException er){
            System.out.println("no data with id "+orderId + "found" + er.getLocalizedMessage());
        }

    }

    private void selectOrder() {
        List<OrderDetails> orderDetails =
                jdbcTemplate.query("select * from order_details",new OrderMapper());
        System.out.println("selecting all orders "+ orderDetails);
    }

    private void insertBatchUpdate(int count)  {
            String query = """
                              insert into order_details 
                              values(:order_id,
                              :item_name,
                              :customer_name,
                              :address)
                          """;

            Map[] params = new HashMap[count];
            IntStream.range(0,count).forEach(i->{
                Map param = new HashMap();
                param.put("order_id",i+4);
                param.put("item_name","itemName"+i);
                param.put("customer_name","customerName"+i);
                param.put("address","address+"+i);
                params[i]= param;

        });

            int[] rowCounts = namedParameterJdbcTemplate.batchUpdate(query,params);
            System.out.println("Batch Update "+ rowCounts.length);
}

    private void deleteData(int orderId) {
        System.out.println("deleting order_id " + orderId);
        String query = """
                              delete from order_details 
                              where order_id = :order_id
                          """;
        Map param = new HashMap();
        param.put("order_id",orderId);
        int rowCount = namedParameterJdbcTemplate.update(query,param);
        System.out.println("deleted order_id " + orderId);
    }

    private void insertDataUsingNamedJdbcTemplate(int orderId,
                                                 String itemName,
                                                 String customerName,
                                                             String address) {
                    String query = """
                              insert into order_details 
                              values(:order_id,
                              :item_name,
                              :customer_name,
                              :address)
                          """;
        Map params = new HashMap();
        params.put("order_id",orderId);
        params.put("item_name",itemName);
        params.put("customer_name",customerName);
        params.put("address",address);
        int rowCount = namedParameterJdbcTemplate.update(query,params);
    }

    private void insertDataUsingPreparedStatement(int orderId,
                                                 String itemName,
                                                 String customerName,
                                                 String address) {
      String query = """
                  insert into order_details values(?,?,?,?)
              """;

      int rowCount = jdbcTemplate.update(query, new PreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement ps) throws SQLException {
              ps.setInt(1, orderId);
              ps.setString(2,itemName);
              ps.setString(3,customerName);
              ps.setString(4,address);
          }
      });

        System.out.println("Row count " +rowCount);

    }


    private void insertHardCodedData() {
        log.info("Insert beginning");
        jdbcTemplate.execute("insert into order_details values(1,'pc','jide','sagamu')");
        log.info("Insert ending");
    }
}
