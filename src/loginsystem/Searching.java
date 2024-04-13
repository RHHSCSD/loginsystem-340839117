/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author Jaden Wickens
 */
public class Searching {
    
    public int binarySearch(String term, String[] list) {
        int low = 0;
        int high = list.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (list[mid].equals(term)) {
                return mid;
            } else if (list[mid].compareTo(term) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public int seqSearch(String term, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(term)) {
                return i;
            }
        }
        return -1;
    }
}
