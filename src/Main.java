import connection.GetConnectionImpl;
import models.dao.UniversityDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UniversityDaoImpl universityDao = new UniversityDaoImpl(GetConnectionImpl.getInstance().getConection());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Do your choise: " + "\n" + "If you want to know 'Head of Departmant' -> write 'head'\n"
                + "'Departmant statistics' -> write 'statistic\n"
                + "'Average Salary' -> write 'salary'\n"
                + "'Count of Employers' -> write 'count'\n"
                + "'Search by Template' -> write 'search'");

        String str = scanner.nextLine();

        while (!str.equals("exit")) {
            switch (str) {
                case "head" :
                    universityDao.headOfDepartment("Economic");
                    break;
                case "statistic" :
                    universityDao.departmantStatistic("Economic");
                    break;
                case "salary" :
                    universityDao.avarageSalaryOfDepartmant("Economic");
                    break;
                case "count" :
                    universityDao.countOfEmployye("Economic");
                    break;
                case "search" :
                    universityDao.searchByTemplate("on");
                    break;
                    default:
                        System.out.println("You wrote not right data!");
            }
            System.out.println("---------------------------------------------------------");
            System.out.println("Repeat your choise!");
            str = scanner.nextLine();
        }

        scanner.close();
        GetConnectionImpl.getInstance().closeConnection();



    }

}

