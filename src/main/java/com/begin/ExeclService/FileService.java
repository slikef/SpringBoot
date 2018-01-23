package com.begin.ExeclService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileService {

    public List<File> pathToFile(String path) {
       List<File> list =  new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        LinkedList<File> listDic = new LinkedList<>();
        if (files == null) return list;
        for(int i = 0;i<files.length;i++) {
            File fileOne = files[i];
            if(fileOne.isDirectory()){
                listDic.add(fileOne);
            }else {
                if (StringUtils.substringAfterLast(files[i].getName(), ".").equals("xlsx")) list.add(files[i]);
                if (StringUtils.substringAfterLast(files[i].getName(), ".").equals("xls")) list.add(files[i]);

            }
        }
        File temp;
        while (!listDic.isEmpty()) {
            temp = listDic.removeFirst();
            if (temp.isDirectory()) {
                files = temp.listFiles();
                if (files == null) continue;
                for(int i =0;i<files.length;i++){
                    if (files[i].isDirectory()){
                        listDic.add(files[i]);
                    }else {
                        if (StringUtils.substringAfterLast(files[i].getName(), ".").equals("xlsx")) list.add(files[i]);
                        if (StringUtils.substringAfterLast(files[i].getName(), ".").equals("xls")) list.add(files[i]);
                    }
                }
            }
        }
        return list;
    }
}
