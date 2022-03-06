package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new TreeMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        Iterator<Map.Entry<String, Resume>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            String searchKey = iterator.next().getKey();
            if (uuid.equals(searchKey)) {
                return searchKey;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] newResume = new Resume[map.size()];
        Iterator<Map.Entry<String, Resume>> iterator = map.entrySet().iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            newResume[i] = iterator.next().getValue();
        }
        return newResume;
    }

    @Override
    public int size() {
        return map.size();
    }
}
