package com.zb.mapper;

import com.zb.entity.Comment;
import com.zb.entity.CommentScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
public interface CommentMapper {

    //根据酒店id查评论表，得到酒店综合评分
    public List<Comment> findScore(@Param("hotelid")Integer hotelid);

}
