package com.example.niilonkirjakauppa.domain;

import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;

        import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthor(@Param("author")String author);
}
