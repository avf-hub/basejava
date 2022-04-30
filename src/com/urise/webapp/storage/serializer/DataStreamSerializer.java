package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeListSection((ListSection) entry.getValue(), dos);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeOrganizationSection((OrganizationSection) entry.getValue(), dos);
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int j = 0; j < size; j++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        readListSection(resume, sectionType, dis);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        readOrganizationSection(resume, sectionType, dis);
                        break;
                }
            }
            return resume;
        }
    }

    private void writeListSection(ListSection listSection, DataOutputStream dos) throws IOException {
        List<String> list = listSection.getItems();
        dos.writeInt(list.size());
        for (String str : list) {
            dos.writeUTF(str);
        }
    }

    private void writeOrganizationSection(OrganizationSection organizationSection, DataOutputStream dos) throws IOException {
        List<Organization> listOrganizations = organizationSection.getOrganizations();
        dos.writeInt(listOrganizations.size());
        for (Organization organization : listOrganizations) {
            List<Organization.Position> listPositions = organization.getPositions();
            dos.writeInt(listPositions.size());
            for (Organization.Position position : listPositions) {
                Link homePage = organization.getHomePage();
                dos.writeUTF(homePage.getName());
                dos.writeUTF(homePage.getUrl());
                writeLocalDate(dos, position.getStartDate());
                writeLocalDate(dos, position.getEndDate());
                dos.writeUTF(position.getTitle());
                dos.writeUTF(position.getDescription());
            }
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonthValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void readListSection(Resume resume, SectionType sectionType, DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        resume.addSection(sectionType, new ListSection(list));
    }

    private void readOrganizationSection(Resume resume, SectionType sectionType, DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Organization> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int sizePositions = dis.readInt();
            List<Organization.Position> listPositions = new ArrayList<>(sizePositions);
            Link link = new Link(dis.readUTF(), dis.readUTF());
            for (int j = 0; j < sizePositions; j++) {
                listPositions.add(new Organization.Position(readLocalDate(dis),
                        readLocalDate(dis), dis.readUTF(), dis.readUTF()));
            }
            list.add(new Organization(link, listPositions));
        }
        resume.addSection(sectionType, new OrganizationSection(list));
    }

}
