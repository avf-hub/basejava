package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        System.out.println(resume.getFullName() + "\n");

        printContacts(resume);
        printBody(resume);
    }

    private static void printContacts(Resume resume) {
        Map<ContactType, String> contacts = resume.getContacts();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STATCKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOME_PAGE, "http://gkislin.ru/");

        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
        System.out.println();
    }

    private static void printBody(Resume resume) {
        Map<SectionType, Section> sections = resume.getSections();
        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));

        List<String> itemsAchievement = new ArrayList<>();
        itemsAchievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие " +
                "(JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        itemsAchievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, " +
                "DuoSecurity, Google Authenticator, Jira, Zendesk.");
        itemsAchievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации " +
                "различных ERP модулей, интеграция CIFS/SMB java сервера.");
        itemsAchievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, " +
                "ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        itemsAchievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, " +
                "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        itemsAchievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), " +
                "Белоруcсии(Erip, Osmp) и Никарагуа.");
        sections.put(SectionType.ACHIEVEMENT, new ListSection(itemsAchievement));

        List<String> itemsQualifications = new ArrayList<>();
        itemsQualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        itemsQualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        itemsQualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        itemsQualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        itemsQualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        itemsQualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        itemsQualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), " +
                "JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        itemsQualifications.add("Python: Django.");
        itemsQualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        itemsQualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        itemsQualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA," +
                " JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        itemsQualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        itemsQualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios," +
                " iReport, OpenCmis, Bonita, pgBouncer.");
        itemsQualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов," +
                " UML, функционального программирования");
        itemsQualifications.add("Родной русский, английский \"upper intermediate\"");
        sections.put(SectionType.QUALIFICATIONS, new ListSection(itemsQualifications));

        List<Organization> organizationsExp = new ArrayList<>();
        organizationsExp.add(new Organization("Java Online Projects", "http://javaops.ru/", LocalDate.of(2013, 10, 1), LocalDate.now(),
                "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        organizationsExp.add(new Organization("Wrike", "https://www.wrike.com/", LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        organizationsExp.add(new Organization("RIT Center", "no website", LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1),
                "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        organizationsExp.add(new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1),
                "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). " +
                "Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        organizationsExp.add(new Organization("Yota", "https://www.yota.ru/", LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1),
                "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
        organizationsExp.add(new Organization("Enkata", "http://enkata.com/", LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1),
                "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        organizationsExp.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1),
                "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        organizationsExp.add(new Organization("Инженер по аппаратному и программному тестированию", "http://www.alcatel.ru/", LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1),
                "Разработчик ПО", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        sections.put(SectionType.EXPERIENCE, new OrganizationSection(organizationsExp));

        List<Organization> organizationsEdu = new ArrayList<>();
        organizationsEdu.add(new Organization("Coursera", "https://www.coursera.org/course/progfun", LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1),
                "Курс", "Functional Programming Principles in Scala\" by Martin Odersky"));
        organizationsEdu.add(new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1),
                "Курс", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
        organizationsEdu.add(new Organization("Siemens AG", "http://www.siemens.ru/", LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1),
                "Курс", "3 месяца обучения мобильным IN сетям (Берлин)"));
        organizationsEdu.add(new Organization("Alcatel", "http://www.alcatel.ru/", LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1),
                "Курс", "6 месяцев обучения цифровым телефонным сетям (Москва)"));
        organizationsEdu.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1),
                "Университет", "Аспирантура (программист С, С++)"));
        organizationsEdu.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1),
                "Университет", "Инженер (программист Fortran, C)"));
        organizationsEdu.add(new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1),
                "Заочная школа", "Закончил с отличием"));
        sections.put(SectionType.EDUCATION, new OrganizationSection(organizationsEdu));

        printMap(sections);
    }

    private static void printMap(Map<SectionType, Section> sections) {
        for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
            if (entry.getKey() == SectionType.ACHIEVEMENT || entry.getKey() == SectionType.QUALIFICATIONS) {
                System.out.println(entry.getKey().getTitle() + "\n");
                for (String item : ((ListSection) entry.getValue()).getItems()) {
                    System.out.println("\u002E " + item);
                }
                System.out.println();
                continue;
            }
            System.out.println(entry.getKey().getTitle() + "\n" + entry.getValue() + "\n");
        }
    }
}
