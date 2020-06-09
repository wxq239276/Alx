package com.zb.service;

import com.zb.entity.CommentScore;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
public interface CommentService {
    public CommentScore findScore(Integer hotelid);

    public void findCommentScore();
}
