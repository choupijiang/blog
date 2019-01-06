package tech.choupijiang.blog.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document
public class Artical {
    @Id final private  String id;
    final private  String name;
    final private  String content;
    final private Date createTime;

}


