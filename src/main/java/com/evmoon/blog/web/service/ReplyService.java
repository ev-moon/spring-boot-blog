package com.evmoon.blog.web.service;

import com.evmoon.blog.domain.Reply;
import com.evmoon.blog.domain.Users;
import com.evmoon.blog.web.repository.ReplyRepository;
import org.springframework.stereotype.Component;

@Component
public class ReplyService {
    private final ReplyRepository replyRepository;

    private ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public Reply getReplyById(int id) {
        return replyRepository.selectByReplyId(id);
    }

    public void addReply(int id, Users author, Reply reply) {
        reply.setAuthor(author);
        reply.setArticleId(id);
        replyRepository.insertReply(reply);
    }

    public void deleteReply(int id) {
        replyRepository.deleteReply(id);
    }

    public void updateReply(int id, Reply updatedReply) {
        replyRepository.updateReply(id, updatedReply);
    }
}
