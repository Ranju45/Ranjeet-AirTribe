# Setup Instructions

## JDK Version Used
- **JDK 11** (LTS recommended; JDK 17 or 21 also work fine)

## Installing JDK

### Windows
1. Download JDK from https://adoptium.net or https://jdk.java.net
2. Run the installer and follow the prompts.
3. Add `JAVA_HOME` to your system environment variables pointing to the JDK install folder.
4. Add `%JAVA_HOME%\bin` to your PATH.

### macOS
```bash
brew install openjdk@17
```

### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

## Verify Installation

```bash
java -version
# Expected output: openjdk version "17.x.x" ...

javac -version
# Expected output: javac 17.x.x
```

## Hello World Walkthrough

1. Create a file `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

2. Compile:
```bash
javac HelloWorld.java
# Produces: HelloWorld.class
```

3. Run:
```bash
java HelloWorld
# Output: Hello, World!
```

This confirms your JDK is set up correctly and you are ready to work on LearnTrack.
