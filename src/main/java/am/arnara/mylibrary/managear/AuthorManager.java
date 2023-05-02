package am.arnara.mylibrary.managear;

import am.arnara.mylibrary.db.DBConnectionProvider;
import am.arnara.mylibrary.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {
    private static final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();

    public void updateAuthorData(Author author) {
        String sql = "update authors set name=?, surname=?, email=?, age=? where id=?";

        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.setInt(5, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author getAuthorByID(int id) {
        String sql = "select * from authors where id=?";
        Author author = null;

        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                author = Author.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    public boolean isAuthorInDB(String email) {
        String sql = "select * from authors where email=?";

        boolean isAuthor = false;

        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                isAuthor = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAuthor;
    }

    public List<Author> getAuthors() {
        String sql = "select * from authors";
        List<Author> authors = new ArrayList<>();

        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                authors.add(Author.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public void addAuthor(Author author) {
        String sql = "insert into authors (name, surname, email, age) values(?,?,?,?)";

        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthorById(int id) {
        String sql = "delete from authors where id=?";
        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
