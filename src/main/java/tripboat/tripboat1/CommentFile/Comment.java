package tripboat.tripboat1.CommentFile;


import lombok.Getter;
import lombok.Setter;
import tripboat.tripboat1.CommunityFile.Community;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime createDate;

    @Column
    private String content;

    @ManyToOne
    private Community community;


    private LocalDateTime modifyDate;

}
