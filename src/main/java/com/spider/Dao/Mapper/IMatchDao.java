package com.spider.Dao.Mapper;


import com.spider.bean.Match;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.JDBCType;
import java.util.List;

@Mapper
public interface IMatchDao {
    @Insert(value = "insert into t_match (cid,game_name,game_end_time,home_team,guest_team,win,defeats,drew,rq_num,rq_win,rq_defeats,rq_drew,create_time)" +
            " values (#{cid,jdbcType=VARCHAR},#{gameName,jdbcType=VARCHAR},#{gameEndTime,jdbcType=TIMESTAMP},#{homeTeam,jdbcType=VARCHAR},#{guestTeam,jdbcType=VARCHAR}" +
            "           ,#{win,jdbcType=DECIMAL},#{defeats,jdbcType=DECIMAL},#{drew,jdbcType=DECIMAL}" +
            "           ,#{rqNum,jdbcType=DECIMAL},#{rqWin,jdbcType=DECIMAL}, #{rqDefeats,jdbcType=DECIMAL},#{rqDrew,jdbcType=DECIMAL}" +
            "           ,#{createTime,jdbcType=TIMESTAMP})")
    int store(Match match);

    @Delete(value = "delete from t_match")
    int cleanAll();

    /**
     * 查询赔率是否发生变化 TODO Cache
     * @param match
     * @return
     */
    @Select(value = "select * from t_match where cid= #{cid,jdbcType=VARCHAR}" +
//            "   and win = #{win,jdbcType=DECIMAL}" +
//            "   and defeats = #{defeats,jdbcType=DECIMAL}" +
//            "   and drew = #{drew,jdbcType=DECIMAL}" +
//            "   and rq_num = #{rqNum,jdbcType=DECIMAL}" +
//            "   and rq_win = #{rqWin,jdbcType=DECIMAL}" +
//            "   and rq_defeats = #{rqDefeats,jdbcType=DECIMAL}" +
//            "   and rq_drew = #{rqDrew,jdbcType=DECIMAL}"
            " order by create_time desc limit 1;"
            )
    @Results(value = {
            @Result(column = "game_name", property = "gameName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "game_end_time", property = "gameEndTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "home_team", property = "homeTeam", jdbcType = JdbcType.VARCHAR),
            @Result(column = "guest_team", property = "guestTeam", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rq_num", property = "rqNum", jdbcType = JdbcType.DECIMAL),
            @Result(column = "rq_win", property = "rqWin", jdbcType = JdbcType.DECIMAL),
            @Result(column = "rq_defeats", property = "rqDefeats", jdbcType = JdbcType.DECIMAL),
            @Result(column = "rq_drew", property = "rqDrew", jdbcType = JdbcType.DECIMAL),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP)})
    Match getMatch(Match match);

    @Select(value = "select * from t_match")
    @Results(value = {
            @Result(column = "game_name", property = "gameName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "game_end_time", property = "gameEndTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "home_team", property = "homeTeam", jdbcType = JdbcType.VARCHAR),
            @Result(column = "guest_team", property = "guestTeam", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rq_num", property = "rqNum", jdbcType = JdbcType.DECIMAL),
            @Result(column = "rq_win", property = "rqWin", jdbcType = JdbcType.DECIMAL),
            @Result(column = "rq_defeats", property = "rqDefeats", jdbcType = JdbcType.DECIMAL),
            @Result(column = "rq_drew", property = "rqDrew", jdbcType = JdbcType.DECIMAL),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP)})
    List<Match> getAllMatch();


}

