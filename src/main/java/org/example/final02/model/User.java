package org.example.final02.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role = "ROLE_USER";

  //  @OneToMany(mappedBy = "user")
  //  private Set<Book> borrowedBooks;

  //  public Set<Book> getBorrowedBooks() {
  //      return borrowedBooks;
  //  }

  //  public void setBorrowedBooks(Set<Book> borrowedBooks) {
  //      this.borrowedBooks = borrowedBooks;
  //  }
}
