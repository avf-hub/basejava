import java.util.ArrayList;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = 0; i < size; i++) {
                storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size = i + 1;
                break;
            }
        }
    }

    String get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                return storage[i].toString();
            }
        }
        return uuid;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                storage[--size] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
