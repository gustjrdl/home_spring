package tripboat.tripboat1.CommentFile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tripboat.tripboat1.CommunityFile.Community;
import tripboat.tripboat1.CommunityFile.CommunityRepository;
import tripboat.tripboat1.CommunityFile.CommunityService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getList() {
        return this.commentRepository.findAll();
    }

    public void create(Community community, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setCommunity(community);
        this.commentRepository.save(comment);
    }
}
