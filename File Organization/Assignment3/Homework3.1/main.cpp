#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;

struct Student {
    string name;
    int age;
    string major;

    Student(string n, int a, string m) {
      this->name = n;
      this->age = a;
      this->major = m;
    }
};

class LinkedList {
private:
    struct Node {
        Student student;
        Node* next;

        Node(Student s) : student(s) {
          this->student = s;
          this->next = nullptr;
        }
    };

    Node* head;

public:
    LinkedList(){
      this->head = nullptr;
    }

    void insert(Student student) {
        Node* newNode = new Node(student);

        if (head == nullptr) {
            head = newNode;
        } 
        else {
            Node* curr = head;
            while (curr->next != nullptr) {
                curr = curr->next;
            }
            curr->next = newNode;
        }
    }

    void update(string name, int newAge, string newMajor) {
        Node* curr = head;
        while (curr != nullptr) {
            if (curr->student.name == name) {
                curr->student.age = newAge;
                curr->student.major = newMajor;
                break;
            }
            curr = curr->next;
        }
    }

    void remove(string name) {
        if (head == nullptr)
            return;

        if (head->student.name == name) {
            Node* temp = head;
            head = head->next;
            delete temp;
            return;
        }

        Node* curr = head;
        while (curr->next != nullptr) {
            if (curr->next->student.name == name) {
                Node* temp = curr->next;
                curr->next = curr->next->next;
                delete temp;
                return;
            }
            curr = curr->next;
        }
    }

    void printToFile(string filename) {
        ofstream file(filename);
        if (file.is_open()) {
            Node* curr = head;
            while (curr != nullptr) {
                file << curr->student.name << "," << curr->student.age << "," << curr->student.major << endl;
                curr = curr->next;
            }
            file.close();
        } else {
            cout << "Dosya açılamadı" << endl;
        }
    }
};
LinkedList dosyadanOku(string filename){
  LinkedList list;

  ifstream file("ogrencibilgileri.txt");
  if (file.is_open()) {
      string line;
      while (getline(file, line)) {
          stringstream Line(line);
          string name, major;
          int age;
          getline(Line, name, ',');
          Line >> age;
          Line.ignore();
          getline(Line, major);

          Student student(name, age, major);
          list.insert(student);
      }
      file.close();
  } 
  else {
      cout << "Dosya açılamadı" << endl;
  }
  return list;
};

int main() {
    LinkedList linkedList = dosyadanOku("ogrencibilgileri.txt");;
  
    Student newStudent("Mustafa Çirci", 20, "Computer Science");
    linkedList.insert(newStudent);

    linkedList.update("Ahmet Yılmaz", 21, "Mathematics");

    linkedList.remove("Ahmet Yılmaz");

    linkedList.printToFile("ogrencibilgileri.txt");

    return 0;
}