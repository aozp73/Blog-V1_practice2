package shop.mtcoding.blogv1_2.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardRepository {
    public int insert(@Param("title") String title, @Param("content") String content,
            @Param("thumbnail") String thumbnail,
            @Param("userId") int userId);

    public List<Board> findByAll();

    public Board findById(int id);

    public int updateById(@Param("id") int id, @Param("title") String title, @Param("content") String content,
            @Param("thumbnail") String thumbnail);

    public int deleteById(int id);
}
