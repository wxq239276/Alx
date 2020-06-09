package com.zb.service;

import com.zb.entity.Comment;
import com.zb.entity.CommentScore;
import com.zb.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
@RestController
public class CommentService {
    @Autowired(required = false)
    private CommentMapper commentMapper;

    @GetMapping(value = "/findScore/{hotelid}")
    public List<Comment> findScore(@PathVariable("hotelid")Integer hotelid){
        return commentMapper.findScore(hotelid);
    };

}
