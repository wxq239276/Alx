package com.zb.controller;

import com.zb.entity.CommentScore;
import com.zb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/myfindScore/{hotelid}")
    public CommentScore findScore(@PathVariable("hotelid") Integer hotelid) {
        return commentService.findScore(hotelid);
    }
}
