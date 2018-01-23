package com.begin.ExeclService;

import com.begin.BeanAction.SQLbean;
import com.begin.Repository.SqlbeanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FirstServer {

    @Autowired
    SqlbeanRepository sqlbeanRepository;

    @Autowired
    FileService fileService;

    @Autowired
    POIService poiService;

    @Autowired
    POIService2 poiService2;

    public void start(String[] profiles,String path){
        System.out.println("开始啦");
        List<SQLbean> list = new ArrayList();
        List<File> files = fileService.pathToFile(path);
        int size = files.size();
        for( int i = 0; i<size;i++){
            SQLbean sqLbean = poiService.readExcel(files.get(i));
            if(sqLbean!=null) list.add(sqLbean);
        }

        initBaseData(list);

    }

    public void start2(String[] profiles,String path){
        System.out.println("开始啦");
        List<SQLbean> list = new ArrayList();
        List<File> files = fileService.pathToFile(path);
        int size = files.size();
        for( int i = 0; i<size;i++){
            List<Map<String, String>> list1 = poiService2.readExcel2(files.get(i));
            int size1 = list1.size();
            for (int j=0 ;j<size1;j++){
                SQLbean sqLbean = poiService2.readMap(list1.get(j));
                if(sqLbean!=null) list.add(sqLbean);
            }
        }
        initBaseData(list);
    }

    public  void   initBaseData(List<SQLbean> list){
        sqlbeanRepository.save(list);
        System.out.println("结束");
    }


    public  void   initBaseData1(){
        SQLbean qaz = sqlbeanRepository.findByFjsx("父级事项2");
        System.out.println("开始啦1"+qaz.getId());
    }
}
