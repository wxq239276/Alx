package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.client.CommentFeignClient;
import com.zb.client.HotelFeignClient;
import com.zb.entity.Comment;
import com.zb.entity.CommentScore;
import com.zb.entity.HotelDetail;
import com.zb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentFeignClient commentFeignClient;
    @Autowired
    private HotelFeignClient hotelFeignClient;
    @Autowired
    private RedisTemplate redisTemplate;

    //对外提供的业务类，只能从redis中查（查各项综合评分）
    @Override
    public CommentScore findScore(Integer hotelid) {
      String key = "score" + hotelid;
      if (redisTemplate.hasKey(key)){
            String jsonStr=redisTemplate.opsForValue().get(key).toString();
            CommentScore commentScore= JSON.parseObject(jsonStr,CommentScore.class);
            return commentScore;
      }else {
          this.findCommentScore();
          String jsonStr=redisTemplate.opsForValue().get(key).toString();
          CommentScore commentScore= JSON.parseObject(jsonStr,CommentScore.class);
          return commentScore;
      }
    }

    //设置一个定时任务，每天0点执行，将计算出来的综合评分存到CommentScore对象中（redis里）
    @Override
    @Scheduled(cron = "00 32 10 * * ?")
    public void findCommentScore() {
        List<HotelDetail>list=hotelFeignClient.findHotelAll();
        for (HotelDetail h:list){
            double positionScore=0;
            double facilitiesScore=0;
            double serviceScore=0;
            double hygieneScore=0;
            double myscore=0;
            int commentnum=0;
            List<Comment>commentList=commentFeignClient.findScore(h.getId());
            for (Comment c : commentList) {
                myscore += c.getScore();
                positionScore += c.getPositionScore();
                facilitiesScore += c.getFacilitiesScore();
                serviceScore += c.getServiceScore();
                hygieneScore += c.getHygieneScore();
                commentnum++;
            }

            myscore /= commentList.size();
            myscore=(double) Math.round(myscore * 10) / 10;
            positionScore /= commentList.size();
            positionScore=(double) Math.round(positionScore * 10) / 10;
            facilitiesScore /= commentList.size();
            facilitiesScore=(double) Math.round(facilitiesScore * 10) / 10;
            serviceScore /= commentList.size();
            serviceScore=(double) Math.round(serviceScore * 10) / 10;
            hygieneScore /= commentList.size();
            hygieneScore=(double) Math.round(hygieneScore * 10) / 10;
            CommentScore commentScore=new CommentScore();

           commentScore.setFacilitiesScore(facilitiesScore);
           commentScore.setHygieneScore(hygieneScore);
           commentScore.setPositionScore(positionScore);
           commentScore.setServiceScore(serviceScore);
           commentScore.setScore(myscore);
            commentScore.setCommentnum(commentnum);
           commentScore.setHotelid(h.getId());

            String key = "score" + h.getId();
            if (redisTemplate.hasKey(key)){
                redisTemplate.delete(key);
            }
            redisTemplate.opsForValue().set(key,JSON.toJSONString(commentScore));
        }
    }
}
