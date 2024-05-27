#include <fstream>
#include <iostream>
#include <sstream>
#include <unordered_map>
#include <vector>

using namespace std;
struct Book {
  string title;
  string author;
  string year;
  string isbn;
};

vector<Book> readDataFromCSV(const string &filePath) {
  vector<Book> data;
  ifstream file(filePath);
  if (file.is_open()) {
    string line;
    while (getline(file, line)) {
      istringstream iss(line);
      string title, author, year, isbn;
      getline(iss, title, ',');
      getline(iss, author, ',');
      getline(iss, year, ',');
      getline(iss, isbn, ',');
      Book book{title, author, year, isbn};
      data.push_back(book);
    }
    file.close();
  }
  return data;
}

vector<Book> linearSearch(const vector<Book> &data, const string &keyword) {
  vector<Book> results;
  for (const auto &book : data) {
    if (book.title.find(keyword) != string::npos ||
        book.author.find(keyword) != string::npos) {
      results.push_back(book);
    }
  }
  return results;
}

vector<Book> hashSearch(const vector<Book> &data, const string &keyword) {
  unordered_map<string, vector<Book>> hashTable;
  for (const auto &book : data) {
    hashTable[book.title].push_back(book);
    hashTable[book.author].push_back(book);
  }
  if (hashTable.find(keyword) != hashTable.end()) {
    return hashTable[keyword];
  } else {
    return {};
  }
}

 void showResults(const vector<Book> &results) {
  if (!results.empty()) {
    for (const auto &book : results) {
      cout << "Title: " << book.title << endl;
      cout << "Author: " << book.author << endl;
      cout << "Year: " << book.year << endl;
      cout << "ISBN: " << book.isbn << endl;
      cout << endl;
    }
  } else {
    cout << "Aranan bilgi bulunamadı." << endl;
  }
}

int main() {
  string filePath = "tr_books.csv"; 
  vector<Book> data = readDataFromCSV(filePath);

  while (true) {
    cout << "Arama yapmak için kitap adını veya yazar adını girin (Çıkmak ""için 'q' tuşuna basın):" << endl;
    string keyword;
    getline(cin, keyword);

    if (keyword == "q") {
      break;
    }

    vector<Book> resultsLinear = linearSearch(data, keyword);
    cout << "Lineer Arama Sonuçları:" << endl;
    showResults(resultsLinear);

    vector<Book> resultsHash = hashSearch(data, keyword);
    cout << "HashTable Arama Sonuçları:" << endl;
    showResults(resultsHash);
  }
  return 0;
}