package com.example.cah.service;

import com.example.cah.Dao.PositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    @Autowired
    private final PositionDao positionDao;

    public PositionService(PositionDao positionDao) {
        this.positionDao = positionDao;
    }



    public int getLastEndTag(){
        return positionDao.getLastEndTag();
    }

    public int getLastMNum(){
        return positionDao.getLastMNum();
    }
}
