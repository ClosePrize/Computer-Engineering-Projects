#include <cstdlib>
#include <ctime>
#include <fstream>
#include <iostream>

void selectionSort(int arr[], int n) {
  for (int i = 0; i < n - 1; i++) {
    int minIndex = i;
    for (int j = i + 1; j < n; j++) {
      if (arr[j] < arr[minIndex]) {
        minIndex = j;
      }
    }
    std::swap(arr[i], arr[minIndex]);
  }
}

void insertionSort(int arr[], int n) {
  for (int i = 1; i < n; i++) {
    int key = arr[i];
    int j = i - 1;
    while (j >= 0 && arr[j] > key) {
      arr[j + 1] = arr[j];
      j--;
    }
    arr[j + 1] = key;
  }
}

// Merge Sort
void merge(int arr[], int left, int middle, int right) {
  int n1 = middle - left + 1;
  int n2 = right - middle;

  int *leftArr = new int[n1];
  int *rightArr = new int[n2];

  for (int i = 0; i < n1; i++) {
    leftArr[i] = arr[left + i];
  }
  for (int j = 0; j < n2; j++) {
    rightArr[j] = arr[middle + 1 + j];
  }

  int i = 0, j = 0, k = left;
  while (i < n1 && j < n2) {
    if (leftArr[i] <= rightArr[j]) {
      arr[k] = leftArr[i];
      i++;
    } else {
      arr[k] = rightArr[j];
      j++;
    }
    k++;
  }

  while (i < n1) {
    arr[k] = leftArr[i];
    i++;
    k++;
  }

  while (j < n2) {
    arr[k] = rightArr[j];
    j++;
    k++;
  }

  delete[] leftArr;
  delete[] rightArr;
}

void mergeSort(int arr[], int left, int right) {
  if (left < right) {
    int middle = left + (right - left) / 2;
    mergeSort(arr, left, middle);
    mergeSort(arr, middle + 1, right);
    merge(arr, left, middle, right);
  }
}

int main() {
  std::ifstream inputFile("veriler.txt");
  if (!inputFile) {
    std::cerr << "Dosya acilamadi!" << std::endl;
    return 1;
  }

  int data[1000];
  int index = 0;
  int num;

  while (inputFile >> num) {
    data[index] = num;
    index++;
  }
  inputFile.close();

  int selectionSortData[1000];
  std::copy(std::begin(data), std::end(data), std::begin(selectionSortData));

  clock_t startTime = clock();
  selectionSort(selectionSortData, 1000);
  clock_t endTime = clock();
  double selectionSortTime = double(endTime - startTime) / CLOCKS_PER_SEC;

  int insertionSortData[1000];
  std::copy(std::begin(data), std::end(data), std::begin(insertionSortData));

  startTime = clock();
  insertionSort(insertionSortData, 1000);
  endTime = clock();
  double insertionSortTime = double(endTime - startTime) / CLOCKS_PER_SEC;

  int mergeSortData[1000];
  std::copy(std::begin(data), std::end(data), std::begin(mergeSortData));

  startTime = clock();
  mergeSort(mergeSortData, 0, 999);
  endTime = clock();
  double mergeSortTime = double(endTime - startTime) / CLOCKS_PER_SEC;

  std::ofstream outputFile("siralanan_veriler.txt");
  if (!outputFile) {
    std::cerr << "Dosya acilamadi!" << std::endl;
    return 1;
  }

  for (int i = 0; i < 1000; i++) {
    outputFile << mergeSortData[i] << std::endl;
  }
  outputFile.close();

  std::cout << "Selection Sort zaman: " << selectionSortTime << " saniye"
            << "\n";
  std::cout << "Insertion Sort zaman: " << insertionSortTime << " saniye"
            << "\n";
  std::cout << "Merge Sort zaman: " << mergeSortTime << " saniye"
            << "\n";

  return 0;
}