package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = new File("C:\\temp\\file.tmp");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1 = new User();

            user1.setFirstName("Petya");
            user1.setLastName("Ivanov");
            user1.setBirthDate(new Date());
            user1.setMale(true);
            user1.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user1);

//            User user2 = new User();
//            user2.setFirstName("Pety");
//            user2.setLastName("Ivano");
//            user2.setBirthDate(new Date());
//            user2.setMale(false);
//            user2.setCountry(User.Country.UKRAINE);
//            javaRush.users.add(user2);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
//            //implement this method - реализуйте этот метод
//            PrintWriter pw = new PrintWriter(outputStream);
//            if(users!=null){
//                for (User user : users) {
//                 String fName = (user.getFirstName() == null) ? "null" : user.getFirstName();
//                 pw.println(fName);
//                 String lName = (user.getLastName() == null) ? "null" : user.getLastName();
//                 pw.println(lName);
//                 pw.println(user.getBirthDate().getTime());
//                 pw.println(user.isMale());
//                 pw.println(user.getCountry());
//                }
//
//            //    pw.close();
//            }
//            pw.flush();
//             //   pw.close();
            DataOutputStream outToFile = new DataOutputStream(outputStream);
            outToFile.writeInt(users.size());
            for (User user : users) {
                String firstName = (user.getFirstName() == null) ? "null" : user.getFirstName();
                outToFile.writeUTF(firstName);
                String lastName = (user.getLastName() == null) ? "null" : user.getLastName();
                outToFile.writeUTF(lastName);
                outToFile.writeLong(user.getBirthDate().getTime());
                outToFile.writeBoolean(user.isMale());
                outToFile.writeUTF(user.getCountry().name());
            }
            outToFile.flush();
            }


        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            while(br.ready()) {
//                User user = new User();
//
//                String firstName = br.readLine();
//                if(firstName.equals("null")) firstName = null;
//                user.setFirstName(firstName);
//
//                String lastName = br.readLine();
//                if(lastName.equals("null")) lastName = null;
//                user.setLastName(lastName);
//
//                user.setBirthDate(new Date(br.readLine()));
//                user.setMale(Boolean.parseBoolean(br.readLine()));
//                user.setCountry(User.Country.valueOf(br.readLine()));
//
//                users.add(user);
//            }
//            br.close();

            DataInputStream fromFile = new DataInputStream(inputStream);

            int usersCount = fromFile.readInt();
            for (int i = 0; i < usersCount; i++) {
                User user = new User();
                String firstName = fromFile.readUTF();
                if (firstName.equals("null")) firstName = null;
                user.setFirstName(firstName);
                String lastName = fromFile.readUTF();
                if (lastName.equals("null")) lastName = null;
                user.setLastName(lastName);
                user.setBirthDate(new Date(fromFile.readLong()));
                user.setMale(fromFile.readBoolean());
                user.setCountry(User.Country.valueOf(fromFile.readUTF()));
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
