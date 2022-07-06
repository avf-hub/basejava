package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("delete from resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("update resume set full_name = ? where uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContacts(conn, r);
            deleteSections(conn, r);
            insertContact(conn, r);
            insertSections(conn, r);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("insert into resume (uuid, full_name) values (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContact(conn, r);
            insertSections(conn, r);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn.prepareStatement("select * from resume where uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));
            }

            try (PreparedStatement ps = conn.prepareStatement("select * from contact where resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, r);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("select * from section where resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, r);
                }
            }

            return r;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("delete from resume r where r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("select * from resume order by full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    map.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("select * from contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = map.get(rs.getString("resume_uuid"));
                    addContact(rs, r);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("select * from section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = map.get(rs.getString("resume_uuid"));
                    addSection(rs, r);
                }
            }

            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("select count(*) as size from resume r", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("size") : 0;
        });
    }

    private void insertContact(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("insert into contact (resume_uuid, type, value) values (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection conn, Resume r) {
        sqlHelper.execute("delete from contact where resume_uuid = ?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            r.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            r.addSection(SectionType.valueOf(rs.getString("type")), new ListSection(Arrays.asList(content.split("\n"))));
        }
    }

    private void insertSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("insert into section (resume_uuid, type, content) values (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, String.valueOf(e.getValue()));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteSections(Connection conn, Resume r) {
        sqlHelper.execute("delete from section where resume_uuid = ?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }
}
