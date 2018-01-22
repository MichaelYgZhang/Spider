package com.spider.service;

import com.spider.bean.Match;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by michael on 2017/10/25.
 */
@Service
public interface IMatchService {
    boolean store(Match match);

    Match getMatch(Match match);

    List<Match> getAllMatch();
}
