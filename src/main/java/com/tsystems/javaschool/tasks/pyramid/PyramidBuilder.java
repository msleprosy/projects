package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;

import static java.util.Collections.sort;


public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        if(inputNumbers.contains(null)) throw new CannotBuildPyramidException("Null value");
        int n = isPyramid(inputNumbers.size());
        if (n != 0) {
            int[][] arr = new int[n][2 * n - 1];
                    sort(inputNumbers);
            int index = 0;
            for (int i = 0; i < n; i++) {
                int k = n - i - 1;
                for (int j = 0; j <= i; j++) {
                    arr[i][k] = inputNumbers.get(index);
                    index = index + 1;
                    k = k + 2;
                }
            }
            return arr;
        }
        throw new CannotBuildPyramidException("Null value");
    }

    private int isPyramid(int size) {
        if (size == 0)
            return 0;
        for (int i = 1; i <= size / 2 + 1; i++) {
            if (size == i * (i + 1) / 2) {
                return i;
            }
        }
        return 0;
    }
}
