package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Point {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Point(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (!startDate.equals(point.startDate)) return false;
        if (!endDate.equals(point.endDate)) return false;
        if (!title.equals(point.title)) return false;
        return description != null ? description.equals(point.description) : point.description == null;
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Point{" + "startDate=" + startDate + ", endDate=" + endDate + ", title='" + title + '\'' + ", description='" + description + '\'' + '}';
    }
}
