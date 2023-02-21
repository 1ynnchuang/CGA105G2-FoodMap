package tw.idv.anthony.web.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tw.idv.anthony.web.member.entity.Member;

//基本的新增、修改、刪除、查詢id
public interface MemberRepository extends JpaRepository<Member, Integer>,MemberOperation {
//    此介面的方法命名有要求

    //    p36-41 sql聚集函數
//    exists(子查詢) :回傳布林(判斷有無資料)
//    count:算數量
    Member getByUsername(String username);
    @Query(
            value = "select * from Member where username= :username and password= :password",nativeQuery = true
    )
//    為什麼不用改get
    Member getForLogin(String username, String password);

}
