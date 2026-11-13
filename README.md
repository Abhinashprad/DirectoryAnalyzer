# Directory Analyzer & Duplicate File Finder

A robust Java application designed to recursively traverse local file directories, compute cryptographic SHA-256 file hashes, and identify duplicate files to reclaim storage space.

## Features
- **Recursive Traversal:** Deep-scans nested directories and sub-folders to discover all files.
- **Cryptographic Hashing:** Uses `MessageDigest` (SHA-256) to compare exact binary file contents rather than relying on file names or extensions.
- **Data Structures:** Leverages Java `HashMap` and `ArrayList` for efficient duplicate grouping.
- **Storage Metrics:** Calculates total files scanned, total bytes processed, and potential disk space recovery.

## Tech Stack
- **Language:** Java (JDK 8+)
- **Core Concepts:** Object-Oriented Programming, File I/O, Recursion, Cryptographic Hashing, Collections Framework.

## project Structure
```text
DirectoryAnalyzer/
├── src/
│   └── com/
│       └── analyzer/
│           ├── FileHasher.java
│           ├── DirectoryScanner.java
│           └── Main.java
└── README.md
```

# How to Run
## Open your terminal or command prompt and navigate to the src directory:
cd DirectoryAnalyzer/src

## Compile all the Java files together:
javac com/analyzer/*.java

## Run the main application:
java com.analyzer.Main

Enter the full path of the folder you wish to scan when prompted by the console.
