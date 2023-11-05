package com.xh.sse.push.example;

import com.xh.sse.push.example.util.SseEmitterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author H.Yang
 * @date 2023/8/12
 */
@Slf4j
@RestController
public class SsePushController {


    /**
     * 建立连接
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/connect")
    public SseEmitter connect(String userId) {
        log.info("用户ID：{}", userId);
        return SseEmitterUtil.connect(userId);
    }

    /**
     * 发送消息
     *
     * @param userId
     * @param content
     * @return
     */
    @PostMapping("/push/{userId}")
    public String push(@PathVariable("userId") String userId, String content) {
        log.info("用户ID：{}", userId);
        SseEmitterUtil.sendMessage(userId, content);
        return "给用户：" + userId + ",发送了消息：" + content;
    }

    /**
     * 关闭连接
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/close")
    public String close(String userId) {
        log.info("关闭连接：{}", userId);
        SseEmitterUtil.removeUser(userId);
        return "close 成功";
    }

}
