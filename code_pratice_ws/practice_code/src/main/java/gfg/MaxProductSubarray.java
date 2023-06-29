package gfg;//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class MaxProductSubarray {
    // Function to find maximum product subarray
    long maxProduct(int[] arr, int n) {
        // code here
        
        long [] maxar = new long[n];
        long [] minar = new long[n];
        
        long maxtillnow = arr[0];
        
        // base cases
        maxar[0] = arr[0];
        minar[0] = min(0, arr[0]);
        
        for (int i = 1; i < n; i++) {
            maxar[i] = max(max(arr[i], maxar[i-1] * (long)arr[i]), minar[i-1] * (long)arr[i]);
            minar[i] = min(min(arr[i], maxar[i-1] * (long)arr[i]), minar[i-1] * (long) arr[i]);
            
            if (maxar[i] > maxtillnow) {
                maxtillnow = maxar[i];
            }
        }
        
        return maxtillnow;
        
    }
    
    long min(long a, long b ) {
        return a < b ? a : b;
    }
    
    long max(long a, long b) {
        return a > b ? a: b;
    }
}