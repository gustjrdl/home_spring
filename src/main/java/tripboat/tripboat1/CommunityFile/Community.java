package tripboat.tripboat1.CommunityFile;

import lombok.Getter;
import lombok.Setter;
import tripboat.tripboat1.CommentFile.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 3000)
    private String content;

    private String subject;

    @Column(length = 20)
    private String region;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE)
    private List<Comment> comment;

    private LocalDateTime modifyDate;

}







