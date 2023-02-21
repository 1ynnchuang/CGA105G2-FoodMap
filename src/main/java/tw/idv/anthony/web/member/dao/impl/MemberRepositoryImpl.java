package tw.idv.anthony.web.member.dao.impl;

import org.hibernate.Session;
import tw.idv.anthony.web.member.dao.MemberOperation;
import tw.idv.anthony.web.member.entity.Member;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class MemberRepositoryImpl implements MemberOperation {
    @PersistenceContext
    private Session session;

    @Override
    public int update(Member member) {
        final StringBuilder sql = new StringBuilder()
                .append("update MEMBER set ");
        final String password = member.getPassword();
        if (password != null && !password.isEmpty()) {
            sql.append("PASSWORD = :password,");
        }
        final byte[] img = member.getImage();
        if (img != null && img.length != 0) {
            sql.append("IMAGE = :image,");
        }
        sql.append("NICKNAME = :nickname,").append("PASS = :pass,")
                .append("ROLE_ID = :roleId,").append("UPDATER = :updater,")
                .append("LAST_UPDATED_DATE = NOW() ").append("WHERE USERNAME = :username");
        Query query = session.createNativeQuery(sql.toString());
        if (password != null && !password.isEmpty()) {
            query.setParameter("password", member.getPassword());
        }
        if (img != null && img.length != 0) {
            query.setParameter("image", img);
        }
        return query.setParameter("nickname", member.getNickname())
                .setParameter("pass", member.getPass()).setParameter("roleId", member.getRoleId())
                .setParameter("updater", member.getUpdater()).setParameter("username", member.getUsername())
                .executeUpdate();
    };
}
