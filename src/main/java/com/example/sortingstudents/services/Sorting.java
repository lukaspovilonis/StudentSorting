package com.example.sortingstudents.services;

import com.example.sortingstudents.dtos.Student;
import com.example.sortingstudents.enums.SortEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Sorting {

    public void sort(List<Student> students, SortEnum sortingAlgorithm) {
        switch (sortingAlgorithm) {
            case BUBBLE:
                optimizedBubbleSort(students);
                break;
            case HEAP:
                heapSort(students);
                break;
            case MERGE:
                mergeSort(students);
                break;
            default:
                System.out.println("Not implemented " + sortingAlgorithm);
                break;
        }
    }

    private void optimizedBubbleSort(List<Student> students) {
        int i = 0, n = students.size();
        boolean swapNeeded = true;
        while (i < n - 1 && swapNeeded) {
            swapNeeded = false;
            for (int j = 1; j < n - i; j++) {
                if (students.get(j - 1).getMark() < students.get(j).getMark()) {
                    Student temp = students.get(j - 1);
                    students.set(j - 1, students.get(j));
                    students.set(j, temp);
                    swapNeeded = true;
                }
            }
            if (!swapNeeded) {
                break;
            }
            i++;
        }
    }

    private void mergeSort(List<Student> fullList) {
        if (fullList.size() > 1) {
            List<Student> l = new ArrayList<>(), r = new ArrayList<>();
            int mid = fullList.size() / 2;
            for (int i = 0; i < mid; i++)
                l.add(fullList.get(i));
            for (int j = mid; j < fullList.size(); j++)
                r.add(fullList.get(j));
            mergeSort(l);
            mergeSort(r);


            merge(fullList, l, r);
        }
    }

    private void merge(
            List<Student> fullList, List<Student> l, List<Student> r) {

        List<Student> temp;
        int leftIndex = 0, rightIndex = 0, numbersIndex = 0;
        while (leftIndex < l.size() && rightIndex < r.size()) {
            if (l.get(leftIndex).getMark() > r.get(rightIndex).getMark()) {
                fullList.set(numbersIndex, l.get(leftIndex));
                leftIndex++;
            } else {
                fullList.set(numbersIndex, r.get(rightIndex));
                rightIndex++;
            }
            numbersIndex++;
        }
        int tempIndex;
        if (leftIndex >= l.size()) {
            temp = r;
            tempIndex = rightIndex;
        } else {
            temp = l;
            tempIndex = leftIndex;
        }

        for (int i = tempIndex; i < temp.size(); i++) {
            fullList.set(numbersIndex, temp.get(i));
            numbersIndex++;
        }
    }

    private void heapSort(List<Student> students) {
        int n = students.size();

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(students, n, i);

        for (int i = n - 1; i > 0; i--) {
            Student temp = students.get(0);
            students.set(0, students.get(i));
            students.set(i, temp);

            heapify(students, i, 0);
        }
    }

    private void heapify(List<Student> students, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && students.get(l).getMark() < students.get(largest).getMark())
            largest = l;

        if (r < n && students.get(r).getMark() < students.get(largest).getMark())
            largest = r;

        if (largest != i) {
            Student swap = students.get(i);
            students.set(i, students.get(largest));
            students.set(largest, swap);

            heapify(students, n, largest);
        }
    }


}
