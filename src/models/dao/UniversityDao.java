package models.dao;

public interface UniversityDao {

    void headOfDepartment(String departmantName);

    void departmantStatistic(String departmantName);

    void avarageSalaryOfDepartmant(String departmantName);

    void countOfEmployye(String departmantName);

    void searchByTemplate(String template);

}
