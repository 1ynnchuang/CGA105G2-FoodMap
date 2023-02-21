package tw.idv.anthony.web.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import tw.idv.anthony.web.member.entity.Member;

import java.util.List;

@Mapper
public interface MemberMapper {
//    方法名稱要對MemberMapper.xml 的id
//    回傳值要對應resultType
    List<Member> selectAll();
}
