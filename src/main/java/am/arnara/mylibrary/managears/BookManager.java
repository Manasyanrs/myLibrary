package am.arnara.mylibrary.managears;

import am.arnara.mylibrary.db.DBConnectionProvider;
import am.arnara.mylibrary.models.Author;
import am.arnara.mylibrary.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();
    private final AuthorManager AUTHOR_MANAGER = new AuthorManager();

    public List<Book> searchBook(String searchParam) {
        List<Book> books = new ArrayList<>();
        String sql = String.format("select * from books where title like '%s' or description like '%s'",
                "%"+searchParam+"%","%"+searchParam+"%");
        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int authorId = resultSet.getInt("author_id");
                Author authorByID = AUTHOR_MANAGER.getAuthorByID(authorId);
                books.add(
                        Book.builder()
                                .id(resultSet.getInt("id"))
                                .title(resultSet.getString("title"))
                                .description(resultSet.getString("description"))
                                .price(resultSet.getDouble("price"))
                                .author_id(authorByID)
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean isCorrectPriceType(String price) {
        try {
            Double.parseDouble(price);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addBook(Book book) {
        String sql = "insert into books (title, description, price, author_id) values(?,?,?,?)";

        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setString(3, String.valueOf(book.getPrice()));
            ps.setInt(4, book.getAuthor_id().getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        String sql = "select * from books";
        List<Book> books = new ArrayList<>();

        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author authorByID = AUTHOR_MANAGER.getAuthorByID(resultSet.getInt("author_id"));
                books.add(Book.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .price(Double.parseDouble(resultSet.getString("price")))
                        .author_id(authorByID)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void deleteBookById(int bookId) {
        String sql = "delete from books where id=?";
        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getBookByID(int bookId) {
        String sql = "select * from books where id=?";
        Book book = null;

        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Author authorByID = AUTHOR_MANAGER.getAuthorByID(resultSet.getInt("author_id"));
                book = Book.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .price(Double.parseDouble(resultSet.getString("price")))
                        .author_id(authorByID)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void updateBookData(Book book) {
        String sql = "update books set title=?, description=?, price=?, author_id=? where id=?";

        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setString(3, String.valueOf(book.getPrice()));
            ps.setInt(4, book.getAuthor_id().getId());
            ps.setInt(5, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
