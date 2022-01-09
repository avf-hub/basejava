import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && uuid.equals(storage[i].toString())) {
                storage[i] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int index = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                index += i;
            }
        }
        return Arrays.copyOfRange(storage, 0, index);
    }

    int size() {
        int index = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                index += i;
            }
        }
        return index;
    }
}
