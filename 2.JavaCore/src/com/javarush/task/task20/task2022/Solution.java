package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
     //   out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    //    in.close();
        stream = new FileOutputStream(fileName,true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Solution s1 = new Solution("C:\\temp\\hello.txt");
//        s1.writeObject("Hello hello!\r\n");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\temp\\file.tmp"));
//        oos.writeObject(s1);
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\temp\\file.tmp"));
//        Solution s2 = (Solution) ois.readObject();
//        s2.writeObject("Hello again!!");
        Solution solution = new Solution("C:\\\\temp\\\\hello.txt");
        solution.writeObject("Shalom!\r\n");
        ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("C:\\\\temp\\\\file.tmp"));
        oos.writeObject(solution);
        ObjectInputStream ois = new ObjectInputStream((new FileInputStream("C:\\\\temp\\\\file.tmp")));
        Solution solution2 = (Solution) ois.readObject();
        solution2.writeObject("Shalom again!");
    }
}
