package uz.java.demo.dao;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uz.java.demo.dto.AuthorRatingDto;
import uz.java.demo.dto.BestSellerBookDto;
import uz.java.demo.dto.BestSellerBookV2Dto;
import uz.java.demo.dto.UserRatingDto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.awt.geom.QuadCurve2D;
import java.util.List;

@Repository
public class BookDao {
    private final EntityManager entityManager;

    @Autowired
    public BookDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<?> bestSellerBooks() {
        return entityManager.createQuery("select count(p) as quantity, sum(p.purchasePrice) as price, p.bookId as bookId, b as book  from Purchase p join Book b on b.id=p.bookId  group by p.bookId, b")
                .unwrap(org.hibernate.query.Query.class).setResultTransformer(Transformers.aliasToBean(BestSellerBookDto.class)).getResultList();
    }

    public List<?> bestSellerBooksV2() {
        Query query = entityManager.createNativeQuery("select  b.id as book_id,\n" +
                "        b.name as book_name,\n" +
                "        sum(p.quantity) as quantity,\n" +
                "        sum(p.purchase_price) as price,\n" +
                "        sum(c.cnt) as comment_count\n" +
                "         from book b,\n" +
                "              (select p.book_id, sum(p.purchase_price) as purchase_price, count(p.id) as quantity  from purchase p group by p.book_id) p,\n" +
                "              (select c.book_id, count(c.id) as cnt from comment c  group by c.book_id) c\n" +
                "        where b.id=p.book_id\n" +
                "            and b.id = c.book_id\n" +
                "        group by b.id, b.name\n" +
                "order by quantity desc\n", BestSellerBookV2Dto.class);
        return query.getResultList();
    }

    public List<?> authorRating() {
        Query query = entityManager.createNativeQuery("select sum(b.comment_count) as comment_count,\n" +
                "       a.id,\n" +
                "       count(b.book_id)     as book_count,\n" +
                "       sum(p.quantity)      as quantity\n" +
                "from author a,\n" +
                "     (select b.id        as book_id,\n" +
                "             count(c.id) as comment_count,\n" +
                "             b.author_id as author_id\n" +
                "      from book b\n" +
                "               left join comment c on b.id = c.book_id\n" +
                "      group by b.author_id, b.id) as b,\n" +
                "     (select p.book_id,\n" +
                "             count(p.id) as quantity\n" +
                "      from purchase p\n" +
                "      group by p.book_id) p\n" +
                "where b.author_id = a.id\n" +
                "  and p.book_id = b.book_id\n" +
                "group by a.id order by quantity desc \n" +
                "\n", AuthorRatingDto.class);
        return query.getResultList();
    }

    public List<?> getUserRating() {
        Query query = entityManager.createNativeQuery("select u.id,\n" +
                "       sum(u.purchase_price) as purchase_price,\n" +
                "       sum(u.book_count)     as book_count,\n" +
                "       sum(c.comment_count)  as comment_count\n" +
                "from (select u.id,\n" +
                "             sum(purchase_price) as purchase_price,\n" +
                "             count(p.book_id)    as book_count\n" +
                "\n" +
                "      from users u\n" +
                "               left join purchase p on u.id = p.user_id\n" +
                "      group by u.id) u,\n" +
                "     (select u.id,\n" +
                "             count(c) as comment_count\n" +
                "      from users u\n" +
                "               left join comment c on u.id = c.user_id\n" +
                "      group by u.id) c\n" +
                "where c.id = u.id\n" +
                "group by u.id\n" +
                "order by u.id\n" +
                "\n", UserRatingDto.class);
        return query.getResultList();
    }

//    public List theBestAuthor() {
//        return entityManager.createQuery(
//                "select  count(b) as bookCount, a, count(c) as commentCount from Author a join Book b on a.id = b.authorId join Comment c on c.bookId=b.id group by a\n"
//        ).unwrap(Query.class).setResultTransformer(Transformers.aliasToBean(TheBestAuthor.class)).getResultList();
//    }
}
