package uz.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import uz.java.demo.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
