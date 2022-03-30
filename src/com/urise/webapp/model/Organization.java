package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;

public class Organization {
    private final Link homePage;
    private final List<Point> list;

    public Organization(Link homePage, Point... points) {
        this.homePage = homePage;
        this.list = Arrays.asList(points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public String toString() {
        return "Organization{" +
                "list=" + list +
                '}';
    }
}
