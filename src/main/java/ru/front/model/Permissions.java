package ru.front.model;

//класс для описания возможностей/полномочия для разных ролей
//можно потом как-нибудь его расширить:добавить Бан например
public enum Permissions {
    USER_READ("user:read"),
    USER_WRITE("user:write");
    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
