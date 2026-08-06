What is JDK, JRE, JVM

JDK : (Java Development kit) The java development software package which is for develop the java application
It includes 
JRE (Java Runtime Enviorment)
Java compiler javac
Debigging tools
JVM(Java virtual machine)

JRE : (Java Runtime machine)
it includes the core libries which is provides the enviorment for run the java application.
it contanies JVM and java class libries for ex java.util etc

JVM (Java Virtual Machine) is a virtual machine that executes Java bytecode. It converts the bytecode into machine code that the operating system can understand.

The JVM also provides:
Memory management,
Garbage Collection,
Platform independence,
Security features,

Every operating system has its own JVM implementation, but all JVMs understand the same Java bytecode.

What is bytecode?

The bytecode is generating when we compile source file through javac compiler. It is converted into .class file.
this code means bytecode. bytecode is intermediate code which is platform independant of any operating system.
JVM read this bytecode and converts into machine code before executing the program.


```
Java Source Code (.java)
          │
          ▼
      Java Compiler (javac)
          │
          ▼
      Bytecode (.class)
          │
          ▼
         JVM
          │
          ▼
      Machine Code
          │
          ▼
      Program Output
```

What does "Write Once, Run Anywhere" mean?
Java follows the principle **"Write Once, Run Anywhere (WORA)."** A Java program is compiled only once into bytecode. The same bytecode can run on any operating system that has a compatible JVM installed.

For example, a Java application compiled on Windows can also run on Linux or macOS without changing the source code. This makes Java a platform-independent programming language and one of the reasons for its popularity in enterprise application development.
