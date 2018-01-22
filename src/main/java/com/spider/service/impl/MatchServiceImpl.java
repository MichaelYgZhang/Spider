package com.spider.service.impl;

import com.spider.Dao.Mapper.IMatchDao;
import com.spider.bean.Match;
import com.spider.service.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by michael on 2017/10/25.
 */
@Service
public class MatchServiceImpl implements IMatchService{

    @Autowired
    private IMatchDao matchDao;

    @Override
    public boolean store(Match match) {
        int result = matchDao.store(match);
        return result > 0;
    }

    @Override
    public Match getMatch(Match match) {
        return matchDao.getMatch(match);
    }

    @Override
    public List<Match> getAllMatch() {
        return matchDao.getAllMatch();
    }
}
