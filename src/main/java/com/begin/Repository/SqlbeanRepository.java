package com.begin.Repository;

import com.begin.BeanAction.SQLbean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlbeanRepository extends JpaRepository<SQLbean,Long> {
     SQLbean findByFjsx( String name);
}
