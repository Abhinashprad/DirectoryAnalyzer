package com.analyzer;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter target directory path to scan: ");
        String path = scanner.nextLine();

        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Error: Invalid directory path provided.");
            scanner.close();
            return;
        }

        System.out.println("\nScanning directory: " + dir.getAbsolutePath() + " ...");
        long startTime = System.currentTimeMillis();

        DirectoryScanner scannerEngine = new DirectoryScanner();
        scannerEngine.scanDirectory(dir);

        long endTime = System.currentTimeMillis();
        Map<String, List<File>> duplicates = scannerEngine.getDuplicates();

        System.out.println("\n================ SCAN SUMMARY ================");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Total files scanned: " + scannerEngine.getTotalFilesScanned());
        System.out.println("Total data scanned: " + (scannerEngine.getTotalBytesScanned() / (1024 * 1024)) + " MB");
        System.out.println("Duplicate groups found: " + duplicates.size());

        if (duplicates.isEmpty()) {
            System.out.println("\nNo duplicate files found! Your directory is clean.");
            scanner.close();
            return;
        }

        System.out.println("\n================ DUPLICATE DETAILS ================");
        long wastedBytes = 0;
        for (Map.Entry<String, List<File>> entry : duplicates.entrySet()) {
            List<File> fileList = entry.getValue();
            System.out.println("\n[Match Group Hash: " + entry.getKey().substring(0, 10) + "...]");
            for (File f : fileList) {
                System.out.println("  -> " + f.getAbsolutePath());
            }
            wastedBytes += fileList.get(0).length() * (fileList.size() - 1);
        }
        System.out.println("\nPotential recoverable storage space: " + (wastedBytes / (1024 * 1024)) + " MB");
        
        scanner.close();
    }
}