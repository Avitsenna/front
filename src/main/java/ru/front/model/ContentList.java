package ru.front.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
//класс для держания контента в виде списка(Arraylist)
public class ContentList {
    private List<Content> list;

    public ContentList() {
        list = new ArrayList<>();
    }
}
