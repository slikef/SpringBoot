package com.begin;

import com.begin.BeanAction.SQLbean;
import com.begin.Repository.SqlbeanRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
    @Autowired
    SqlbeanRepository sqlbeanRepository;
    @Test
    public void test() throws Exception {
        SQLbean sb = new SQLbean();
        sb.setFjsx("父级事项");
        sqlbeanRepository.save(sb);
        System.out.println("开始啦1");

    }
}