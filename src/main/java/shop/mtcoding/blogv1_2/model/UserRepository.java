package shop.mtcoding.blogv1_2.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    public int insert(@Param("username") String username, @Param("password") String password,
            @Param("email") String email);

    public List<User> findByAll();

    public User findByUsername(String username);

    public User findById(int id);

    public int updateById(@Param("id") int id, @Param("password") String password, @Param("email") String email);

    public int deleteById(int id);
}
