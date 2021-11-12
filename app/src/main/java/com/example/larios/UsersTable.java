package com.example.larios;

import android.provider.BaseColumns;

public class UsersTable implements BaseColumns{
    public static abstract class UsersEntry  {
        public static final String TABLE_NAME ="usuarios";

        public static final String ID = "id";
        public static final String NAME = "nombre";
        public static final String PASS = "pass";
        public static final String ADMIN = "admin";
        public static final String AVATAR_URI = "avatar_uri";
}
}