#include <iostream>
#include <fstream>
#include <map>

using namespace std;

class Student {
  public:
    string name;
    string surname;
    int number;
    string department;
};

void writeStudents(const map<int, Student>& students) {
    ofstream file("students.txt");
    if (file.is_open()) {
        for (const auto& student : students) {
            file << student.first << " " << student.second.name << " " << student.second.surname << " " << student.second.department << endl;
        }
        file.close();
        cout << "Kayıtlar dosyaya yazıldı." << endl;
    } else {
        cout << "Dosya açılamadı." << endl;
    }
}

map<int, Student> readStudents() {
    map<int, Student> students;
    ifstream file("students.txt");
    if (file.is_open()) {
        int number;
        while (file >> number) {
            Student student;
            file >> student.name >> student.surname >> student.department;
            students[number] = student;
        }
        file.close();
        cout << "Kayıtlar dosyadan okundu." << endl;
    } else {
        cout << "Dosya açılamadı." << endl;
    }
    return students;
}

void addStudent(map<int, Student>& students) {
    int number;
    cout << "Öğrenci numarasını girin: ";
    cin >> number;

    if (students.find(number) != students.end()) {
        cout << "Bu numaraya sahip bir öğrenci zaten kayıtlı." << endl;
        return;
    }

    Student student;
    student.number = number;
    cout << "Öğrenci adını girin: ";
    cin >> student.name;
    cout << "Öğrenci soyadını girin: ";
    cin >> student.surname;
    cout << "Öğrenci bölümünü girin: ";
    cin >> student.department;

    students[number] = student;

    writeStudents(students);
    cout << "Yeni kayıt eklendi." << endl;
}

void updateStudent(map<int, Student>& students) {
    int number;
    cout << "Güncellenecek öğrenci numarasını girin: ";
    cin >> number;

    if (students.find(number) == students.end()) {
        cout << "Bu numaraya sahip bir öğrenci kaydı bulunamadı." << endl;
        return;
    }

    Student& student = students[number];
    cout << "Yeni öğrenci adını girin: ";
    cin >> student.name;
    cout << "Yeni öğrenci soyadını girin: ";
    cin >> student.surname;
    cout << "Yeni öğrenci bölümünü girin: ";
    getline(cin,student.department);

    writeStudents(students);
    cout << "Kayıt güncellendi." << endl;
}

void deleteStudent(map<int, Student>& students) {
    int number;
    cout << "Silinecek öğrenci numarasını girin: ";
    cin >> number;

    if (students.find(number) == students.end()) {
        cout << "Bu numaraya sahip bir öğrenci kaydı bulunamadı." << endl;
        return;
    }

    students.erase(number);

    writeStudents(students);
    cout << "Kayıt silindi." << endl;
}

int main() {
    map<int, Student> students = readStudents();

    while (true) {
        cout << "\n1. Yeni öğrenci kaydı ekle" << endl;
        cout << "2. Öğrenci kaydını güncelle" << endl;
        cout << "3. Öğrenci kaydını sil" << endl;
        cout << "4. Öğrenci bilgilerini görüntüle" << endl;
        cout << "5. Çıkış" << endl;
        cout << "Seçiminizi yapın: ";

        int choice;
        cin >> choice;
        switch (choice) {
          case 1:
              addStudent(students);
              break;
          case 2:
              updateStudent(students);
              break;
          case 3:
              deleteStudent(students);
              break;
          case 4:
            if(students.size() == 0){
              cout << "Kayıt bulunamadı." << endl;
              break;
            }
              for (const auto& student : students) {
                  cout << "\nÖğrenci Numarası: " << student.first << endl;
                  cout << "Adı: " << student.second.name << endl;
                  cout << "Soyadı: " << student.second.surname << endl;
                  cout << "Bölümü: " << student.second.department << endl;
              }
              break;
          case 5:
              writeStudents(students);
              return 0;
          default:
              cout << "\n Hatalı seçim yaptınız. " << endl;
        }
    }
    return 0;
}