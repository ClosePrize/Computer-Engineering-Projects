#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <unordered_map>

using namespace std;

struct Student {
    string name;
    int age;
    string grade;
};

class HashTable {
private:
    unordered_map<string, Student> table;

public:
    void insert(const string& key, const Student& student) {
        table[key] = student;
    }

    void remove(const string& key) {
        table.erase(key);
    }

    void update(const string& key, const Student& updatedStudent) {
        if (table.find(key) != table.end()) {
            table[key] = updatedStudent;
        }
    }

    void display() const {
        for (const auto& pair : table) {
            cout << "Key: " << pair.first << ", Value: " << pair.second.name << " " << pair.second.age << " " << pair.second.grade << endl;
        }
    }

    void saveToFile(const string& filename) const {
        ofstream file(filename);

        if (file.is_open()) {
            for (const auto& pair : table) {
                file << pair.first << "," << pair.second.name << "," << pair.second.age << "," << pair.second.grade << "\n";
            }
            file.close();
            cout << "Data saved to file: " << filename << endl;
        } else {
            cout << "Unable to open file: " << filename << endl;
        }
    }

    void loadFromFile(const string& filename) {
        ifstream file(filename);
        table.clear();

        if (file.is_open()) {
            string line;
            while (getline(file, line)) {
                istringstream iss(line);
                string key, name, grade;
                int age;
                getline(iss, key, ',');
                getline(iss, name, ',');
                iss >> age;
                getline(iss >> ws, grade);
                Student student = {name, age, grade};
                table[key] = student;
            }
            file.close();
            cout << "Data loaded from file: " << filename << endl;
        } else {
            cout << "Unable to open file: " << filename << endl;
        }
    }
};

int main() {
    HashTable hashtable;
    hashtable.loadFromFile("ogrencibilgileri.txt");

    Student student1 = {"Mustafa Çirci", 20, "A"};
    hashtable.insert("1306210018", student1);

    Student updatedStudent = {"Ahmet Yılmaz", 21, "B"};
    hashtable.update("1306240001", updatedStudent);

    hashtable.remove("1306210018");

    Student student2 = {"Mustafa Çirci", 20, "A"};
    hashtable.insert("1306210018", student2);

    hashtable.display();

    hashtable.saveToFile("ogrencibilgileri.txt");

    return 0;
}