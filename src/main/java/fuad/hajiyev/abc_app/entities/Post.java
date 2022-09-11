package fuad.hajiyev.abc_app.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    @JsonIgnore
    User user;

    String title;

    @Lob
    @Column(columnDefinition = "text")
    String text;
}

