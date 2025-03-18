// DO NOT USE PACKAGE

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class StudentManagementImpl implements StudentManagement {

    private ArrayList<Student> students;

    public StudentManagementImpl() {
        this.students = new ArrayList<>();
    }

    public StudentManagementImpl(String dataPath) {
        this.students = new ArrayList<>();
        readData(dataPath);
    }

    private void readData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                students.add(new Student(data[0], data[1], data[2], Double.parseDouble(data[3]), data[4]));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public int getNoOfStudent() {
        return students.size();
    }

    @Override
    public int getNoOfStudent_byGender(String gender) {
        return (int) students.stream().filter(s -> s.getGender().equalsIgnoreCase(gender)).count();
    }

    @Override
    public int getNoOfStudent_byMajor(String major) {
        return (int) students.stream().filter(s -> s.getMajor().equalsIgnoreCase(major)).count();
    }

    @Override
    public int getNoOfStudent_byGPA(double gpa) {
        return (int) students.stream().filter(s -> s.getGpa() < gpa).count();
    }

    @Override
    public Student findStudent_byName(String name) {
        return students.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public Student findStudent_byID(String id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public ArrayList<Student> findStudent_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Student> findStudent_byGPA(double gpa) {
        return students.stream()
                .filter(s -> s.getGpa() < gpa)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public double getAvgGPA() {
        return students.stream().mapToDouble(Student::getGpa).average().orElse(0);
    }

    @Override
    public Student getHighestGPA_byGender(String gender) {
        return students.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
    }

    @Override
    public Student getHighestGPA_byFName(String fname) {
        return students.stream()
                .filter(s -> s.getName().split(" ")[0].equalsIgnoreCase(fname))
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
    }

    @Override
    public Student getHighestGPA_byLName(String lname) {
        return students.stream()
                .filter(s -> s.getName().split(" ")[1].equalsIgnoreCase(lname))
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
    }

    @Override
    public Student getHighestGPA_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
    }

    @Override
    public Student getLowestGPA_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .min(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
    }

    @Override
    public ArrayList<Student> getTop10_byGPA() {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Student> getTop10GPA_byGender(String gender) {
        return students.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Student> getTop10GPA_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Student> getLast10GPA_byGender(String gender) {
        return students.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .sorted(Comparator.comparingDouble(Student::getGpa))
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Student> getLast10GPA_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .sorted(Comparator.comparingDouble(Student::getGpa))
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<String> getMajors() {
        return students.stream()
                .map(Student::getMajor)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
