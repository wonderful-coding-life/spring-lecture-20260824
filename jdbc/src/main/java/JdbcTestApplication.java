import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class JdbcTestApplication {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://54.180.249.236:3306/tutor", "tutor", "tutorp");

        JdbcTestApplication application = new JdbcTestApplication();

        application.selectAllMembers(connection);
        application.selectMemberById(connection, 1L);
        application.selectMemberById(connection, 2L);
        application.selectMemberById(connection, 3L);
        application.selectMemberById(connection, 4L);
        application.selectMemberByIdPreparedStatement(connection, 1L);
        application.selectMemberByIdPreparedStatement(connection, 2L);
        application.selectMemberByIdPreparedStatement(connection, 3L);
        application.selectMemberByIdPreparedStatement(connection, 4L);

        connection.close();
    }

    private void selectAllMembers(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM member");
        while (resultSet.next()) {
            Member member = new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age"));
            log.info("회원 {}", member);
        }
    }

    private void selectMemberById(Connection connection, Long memberId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM member WHERE id=" + memberId);
        while (resultSet.next()) {
            Member member = new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age"));
            log.info("회원 {}", member);
        }
    }

    private void selectMemberByIdPreparedStatement(Connection connection, Long memberId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM member WHERE id=?");
        preparedStatement.setLong(1, memberId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Member member = new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age"));
            log.info("회원 {}", member);
        }
    }
}
