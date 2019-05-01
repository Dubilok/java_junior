package test;

import connection.GetConnectionImpl;
import models.dao.UniversityDaoImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static String selectDepartment(Scanner scanner, UniversityDaoImpl universityDao) throws Exception {
        System.out.println("Select the name of department:");
        ArrayList<String> list = universityDao.departmentsNames();
        int c = 0;
        for (String s : list) {
            System.out.println(c++ + ": " + s);
        }
        String str = scanner.nextLine();
        c = Integer.parseInt(str);

        if (list.size() > c) return list.get(c);
        else throw new Exception("Wrong department name!");

    }

    public static void main(String[] args) {

        UniversityDaoImpl universityDao = new UniversityDaoImpl(GetConnectionImpl.getInstance().getConection());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Do your choise: " + "\n" + "If you want to know 'Head of Departmant' -> write '1'\n"
                + "'Departmant statistics' -> write '2'\n"
                + "'Average Salary' -> write '3'\n"
                + "'Count of Employers' -> write '4'\n"
                + "'Search by Template' -> write '5'\n"
                + "'Exit' -> write '0'\n");


        String str;
        int selectQuery = -1;
        while (selectQuery != 0) {
            try {
                str = scanner.nextLine();
                selectQuery = Integer.parseInt(str);
                switch (selectQuery) {
                    case 1:
                        str = selectDepartment(scanner, universityDao);
                        universityDao.headOfDepartment(str);
                        break;
                    case 2:
                        str = selectDepartment(scanner, universityDao);
                        universityDao.departmantStatistic(str);
                        break;
                    case 3:
                        str = selectDepartment(scanner, universityDao);
                        universityDao.avarageSalaryOfDepartmant(str);
                        break;
                    case 4:
                        str = selectDepartment(scanner, universityDao);
                        universityDao.countOfEmployye(str);
                        break;
                    case 5:
                        System.out.println("Enter the name of employer:");
                        str = scanner.nextLine();
                        universityDao.searchByTemplate(str);
                        break;
                    case 0:
                        selectQuery = 0;
                        break;
                    default:
                        System.out.println("You wrote not right data!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (selectQuery != 0) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Repeat your choise!");
            }
        }

        scanner.close();
        GetConnectionImpl.getInstance().closeConnection();


    }

}

