package models.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversityDaoImpl implements UniversityDao {

    //language=SQL
    private final String SQL_HEAD_OF_DEPARTMANT = "SELECT a.fullName FROM employers a, departments b WHERE a.id = b.headId AND b.departmentName = ?";

    //language=SQL
    private final String SQL_DEPARTMANT_STATISTIC = "SELECT a.name ,count(*) as 'count' FROM degreeNames a, employers b, appointments c, departments d" +
                                                    " WHERE b.degreeId = a.id AND d.departmentName = ? AND c.departmentId = d.id AND c.employerId = b.id GROUP BY b.degreeId";

    //language=SQL
    private final String SQL_AVARAGE_SALARY_OF_DEPARTMANT = "SELECT AVG(a.salary) as 'average' from employers a, departments b, appointments c" +
                                                            " WHERE a.id = c.employerId AND b.departmentName = ? AND b.id = c.departmentId";

    //language=SQL
    private final String SQL_COUNT_OF_EMPLOYEERS = "SELECT count(*) as 'count' FROM employers b, appointments c, departments d where d.departmentName = ? AND c.departmentId = d.id AND b.id = c.employerId";

    //language=SQL
    private final String SQL_SEARCH_BY_TEMPLATE = "SELECT employers.fullName FROM employers WHERE fullName LIKE ?";

    private Connection connection;

    public UniversityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void headOfDepartment(String departmantName) {
        System.out.println("Head of " + departmantName + " is:");
        resultSetTemplate(departmantName, SQL_HEAD_OF_DEPARTMANT,"fullName");
    }

    @Override
    public void departmantStatistic(String departmantName) {
        System.out.println("Statistics by " + departmantName + ":");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DEPARTMANT_STATISTIC);
            preparedStatement.setString(1, departmantName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + ": " + resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void avarageSalaryOfDepartmant(String departmantName) {
        System.out.print("Average salary of " + departmantName + " is: ");
        resultSetTemplate(departmantName,SQL_AVARAGE_SALARY_OF_DEPARTMANT,"average");
    }

    @Override
    public void countOfEmployye(String departmantName) {
        System.out.print("Count of employers in " + departmantName + " is: ");
        resultSetTemplate(departmantName,SQL_COUNT_OF_EMPLOYEERS,"count");
    }

    @Override
    public void searchByTemplate(String template) {
        System.out.println("Statistics by " + template + ":");
        final String templateSearch = "%" + template + "%";
        resultSetTemplate(templateSearch, SQL_SEARCH_BY_TEMPLATE,"fullName");
    }

    private void resultSetTemplate(String templateSearch, String sql_search_by_template, String columLabel) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_search_by_template);
            preparedStatement.setString(1, templateSearch);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(columLabel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
