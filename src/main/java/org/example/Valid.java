package org.example;

public class Valid {

      public static Boolean checkLogin (String login){
          if (login == null)
              return false;
          if (login.length() > 10 || login.length() < 4)
              return false;
          return true;
      }
      public static Boolean checkPassword(String password) {
        if (password == null)
            return false;
        if (!password.matches("\\d+"))
            return false;
        return password.length() >= 6;
    }

}
