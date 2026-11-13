package com.analyzer;

import java.io.File;
import java.util.*;

public class DirectoryScanner {
    private final Map<String, List<File>> hashMap = new HashMap<>();
    private long totalFilesScanned = 0;
    private long totalBytesScanned = 0;

    public void scanDirectory(File folder) {
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(file);
            } else if (file.isFile()) {
                totalFilesScanned++;
                totalBytesScanned += file.length();
                try {
                    String hash = FileHasher.calculateSHA256(file);
                    hashMap.computeIfAbsent(hash, k -> new ArrayList<>()).add(file);
                } catch (Exception e) {
                    System.err.println("Could not process file: " + file.getAbsolutePath());
                }
            }
        }
    }

    public Map<String, List<File>> getDuplicates() {
        Map<String, List<File>> duplicates = new HashMap<>();
        for (Map.Entry<String, List<File>> entry : hashMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                duplicates.put(entry.getKey(), entry.getValue());
            }
        }
        return duplicates;
    }

    public long getTotalFilesScanned() { return totalFilesScanned; }
    public long getTotalBytesScanned() { return totalBytesScanned; }
}