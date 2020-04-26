/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.exceptions.NonexistentEntityException;
import Dto.Amistad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Dto.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pc-Victor
 */
public class AmistadJpaController implements Serializable {

    public AmistadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Amistad amistad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = amistad.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                amistad.setUsuario(usuario);
            }
            Usuario amistadRel = amistad.getAmistad();
            if (amistadRel != null) {
                amistadRel = em.getReference(amistadRel.getClass(), amistadRel.getId());
                amistad.setAmistad(amistadRel);
            }
            em.persist(amistad);
            if (usuario != null) {
                usuario.getAmistadList().add(amistad);
                usuario = em.merge(usuario);
            }
            if (amistadRel != null) {
                amistadRel.getAmistadList().add(amistad);
                amistadRel = em.merge(amistadRel);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Amistad amistad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Amistad persistentAmistad = em.find(Amistad.class, amistad.getId());
            Usuario usuarioOld = persistentAmistad.getUsuario();
            Usuario usuarioNew = amistad.getUsuario();
            Usuario amistadRelOld = persistentAmistad.getAmistad();
            Usuario amistadRelNew = amistad.getAmistad();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                amistad.setUsuario(usuarioNew);
            }
            if (amistadRelNew != null) {
                amistadRelNew = em.getReference(amistadRelNew.getClass(), amistadRelNew.getId());
                amistad.setAmistad(amistadRelNew);
            }
            amistad = em.merge(amistad);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getAmistadList().remove(amistad);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getAmistadList().add(amistad);
                usuarioNew = em.merge(usuarioNew);
            }
            if (amistadRelOld != null && !amistadRelOld.equals(amistadRelNew)) {
                amistadRelOld.getAmistadList().remove(amistad);
                amistadRelOld = em.merge(amistadRelOld);
            }
            if (amistadRelNew != null && !amistadRelNew.equals(amistadRelOld)) {
                amistadRelNew.getAmistadList().add(amistad);
                amistadRelNew = em.merge(amistadRelNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = amistad.getId();
                if (findAmistad(id) == null) {
                    throw new NonexistentEntityException("The amistad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Amistad amistad;
            try {
                amistad = em.getReference(Amistad.class, id);
                amistad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The amistad with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = amistad.getUsuario();
            if (usuario != null) {
                usuario.getAmistadList().remove(amistad);
                usuario = em.merge(usuario);
            }
            Usuario amistadRel = amistad.getAmistad();
            if (amistadRel != null) {
                amistadRel.getAmistadList().remove(amistad);
                amistadRel = em.merge(amistadRel);
            }
            em.remove(amistad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Amistad> findAmistadEntities() {
        return findAmistadEntities(true, -1, -1);
    }

    public List<Amistad> findAmistadEntities(int maxResults, int firstResult) {
        return findAmistadEntities(false, maxResults, firstResult);
    }

    private List<Amistad> findAmistadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Amistad.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Amistad findAmistad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Amistad.class, id);
        } finally {
            em.close();
        }
    }

    public int getAmistadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Amistad> rt = cq.from(Amistad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
