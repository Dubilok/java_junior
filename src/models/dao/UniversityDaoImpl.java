package models.dao;

import java.sql.*;
import java.util.ArrayList;

public class UniversityDaoImpl implements UniversityDao {

    //language=SQL
    private final String SQL_HEAD_OF_DEPARTMENT = "SELECT a.fullName FROM employers a, departments b WHERE a.id = b.headId AND b.departmentName = ?";

    //language=SQL
    private final String SQL_DEPARTMENT_STATISTIC = "SELECT a.name ,count(*) as 'count' FROM degreeNames a, employers b, appointments c, departments d" +
                                                    " WHERE b.degreeId = a.id AND d.departmentName = ? AND c.departmentId = d.id AND c.employerId = b.id GROUP BY b.degreeId";

    //language=SQL
    private final String SQL_AVERAGE_SALARY_OF_DEPARTMENT = "SELECT AVG(a.salary) as 'average' from employers a, departments b, appointments c" +
                                                            " WHERE a.id = c.employerId AND b.departmentName = ? AND b.id = c.departmentId";

    //language=SQL
    private final String SQL_COUNT_OF_EMPLOYERS = "SELECT count(*) as 'count' FROM appointments c, departments d where d.departmentName = ? AND c.departmentId = d.id";

    //language=SQL
    private final String SQL_SEARCH_BY_TEMPLATE = "SELECT employers.fullName FROM employers WHERE fullName LIKE ?";

    //language=SQL
    private final String SQL_DEPARTMENT_LIST = "SELECT departmentName FROM departments";

    private Connection connection;

    public UniversityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<String> departmentsNames() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            //statement.execute(SQL_DEPARTMENT_LIST);
            ResultSet resultSet = statement.executeQuery(SQL_DEPARTMENT_LIST);
            while (resultSet.next()) {
                list.add(resultSet.getString("departmentName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void headOfDepartment(String departmantName) {
        System.out.println("Head of " + departmantName + " is:");
        resultSetTemplate(departmantName, SQL_HEAD_OF_DEPARTMENT,"fullName");
    }

    @Override
    public void departmantStatistic(String departmantName) {
        System.out.println("Statistics by " + departmantName + ":");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DEPARTMENT_STATISTIC);
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
        resultSetTemplate(departmantName, SQL_AVERAGE_SALARY_OF_DEPARTMENT,"average");
    }

    @Override
    public void countOfEmployye(String departmantName) {
        System.out.print("Count of employers in " + departmantName + " is: ");
        resultSetTemplate(departmantName, SQL_COUNT_OF_EMPLOYERS,"count");
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
