import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.declarations.OwnerDao;
import org.example.implementations.entities.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TestCrudOwner {
    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityTransaction mockTransaction;

    @Mock
    private OwnerDao ownerDao;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrUpdateOwner() {
        Owner owner = new Owner();

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.getTransaction()).thenReturn(mockTransaction);

        ownerDao.save(owner);

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager, times(2)).getTransaction();
        verify(mockTransaction).begin();
        verify(entityManager).merge(owner);
        verify(mockTransaction).commit();
        verify(entityManager).close();
    }
    @Test
    void getByIdOwner() {
        Owner owner = new Owner();
        owner.setId(1);

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.find(Owner.class, 1L)).thenReturn(owner);

        Owner result = ownerDao.getById(1);

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).find(Owner.class, 1L);
        verify(entityManager).close();

        assertEquals(owner, result);
    }

    @Test
    void getAllOwners() {
        List<Owner> owners = List.of(new Owner(), new Owner());
        Query mockQuery = mock(Query.class);
        Class<?> clazz = Owner.class;
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.createQuery("FROM " + clazz.getName())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(owners);

        List<Owner> result = ownerDao.findAll();

        verify(entityManagerFactory).createEntityManager();
        verify(mockQuery).getResultList();
        verify(entityManager).createQuery("FROM " + clazz.getName());
        verify(entityManager).close();

        assertEquals(owners, result);
    }

    @Test
    void getCountOwners() {
        List<Owner> owners = List.of(new Owner(), new Owner());
        Query mockQuery = mock(Query.class);
        Class<?> clazz = Owner.class;
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        when(entityManager.createQuery("FROM " + clazz.getName())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(owners);

        Long count = ownerDao.count();

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).createQuery("FROM " + clazz.getName());
        verify(mockQuery).getResultList();
        verify(entityManager).close();

        assertEquals(2L, count);
    }

    @Test
    void deleteOwner() {
        Owner owner = new Owner();

        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        ownerDao.delete(owner);

        verify(entityManagerFactory).createEntityManager();
        verify(entityManager).remove(owner);
        verify(entityManager).close();
    }
}
