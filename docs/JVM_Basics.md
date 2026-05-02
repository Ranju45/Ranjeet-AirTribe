# JVM Basics

## JDK, JRE, and JVM — What's the difference?

**JVM (Java Virtual Machine)**
The JVM is a virtual machine that runs Java programs. It reads compiled Java bytecode
and executes the instructions on whatever operating system it is running on.
You never interact with the JVM directly — it works behind the scenes when you run `java MyProgram`.

**JRE (Java Runtime Environment)**
The JRE includes the JVM plus the standard Java class libraries (like `java.util`, `java.io`).
If you only need to *run* a Java application (not write or compile one), the JRE is enough.

**JDK (Java Development Kit)**
The JDK is the full toolkit for Java developers. It includes:
- The JRE (so you can run programs)
- The Java compiler (`javac`) to turn `.java` source files into `.class` bytecode files
- Debugging tools and other utilities

As a developer, you always install the JDK.

---

## What is Bytecode?

When you compile a `.java` file with `javac`, the output is not machine code specific to
Windows or Linux. Instead, it produces a `.class` file containing **bytecode** — a compact,
platform-neutral set of instructions designed for the JVM to read.

Think of bytecode as a "middle language" — it's closer to machine code than Java source,
but still abstract enough that any JVM on any OS can run it.

---

## "Write Once, Run Anywhere"

This is Java's most famous promise. Because your code compiles to bytecode (not OS-specific
machine code), the same `.class` file can run on Windows, macOS, Linux, or any platform that
has a JVM installed — without recompiling.

You write and compile your code once on your laptop, and the same bytecode file will run
correctly on a server running a completely different operating system. The JVM handles
all the OS-specific translation underneath. This is what makes Java very popular for
cross-platform enterprise applications.
