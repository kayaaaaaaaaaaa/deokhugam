package deokhugam.deokhugam.book.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deokhugam.deokhugam.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {}
