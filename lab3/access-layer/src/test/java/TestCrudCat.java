import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.implementations.dao.CatDaoImpl;
import org.example.implementations.entities.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    private CatDaoImpl CatDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        CatDao = new CatDaoImpl(Cat.class, entityManagerFactory);
    }

    @Test
    void saveOrUpdateCat() {
        Cat Cat = new Cat();

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(mockTransaction);

        CatDao.saveOrUpdate(Cat);

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

        Cat result = CatDao.getById(1L);

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

        List<Cat> result = CatDao.getAll();

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

        Long count = CatDao.getCount();

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).createQuery("FROM " + clazz.getName());
        verify(mockQuery).getResultList();
        verify(entityManager).close();

        assertEquals(2L, count);
    }
    @Test
    void getItemsCats() {
        List<Cat> Cats = List.of(new Cat(), new Cat());
        Query mockQuery = mock(Query.class);
        Class<?> clazz = Cat.class;
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.createQuery("FROM " + clazz.getName())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(Cats);
        when(mockQuery.setFirstResult(0)).thenReturn(mockQuery);
        when(mockQuery.setMaxResults(10)).thenReturn(mockQuery);

        List<Cat> result = CatDao.getItems(0, 10);

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).createQuery("FROM " + clazz.getName());
        verify(mockQuery).getResultList();
        verify(mockQuery).setFirstResult(0);
        verify(mockQuery).setMaxResults(10);
        verify(mockQuery).getResultList();
        verify(entityManager).close();

        assertEquals(Cats, result);
    }

    @Test
    void deleteCat() {
        Cat Cat = new Cat();

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        CatDao.delete(Cat);

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).remove(Cat);
        verify(entityManager).close();
    }
}
