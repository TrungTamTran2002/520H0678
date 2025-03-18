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
        //Trả về số lượng cựu sinh viên có trong danh sách. 
    }

    @Override
    public int getNoOfStudent_byGender(String gender) {
        return (int) students.stream().filter(s -> s.getGender().equalsIgnoreCase(gender)).count();
        //Trả về số lượng cựu sinh viên, lọc theo giá trị gender
    }

    @Override
    public int getNoOfStudent_byMajor(String major) {
        return (int) students.stream().filter(s -> s.getMajor().equalsIgnoreCase(major)).count();
        //Trả về số lượng cựu sinh viên, lọc theo giá trị major.
    }

    @Override
    public int getNoOfStudent_byGPA(double gpa) {
        return (int) students.stream().filter(s -> s.getGpa() < gpa).count();
        //Trả về số lượng cựu sinh viên có điểm GPA nhỏ hơn giá trị cho trước. 
    }

    @Override
    public Student findStudent_byName(String name) {
        return students.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        //Tìm và trả về đối tượng sinh viên theo name, trả về null nếu không tìm thấy.
    }

    @Override
    public Student findStudent_byID(String id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        //Tìm và trả về đối tượng sinh viên theo id, trả về null nếu không tìm thấy.
    }

    @Override
    public ArrayList<Student> findStudent_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toCollection(ArrayList::new));
                //Tìm và trả về danh sách sinh viên, lọc theo major.
    }

    @Override
    public ArrayList<Student> findStudent_byGPA(double gpa) {
        return students.stream()
                .filter(s -> s.getGpa() < gpa)
                .collect(Collectors.toCollection(ArrayList::new));
                //Tìm và trả về danh sách sinh viên có GPA nhỏ hơn giá trị cho trước. 
    }

    @Override
    public double getAvgGPA() {
        return students.stream().mapToDouble(Student::getGpa).average().orElse(0);
        //Tính và trả về giá trị trung bình của GPA.
    }

    @Override
    public Student getHighestGPA_byGender(String gender) {
        return students.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
                // Tìm và trả về sinh viên có điểm trung bình GPA cao nhất theo giới tính.
    }

    @Override
    public Student getHighestGPA_byFName(String fname) {
        return students.stream()
                .filter(s -> s.getName().split(" ")[0].equalsIgnoreCase(fname))
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
        // Tìm và trả về sinh viên có điểm trung bình GPA cao nhất theo tên (first name).
    }

    @Override
    public Student getHighestGPA_byLName(String lname) {
        return students.stream()
                .filter(s -> s.getName().split(" ")[1].equalsIgnoreCase(lname))
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
                //Tìm và trả về sinh viên có điểm trung bình GPA cao nhất theo họ (last name).
    }

    @Override
    public Student getHighestGPA_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .max(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
                //Tìm và trả về sinh viên có điểm trung bình GPA cao nhất theo ngành (major).
    }

    @Override
    public Student getLowestGPA_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .min(Comparator.comparingDouble(Student::getGpa))
                .orElse(null);
                //Tìm và trả về sinh viên có điểm trung bình GPA thấp nhất theo ngành (major).
    }

    @Override
    public ArrayList<Student> getTop10_byGPA() {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
                //Tìm và trả về danh sách Top10 sinh viên có điểm GPA cao nhất.
    }

    @Override
    public ArrayList<Student> getTop10GPA_byGender(String gender) {
        return students.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
                //Tìm và trả về danh sách Top10 sinh viên có điểm GPA cao nhất theo giới tính (gender). 
    }

    @Override
    public ArrayList<Student> getTop10GPA_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
    //Tìm và trả về danh sách Top10 sinh viên có điểm GPA cao nhất theo ngành học (major).
    }

    @Override
    public ArrayList<Student> getLast10GPA_byGender(String gender) {
        return students.stream()
                .filter(s -> s.getGender().equalsIgnoreCase(gender))
                .sorted(Comparator.comparingDouble(Student::getGpa))
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
                // Tìm và trả về danh sách Last10 sinh viên có điểm GPA thấp nhất theo giới tính (gender).
    }

    @Override
    public ArrayList<Student> getLast10GPA_byMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .sorted(Comparator.comparingDouble(Student::getGpa))
                .limit(10)
                .collect(Collectors.toCollection(ArrayList::new));
                //Tìm và trả về danh sách Last10 sinh viên có điểm GPA thấp nhất theo ngành học (major).
    }

    @Override
    public ArrayList<String> getMajors() {
        return students.stream()
                .map(Student::getMajor)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
                //Tìm và trả về danh sách các ngành học trong dữ liệu, lưu ý loại bỏ những giá trị trùng nhau. Có thể sử dụng HashSet để loại trừ những giá trị trùng nhau.
    }
}
