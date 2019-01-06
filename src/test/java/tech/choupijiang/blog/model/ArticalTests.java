package tech.choupijiang.blog.model;

import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


public class ArticalTests {
    @Test
    public void articalManagedByLombokShouldWork(){
        Artical artical = new Artical("1", "China Baidu Rise ...", "Robin li saied ...", new Date());
        assertThat(artical.getId()).isEqualTo("1");
        assertThat(artical.getName()).isEqualTo("China Baidu Rise ...");
    }
}
