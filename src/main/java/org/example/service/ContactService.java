package org.example.service;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import java.sql.SQLException;
import java.util.List;

public class ContactService {
    private final ContactDao dao = new ContactDao();

    public List<Contact> listAll() {
        try { return dao.findAll(); }
        catch(SQLException e) { throw new RuntimeException(e); }
    }
    public void add(String name, String phone) {
        Contact c = new Contact(); c.setName(name); c.setPhone(phone);
        try { dao.save(c); } catch(SQLException e) { throw new RuntimeException(e);}
    }
    public void remove(int id) {
        try { dao.delete(id); } catch(SQLException e){ throw new RuntimeException(e);}
    }
}