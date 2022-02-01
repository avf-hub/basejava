package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("ERROR: В хранилище нет резюме c таким " + r.getUuid() + ", обновлять нечего!");
            return;
        }
        storage[index] = r;
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("ERROR: Хранилище переполнено!");
            return;
        }
        int index = getIndex(r.getUuid());
        if (index == -1) {
            storage[size++] = r;
            return;
        }
        System.out.println("ERROR: В хранилище уже есть резюме c таким " + r.getUuid());
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: В хранилище нет резюме c таким " + uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: В хранилище нет резюме c таким " + uuid);
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}