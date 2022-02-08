package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("ERROR: В хранилище нет резюме c таким " + r.getUuid() + ", обновлять нечего!");
            return;
        }
        storage[index] = r;
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) > 0) {
            System.out.println("ERROR: В хранилище уже есть резюме c таким " + r.getUuid());
            return;
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("ERROR: Хранилище переполнено!");
            return;
        }
        storage[size++] = r;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: В хранилище нет резюме c таким " + uuid);
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
