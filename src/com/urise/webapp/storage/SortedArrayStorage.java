package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    /*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
*/

    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void deleteResume(int index) {
        int indexMoved = size - index - 1;
        if (indexMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, indexMoved);
        }
    }

    @Override
    protected void insertResume(Resume r, int index) {
        int sortIndex = -index - 1;
        System.arraycopy(storage, sortIndex, storage, sortIndex + 1, size - sortIndex);
        storage[sortIndex] = r;
    }
}
