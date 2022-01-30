package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    public void update(Resume r) {
        Objects.requireNonNull(r, "ERROR: резюме с значением null не будет обновлено!");
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) {
                storage[i] = r;
            }
        }
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("ERROR: Хранилище переполнено!");
            return;
        }
        if (storage[size] == null) {
            storage[size++] = r;
        } else {
            System.out.println("ERROR: В хранилище уже есть резюме!");
        }
    }

    public Resume get(String uuid) {
        Objects.requireNonNull(uuid, "ERROR: В метод get() передано значение null!");
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        Objects.requireNonNull(uuid, "ERROR: Значение null не нужно удалять с хранилища!");
        for (int i = 0; i < size - 1; i++) {
            if (uuid.equals(storage[i].toString())) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                storage[--size] = null;
                break;
            }
        }
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
}
