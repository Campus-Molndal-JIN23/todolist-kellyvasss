package database;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import todo.Todo;

import org.bson.Document;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoTest {

    @Mock
    private Mongo mongo;
    @Mock
    private MongoCollection<Document> mongoCollection;
    private Todo todo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialisera mock-objekt
        mongo = mock(Mongo.class);
        mongoCollection = mock(MongoCollection.class);
        todo = new Todo();
    }

    @Test
    void v() {
        mongo.create(todo);
        verify(mongo).create(todo);
    }
    @Test
    void testCreate() {
        mongo.create(todo);
        verify(mongo).create(todo);
    }

    @Test
    void testDelete() {
        mongo.delete(todo.getId());
        verify(mongo).delete(todo.getId());
    }

    @Test
    void testUpdate() {
        mongo.update(todo.getId());
        verify(mongo).update(todo.getId());
    }

    @Test
    void testRead() {
        when(mongo.read(todo.getId())).thenReturn(todo);
        assertEquals(todo, mongo.read(todo.getId()));
    }

    @Test
    void testReadAll() {
        when(mongo.getAll()).thenReturn(new ArrayList<Todo>());
        assertEquals(new ArrayList<Todo>(), mongo.getAll());
    }
}