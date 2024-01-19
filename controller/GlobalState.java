package com.example.temp;

public class GlobalState
{
        private static int userId;

        public static int getUserId() {
            return userId;
        }

        public static void setUserId(int userId) {
            GlobalState.userId = userId;
        }

}
