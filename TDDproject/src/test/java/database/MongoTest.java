package database;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import todo.Todo;

import javax.swing.text.Document;

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
        mongo = mock(Mongo.class);
        mongoCollection = mock(MongoCollection.class);
        todo = new Todo();
    }
    @Test
    void testCreate() {


        mongo.create(todo);

        verify(mongoCollection).insertOne(any(Document.class));

    }
    @Test
    void testDelete() {

        mongo.delete(todo.getId());

        verify(mongoCollection).deleteOne(todo.toDoc());

    }
    @Test
    void testUpdate() {

        mongo.update(todo.getId());
        verify(mongoCollection).updateOne((Bson) todo.toDoc(), (Bson) any(Document.class));
    }
    @Test
    void testRead() {
        when(mongo.read(todo.getId())).thenReturn(new Todo());

        assertEquals(new Todo(), mongo.read(todo.getId()));

    }
    @Test
    void testReadAll() {
        when(mongo.getAll()).thenReturn(new ArrayList<Todo>());
        assertEquals(new ArrayList<Todo>(), mongo.getAll());
    }
}