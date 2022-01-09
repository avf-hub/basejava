import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && uuid != null && uuid.equals(storage[i].toString())) {
                return storage[i].toString();
            }
        }
        return uuid;
    }

    void delete(String uuid) {
        Resume[] tempStorage = Arrays.copyOf(storage, storage.length);
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && uuid.equals(storage[i].toString())) {
                storage[i] = null;
                break;
            }
        }
        for (int j = 0; j < storage.length; j++) {
            if (tempStorage[j] != null && !tempStorage[j].equals(storage[j])) {
                storage[j] = tempStorage[j + 1];
                storage[j + 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        return size;
    }
}
