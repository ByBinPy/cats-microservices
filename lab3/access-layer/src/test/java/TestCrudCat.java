import jakarta.persistence.*;
import org.example.declarations.CatDao;
import org.example.implementations.entities.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TestCrudCat {
    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityTransaction mockTransaction;

    @Mock
    private CatDao catDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void saveOrUpdateCat() {
        Cat Cat = new Cat();

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(mockTransaction);

        catDao.save(Cat);

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager, times(2)).getTransaction();
        verify(mockTransaction).begin();
        verify(entityManager).merge(Cat);
        verify(mockTransaction).commit();
        verify(entityManager).close();
    }
    @Test
    void getByIdCat() {
        Cat Cat = new Cat();
        Cat.setId(1L);

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.find(Cat.class, 1L)).thenReturn(Cat);

        Cat result = catDao.findById(1).get();

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).find(Cat.class, 1L);
        verify(entityManager).close();

        assertEquals(Cat, result);
    }

    @Test
    void getAllCats() {
        List<Cat> Cats = List.of(new Cat(), new Cat());
        Query mockQuery = mock(Query.class);
        Class<?> clazz = Cat.class;
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.createQuery("FROM " + clazz.getName())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(Cats);

        List<Cat> result = catDao.findAll();

        verify(entityManagerFactory).createEntityManager();
        verify(mockQuery).getResultList();
        verify(entityManager).createQuery("FROM " + clazz.getName());
        verify(entityManager).close();

        assertEquals(Cats, result);
    }

    @Test
    void getCountCats() {
        List<Cat> Cats = List.of(new Cat(), new Cat());
        Query mockQuery = mock(Query.class);
        Class<?> clazz = Cat.class;
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.createQuery("FROM " + clazz.getName())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(Cats);

        Long count = catDao.count();

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).createQuery("FROM " + clazz.getName());
        verify(mockQuery).getResultList();
        verify(entityManager).close();

        assertEquals(2L, count);
    }
    @Test
    void deleteCat() {
        Cat Cat = new Cat();

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        catDao.delete(Cat);

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).remove(Cat);
        verify(entityManager).close();
    }
}
