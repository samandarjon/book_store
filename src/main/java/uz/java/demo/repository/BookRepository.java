package uz.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.java.demo.dto.BookDtoWithAuthor;
import uz.java.demo.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select new uz.java.demo.dto.BookDtoWithAuthor(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) " +
            "from Book b join Author a on b.authorId = a.id where b.id = :id")
    BookDtoWithAuthor getBookWithJoin(@Param("id") Long id);

    @Query(value = "select * from Book b limit :limit offset :offset", nativeQuery = true)
    List<Book> findAllBook(@Param("limit") Long limit, @Param("offset") Long offset);

    List<Book> findAllByAuthorId(Long authorId);

    @Query("select new uz.java.demo.dto.BookDtoWithAuthor(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) " +
            "from Book b join Author a on b.id = a.id where a.name like(CONCAT('%',:name,'%'))")
    List<BookDtoWithAuthor> findAllByAuthorName(@Param("name") String name);

    @Query("select new uz.java.demo.dto.BookDtoWithAuthor(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) " +
            "from Book b join Author a on b.id = a.id where b.ISBN_10=:isbn")
    List<BookDtoWithAuthor> findAllByISBN_10(@Param("isbn") String isbn);

    @Query("select new uz.java.demo.dto.BookDtoWithAuthor(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) " +
            "from Book b join Author a on b.id = a.id where a.name like(CONCAT('%',:name,'%')) and b.ISBN_10=:isbn")
    List<BookDtoWithAuthor> findAllByAuthorNameAndISBN_10(@Param("name") String name, @Param("isbn") String isbn_10);

}
